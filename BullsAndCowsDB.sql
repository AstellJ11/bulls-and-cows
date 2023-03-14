-- Code written by James Astell

DROP DATABASE IF EXISTS BullsAndCowsDB;
CREATE DATABASE BullsAndCowsDB;
USE BullsAndCowsDB;

CREATE TABLE Game (
	game_id INT PRIMARY KEY AUTO_INCREMENT,
    /*startedTime DATETIME,*/
    numberOfGuesses INT,
    answer VARCHAR(4),
    isWon BOOLEAN
);

INSERT INTO Game (startedTime, numberofGuesses, answer, isWon)
    VALUES 
    ('2023-03-06', '4', '1234', 1),
    ('2023-03-07', '7', '4321', 0),
    ('2023-03-08', '1', '7894', 0);