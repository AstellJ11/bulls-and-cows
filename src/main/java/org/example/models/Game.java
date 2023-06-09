package org.example.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Game {

    private int id;
    private int numberOfGuesses;
    private String answer;
    private Boolean isWon;

    public Game() {
    }

    public Game(int id, int numberOfGuesses, String answer, Boolean isWon) {
        this.id = id;
        this.numberOfGuesses = numberOfGuesses;
        this.answer = answer;
        this.isWon = isWon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumberOfGuesses() {
        return numberOfGuesses;
    }

    public void setNumberOfGuesses(int numberOfGuesses) {
        this.numberOfGuesses = numberOfGuesses;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Boolean getWon() {
        return isWon;
    }

    public void setWon(Boolean won) {
        isWon = won;
    }

    @Override
    public String toString() {
        return "Game{" +
                "gameID=" + id +
                ", numberOfGuesses=" + numberOfGuesses +
                ", answer='" + answer + '\'' +
                ", isWon=" + isWon +
                '}';
    }
}
