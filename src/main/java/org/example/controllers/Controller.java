package org.example.controllers;


import org.example.data.Dao;
import org.example.models.Game;
import org.example.models.Round;
import org.example.view.View;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/game")
@Component
public class Controller {

    private final Dao dao;
    private final View view;

    public Controller(Dao dao, View view) {
        this.dao = dao;
        this.view = view;
    }

    public void runProgram() {
        boolean keepGoing = true;
        int menuSelection = 0;

        while (keepGoing) {
            menuSelection = view.displayMenuGetSelection();

            switch (menuSelection) {
                case 1:
                    playGame();
                    break;
                case 2:
                    displayAll();
                    break;
                case 3:
                    displayById();
                    break;
                case 4:
                    keepGoing = false;
                    break;
                default:
                    view.displayUnknownCommandBanner();
            }
        }
        view.displayExitBanner();
    }



    /* ------------------------------------ Start a new game ------------------------------------- */

    // POST ("begin")
    @PostMapping("/begin")
    @ResponseStatus(HttpStatus.CREATED)
    public Game startGame(Game game) {
        return dao.startGame(game);
    }


    /* ------------------------------------ Playing the game ------------------------------------- */

    public void playGame() {
        Boolean isWon = false;
        int roundNumber = 0;

        Game game = dao.createGame();  // Create a brand-new game

        do {
            roundNumber++;
            String userGuess = view.getUserGuess(roundNumber);  // Gather the users guess
            isWon = dao.compareGuess(game, userGuess);
        } while (!isWon && roundNumber < 10);

        if (isWon) {
            view.displayUserWon(roundNumber);
            game.setNumberOfGuesses(roundNumber);
            game.setWon(true);
        } else {
            view.displayUserLost();
            game.setNumberOfGuesses(roundNumber);
            game.setWon(false);
        }

        dao.updateDB(game);  // Return the result of the game to the DB
    }


    /* -------------------------------------- Make a guess --------------------------------------- */

    // POST ("guess")
    @PostMapping("/guess")
    @ResponseStatus(HttpStatus.CREATED)
    public Round guess(@RequestBody Round round) {
        view.displayGuessPostman();
        return dao.guess(round);
    }


    /* ------------------------------------ Return all games ------------------------------------- */

    // GET ("game")
    @GetMapping
    public List<Game> getAll() {
        return dao.getAll();
    }

    public void displayAll() {
        List<Game> gameList = dao.getAll();
        view.displayAll(gameList);
    }


    /* ----------------------------------- Return game by ID ------------------------------------- */

    // GET ("game/id")
    @GetMapping("/{id}")
    public ResponseEntity<Game> getById(@PathVariable int id) {
        Game result = dao.getById(id);
        if (result == null) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(result);
    }

    public void displayById() {
        int id = view.getGameIdChoice();
        Game gameList = dao.getById(id);
        view.displayById(gameList);
    }


}
