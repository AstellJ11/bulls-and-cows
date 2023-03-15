package org.example.controllers;


import org.example.data.Dao;
import org.example.models.Game;
import org.example.view.View;
import org.springframework.boot.SpringApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
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

    public void runProgram() throws SQLException {
        boolean keepGoing = true;
        int menuSelection = 0;

        while (keepGoing) {
            menuSelection = view.displayMenuGetSelection();

            switch (menuSelection) {
                case 1:
                    // REPLACE
                    break;
                case 2:
                    displayAll();
                    break;
                case 3:
                    // REPLACE
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
    
    @GetMapping
    public List<Game> all() {
        return dao.getAll();
    }

    public void displayAll() throws SQLException {
        List<Game> gameList = dao.displayAll();
        view.displayAll(gameList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Game> getById(@PathVariable int id) {
        Game result = dao.getById(id);
        if (result == null) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(result);
    }


}
