package org.example.data;

import org.example.models.Game;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


public class InMemoryDao implements Dao {

    private static final List<Game> games = new ArrayList<>();

    @Override
    public List<Game> getAll() {
        return new ArrayList<>(games);
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


}
