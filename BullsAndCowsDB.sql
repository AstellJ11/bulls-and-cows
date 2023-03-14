-- Code written by James Astell

DROP DATABASE IF EXISTS BullsAndCows;
CREATE DATABASE BullsAndCows;
USE BullsAndCows;

CREATE TABLE Game (
	game_id INT PRIMARY KEY AUTO_INCREMENT,
    startedTime DATETIME,
    numberOfGuesses INT,
    answer VARCHAR(4),
    isWon BOOLEAN
);