package org.example.data;

import org.example.models.Game;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*@Repository
@Profile("memory")
public class InMemoryDao implements Dao {

    private static final List<Game> games = new ArrayList<>();

    @Override
    public List<Game> getAll() {
        System.out.println("In memory");
        return new ArrayList<>(games);
    }

    @Override
    public void displayAll() throws SQLException {

    }

    @Override
    public Game getById(int id) {
        for (Game game: games) {
            if (game.getId() == id) {
                return game;
            }
        }
        return null;
    }


}*/
