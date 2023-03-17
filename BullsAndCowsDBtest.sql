-- Code written by James Astell

DROP DATABASE IF EXISTS BullsAndCowsDBtest;
CREATE DATABASE BullsAndCowsDBtest;
USE BullsAndCowsDBtest;

CREATE TABLE Game (
	game_id INT PRIMARY KEY AUTO_INCREMENT,
    numberOfGuesses INT,
    answer VARCHAR(4),
    isWon BOOLEAN
);

CREATE TABLE Guess (
	game_id INT PRIMARY KEY AUTO_INCREMENT,
    guess VARCHAR(4)
);