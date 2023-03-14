package org.example.controllers;


import org.example.data.Dao;
import org.example.models.Game;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/game")
public class Controller {

    private final Dao dao;

    public Controller(Dao dao) {
        this.dao = dao;
    }

    @GetMapping
    public List<Game> all() {
        return dao.getAll();
    }


}
