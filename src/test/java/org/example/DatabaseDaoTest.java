package org.example;

import junit.framework.TestCase;
import org.example.data.Dao;
import org.example.models.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
@Commit
public class DatabaseDaoTest extends TestCase {

    @Autowired
    Dao dao;

    @BeforeEach
    public void setUp() {
        List<Game> games = dao.getAll();
        for(Game game : games) {
            dao.deleteRoomById(game.getId());
        }
    }

    @Test
    public void testCreateNewGame() {
        Game game = new Game();
        game = dao.createGame();  // Call the createGame method to make a new game

        Game fromDao = dao.getById(game.getId());  // Call the new game from the DB

        assertEquals(game, fromDao);  // Check if they are the same
    }


}