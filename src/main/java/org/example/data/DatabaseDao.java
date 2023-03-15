package org.example.data;

import org.example.models.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;

@Repository
@Profile("database")
public class DatabaseDao implements Dao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DatabaseDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Game startGame(Game game) {

        final String sql = "INSERT INTO Game(numberOfGuesses, answer, isWon) VALUES(?,?,?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update((Connection conn) -> {

            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            statement.setInt(1, 0);
            statement.setString(2, "4321");
            statement.setBoolean(3, false);

            return statement;

        }, keyHolder);

        game.setId(keyHolder.getKey().intValue());

        // Return the newly created game so we can view in Postman
        // USE THIS METHOD IF WANT TO VIEW CREATED GAME IN POSTMAN
/*        final String sqlGet = "SELECT game_id, numberOfGuesses, answer, isWon " +
                "FROM Game WHERE game_id = ?;";
        return jdbcTemplate.queryForObject(sqlGet, new GameMapper(), keyHolder.getKey().intValue());*/

        // This returns a blank game in Postman, just the id as requested by the brief
        return game;
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
}
