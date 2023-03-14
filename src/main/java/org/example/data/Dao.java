package org.example.data;

import org.example.models.Game;

import java.util.List;

public interface Dao {

    List<Game> getAll();

    Game getById(int id);


}
