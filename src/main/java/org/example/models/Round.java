package org.example.models;

public class Round {

    private int id;
    private String guess;

    public Round() {
    }

    public Round(int id, String guess) {
        this.id = id;
        this.guess = guess;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGuess() {
        return guess;
    }

    public void setGuess(String guess) {
        this.guess = guess;
    }

    @Override
    public String toString() {
        return "Round{" +
                "id=" + id +
                ", guess='" + guess + '\'' +
                '}';
    }
}
