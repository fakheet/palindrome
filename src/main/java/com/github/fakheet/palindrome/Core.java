package com.github.fakheet.palindrome;

import java.util.*;

public class Core {
    private HashMap<String, Player> players;
    public List<Player> leaderboards;

    public Core() {
        players = new HashMap<>();
        leaderboards = new ArrayList<>();
    }

    public void addPlayer(Player player) {
        this.players.put(player.getName(), player);
    }

    public void takeInput(String input, String playerName) throws Exception {
        if (isPalindrome(input)) {
            Player player = getPlayer(playerName);
            if (!player.wasInputUsed(input)) {
                player.addScore(input.length());
                player.addInput(input);
                updateLeaderboards();
            }
        }
    }

    public static boolean isPalindrome(String string) {
        string = string.toLowerCase();
        string = string.replaceAll("\\s+", "");

        if (string.length() != 0) {
            int last = string.length() - 1;
            for (int i = 0; i < string.length() / 2; i++) {
                if (string.charAt(i) != string.charAt(last - i)) {
                    return false;
                }
            }
        }
        return true;
    }

    private void updateLeaderboards() {
        ArrayList<Player> newLeaderboards = new ArrayList<>(players.values());
        newLeaderboards.sort(Comparator.comparing(Player::getScore).reversed());

        int cut = 5;
        if (newLeaderboards.size() < 5) {
            cut = newLeaderboards.size();
        }
        leaderboards = newLeaderboards.subList(0, cut);
    }

    public List<Player> getLeaderboards() {
        updateLeaderboards();
        return leaderboards;
    }

    public void printLeaderboards() {
        updateLeaderboards();
        System.out.println("Top 5 players:");
        for (Player p : leaderboards) {
            System.out.println(p.getName() + ": " + p.getScore());
        }
    }

    public HashMap<String, Player> getPlayers() {
        return players;
    }

    public Player getPlayer(String name) throws Exception {
        if (!players.containsKey(name)) {
            throw new Exception("Player doesn't exist");
        }
        return players.get(name);
    }
}