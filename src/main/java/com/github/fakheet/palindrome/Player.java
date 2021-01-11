package com.github.fakheet.palindrome;

import java.util.ArrayList;
import java.util.Objects;

public class Player {
    private final String name;
    private int score = 0;
    private ArrayList<String> usedInputs = new ArrayList<>();

    public Player(String name) {
        this.name = name;
    }

    public boolean wasInputUsed(String input) {
        return usedInputs.contains(input);
    }

    public void addScore(int score) {
        this.score += score;
    }

    public void addInput(String input) {
        usedInputs.add(input);
    }

    public String getName() {
        return name;
    }

    public Integer getScore() {
        return score;
    }

    public ArrayList<String> getUsedInputs() {
        return usedInputs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player)) return false;
        Player player = (Player) o;
        return name.equals(player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", score=" + score +
                ", usedInputs=" + usedInputs +
                '}';
    }
}
