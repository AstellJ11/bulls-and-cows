package org.example.data;

import org.example.models.Game;
import org.example.models.Round;

import java.util.List;

public interface Dao {

    Game startGame(Game game);

    Game createGame();

    Round guess(Round round);

    Boolean compareGuess(Game game, String userGuess);

    Boolean updateDB(Game game);

    List<Game> getAll();

    Game getById(int id);


}
