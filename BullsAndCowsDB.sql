-- Code written by James Astell

DROP DATABASE IF EXISTS BullsAndCowsDB;
CREATE DATABASE BullsAndCowsDB;
USE BullsAndCowsDB;

CREATE TABLE Game (
	game_id INT PRIMARY KEY AUTO_INCREMENT,
    numberOfGuesses INT,
    answer VARCHAR(4),
    isWon BOOLEAN
);

INSERT INTO Game (numberofGuesses, answer, isWon)
    VALUES 
    ('4', '1234', 1),
    ('10', '4321', 0),
    ('8', '7894', 1);