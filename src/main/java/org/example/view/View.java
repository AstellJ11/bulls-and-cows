package org.example.view;

import org.example.models.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class View {

    private UserIO io;

    @Autowired
    public View(UserIO io) {
        this.io = io;
    }


    /* ------------------------------------------ Misc ------------------------------------------- */

    public int displayMenuGetSelection() {
        System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
        System.out.println("Application started! Game can now be interacted with using Postman!\n");
        System.out.println("* << Bulls and Cows Game >>");
        System.out.println("* 1. Start new game");
        System.out.println("* 2. List all games previously played");
        System.out.println("* 3. Find previous game by ID");
        System.out.println("* 4. Quit");
        System.out.println("*");
        System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");

        return io.readInt("Please select from the above choices:");
    }

    public void displayExitBanner() {
        System.out.println("Quitting.... Goodbye!");
    }

    public void displayUnknownCommandBanner() {
        System.out.println("Invalid command. Please try again.");
    }


    /* --------------------------------------- Display All --------------------------------------- */

    public void displayAll(List<Game> gameList) {
        for (Game currentGame : gameList) {
            String gameInfo = "\nGame ID: " + currentGame.getId()
                    + "\nNumber of Guesses: " + currentGame.getNumberOfGuesses()
                    + "\nCorrect answer: " + currentGame.getAnswer()
                    + "\nGame won? " + currentGame.getWon()
                    + "\n* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *";

            System.out.println(gameInfo);
        }
        io.readString("Please press enter to continue.");
    }


    /* -------------------------------------- Display by ID -------------------------------------- */

    public int getGameIdChoice() {
        return io.readInt("Please enter a valid game ID value:");
    }

    public void displayById(Game game) {
        //Game game = new Game();

        String gameInfo = "\nGame ID: " + game.getId()
                + "\nNumber of Guesses: " + game.getNumberOfGuesses()
                + "\nCorrect answer: " + game.getAnswer()
                + "\nGame won? " + game.getWon()
                + "\n* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *";

        System.out.println(gameInfo);

        io.readString("Please press enter to continue.");
    }


}
