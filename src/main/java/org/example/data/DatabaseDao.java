package org.example.data;

import org.example.models.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
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
    public List<Game> getAll() {
        final String sql = "SELECT game_id, startedTime, numberOfGuesses, answer, isWon FROM Game;";
        return jdbcTemplate.query(sql, new GameMapper());
    }

    @Override
    public List<Game> displayAll() throws SQLException {
        List<Game> games = jdbcTemplate.query("SELECT * FROM Game", new GameMapper());
        return games;
    }

    @Override
    public Game getById(int id) {
        final String sql = "SELECT game_id, startedTime, numberOfGuesses, answer, isWon FROM Game WHERE game_id = ?;";
        return jdbcTemplate.queryForObject(sql, new GameMapper(), id);
    }


    // Mapper
    private static final class GameMapper implements RowMapper<Game> {

        @Override
        public Game mapRow(ResultSet rs, int index) throws SQLException {
            Game game = new Game();

            game.setId(rs.getInt("game_id"));
            game.setStartedTime(rs.getString("startedTime"));
            game.setNumberOfGuesses(rs.getInt("numberOfGuesses"));
            game.setAnswer(rs.getString("answer"));
            game.setWon(rs.getBoolean("isWon"));

            return game;
        }
    }
}
