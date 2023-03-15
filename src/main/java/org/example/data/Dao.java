package org.example.data;

import org.example.models.Game;

import java.sql.SQLException;
import java.util.List;

public interface Dao {

    List<Game> getAll();

    List<Game> displayAll() throws SQLException;

    Game getById(int id);


}
