package org.example.data;

import org.example.models.Game;
import org.example.models.Round;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.*;

@Repository
@Profile("database")
public class DatabaseDao implements Dao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DatabaseDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Game beginGame(Game game) {

        final String sql = "INSERT INTO Game(numberOfGuesses, answer, isWon) VALUES(?,?,?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update((Connection conn) -> {

            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            statement.setInt(1, 0);
            statement.setString(2, generateRandom());
            statement.setBoolean(3, false);

            return statement;

        }, keyHolder);

        game.setId(keyHolder.getKey().intValue());

        // Return the newly created game so we can view in Postman
        // USE THIS METHOD IF WANT TO VIEW CREATED GAME IN POSTMAN
        /*final String sqlGet = "SELECT game_id, numberOfGuesses, answer, isWon " +
                "FROM Game WHERE game_id = ?;";
        return jdbcTemplate.queryForObject(sqlGet, new GameMapper(), keyHolder.getKey().intValue());*/

        // This returns a blank game in Postman, just the id as requested by the brief
        return game;
    }

    @Override
    public Game createGame() {

        final String sql = "INSERT INTO Game(numberOfGuesses, answer, isWon) VALUES(?,?,?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update((Connection conn) -> {

            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            statement.setInt(1, 0);
            statement.setString(2, generateRandom());
            statement.setBoolean(3, false);

            return statement;

        }, keyHolder);

        Game currentGame = new Game();
        currentGame.setId(keyHolder.getKey().intValue());  // Assign auto incremented id to Game object

        return currentGame = getById(currentGame.getId());  // Call the rest of the game by the id using the getById
    }

    @Override
    public Round guess(Round round) {
        final String sql = "INSERT INTO Guess(game_id, guess) VALUES(?,?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update((Connection conn) -> {

            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            statement.setInt(1, round.getId());
            statement.setString(2, round.getGuess());

            return statement;

        }, keyHolder);

        // Read the game from sql DB to find entry with that ID
        final String sqlRead = "SELECT * FROM Game WHERE game_id = ?;";
        Game game = jdbcTemplate.queryForObject(sqlRead, new GameMapper(), round.getId());

        // Once we have the Round object from Postman we delete the DB entry to allow duplicate entry's for the next
        // round
        final String sqlDelete = "DELETE FROM Guess WHERE game_id = ?;";
        jdbcTemplate.update(sqlDelete, round.getId());

        compareGuess(game, round.getGuess());

        // If correct guess, mark game as finished (Can't track number of guess via Postman sadly
        if (Objects.equals(game.getAnswer(), round.getGuess())) {
            System.out.println("You won!");
            game.setWon(true);

            final String sqlWon = "UPDATE Game SET isWon = ? WHERE game_id = ?;";

            jdbcTemplate.update(sqlWon, game.getWon(), game.getId());
        }
        return round;
    }

    @Override
    public Boolean compareGuess(Game game, String userGuess) {
        // Convert strings to int arrays
        int[] answerArray = convertToIntArray(game.getAnswer());
        int[] guessArray = convertToIntArray(userGuess);

        int bulls = countBulls(answerArray, guessArray);
        int cows = countCows(answerArray, guessArray);

        if (!(bulls == 4)) {
            System.out.println("Bulls: " + bulls + " Cows: " + cows);
            return false;
        } else {
            return true;
        }
    }

    public Boolean updateDB(Game game) {
        final String sql = "UPDATE Game SET "
                + "numberOfGuesses = ?, "
                + "answer = ?, "
                + "isWon = ? "
                + "WHERE game_id = ?;";

        return jdbcTemplate.update(sql,
                game.getNumberOfGuesses(),
                game.getAnswer(),
                game.getWon(),
                game.getId()) > 0;
    }

    @Override
    public List<Game> getAll() {
        List<Game> games = jdbcTemplate.query("SELECT * FROM Game", new GameMapper());
        return games;
    }

    @Override
    public Game getById(int id) {
        final String sql = "SELECT game_id, numberOfGuesses, answer, isWon FROM Game WHERE game_id = ?;";
        return jdbcTemplate.queryForObject(sql, new GameMapper(), id);
    }

    @Override
    public Boolean deleteRoomById(int id) {
        final String sql = "DELETE FROM Game WHERE id = ?;";
        return jdbcTemplate.update(sql, id) > 0;
    }


    // Mapper
    private static final class GameMapper implements RowMapper<Game> {

        @Override
        public Game mapRow(ResultSet rs, int index) throws SQLException {
            Game game = new Game();

            game.setId(rs.getInt("game_id"));
            game.setNumberOfGuesses(rs.getInt("numberOfGuesses"));
            game.setAnswer(rs.getString("answer"));
            game.setWon(rs.getBoolean("isWon"));

            return game;
        }
    }

    /**
     * Method to covert a string of 4 numbers in the format 'xxxx'
     * to an array of 4 integers
     *
     * @param s String to be converted
     * @return Array of 4 integers
     */
    public int[] convertToIntArray(String s) {
        int[] intArray = new int[4];

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            intArray[i] = Character.getNumericValue(c);
        }

        return intArray;
    }

    public int countBulls(int[] answer, int[] guess) {
        int bulls = 0;

        for (int i = 0; i < answer.length; i++) {
            if (answer[i] == guess[i]) {
                bulls++;
            }
        }

        return bulls;
    }

    public int countCows(int[] answer, int[] guess) {
        int cows = 0;

        for (int i = 0; i < answer.length; i++) {
            for (int j = 0; j < guess.length; j++) {
                if (answer[i] == guess[j] && i != j) {
                    cows++;
                }
            }
        }

        return cows;
    }

    /**
     * Method to generate a random answer in String format
     *
     * @return A random String of 4 integers 'xxxx'
     */
    public String generateRandom() {
        // Use a list of digits and take from the list as an easy way to ensure no duplicates
        List<Integer> digits = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
        Collections.shuffle(digits);

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            stringBuilder.append(digits.get(i));
        }

        return stringBuilder.toString();
    }


}
