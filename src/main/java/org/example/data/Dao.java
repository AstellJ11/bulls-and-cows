package org.example.data;

import org.example.models.Game;
import org.example.models.Round;

import java.util.List;

public interface Dao {

    /**
     * A method that is used for the Postman interaction with the app.
     * Begins a new game, generates an answer and adds to the SQL DB.
     *
     * @param game - Empty Game object
     * @return the new game Game object
     */
    Game beginGame(Game game);

    /**
     * A method that is the local console version of the app.
     * Begins a new game, generates an answer and adds to the SQL DB.
     *
     * @return the new game Game object
     */
    Game createGame();

    /**
     * A method that takes a user guess for a specific game via an ID via Postman.
     * Temporarily adds this to the DB before accessing it and then removing it to allow repeated
     * guesses for the same game.
     *
     * @param round - Round Object containing the user submitted game_id and guess values
     * @return the guess inside the Round object
     */
    Round guess(Round round);

    /**
     * A simple method to compare the user guess to the answer.
     * Report back to the user the number of bulls & cows they have or if they are correct.
     *
     * @param game - The Game object being guessed to
     * @param userGuess - The guess from the user
     * @return true if the user guesses correctly, false otherwise
     */
    Boolean compareGuess(Game game, String userGuess);

    /**
     * A very simple method to update the DB for new Game values.
     *
     * @param game - The Game object to be updated too
     * @return true if successful
     */
    Boolean updateDB(Game game);

    /**
     * A simple method to retrieve every game from the DB.
     *
     * @return an array list of all the games in the DB
     */
    List<Game> getAll();

    /**
     * A simple method to retrieve a specific game from the DB based on id.
     *
     * @param id - id of the game
     * @return the specific Game object for that game
     */
    Game getById(int id);

    /**
     * A simple method to delete a specific game from the DB based on id.
     *
     * @param id - id of the game
     * @return true if successful
     */
    Boolean deleteRoomById(int id);


}
