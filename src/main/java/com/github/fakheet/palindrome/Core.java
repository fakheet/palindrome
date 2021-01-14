package com.github.fakheet.palindrome;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class Core {
    // hashmap containing player objects, uses player name as a key
    private HashMap<String, Player> players;
    public List<Player> leaderboards;

    public Core() {
        players = new HashMap<>();
        leaderboards = new ArrayList<>();
    }

    // adds a new player to the game
    public void addPlayer(Player player) {
        this.players.put(player.getName(), player);
    }

    public void takeInput(String input, String playerName) throws Exception {
        // first check if the phrase is a palindrome
        if (isPalindrome(input)) {
            Player player = getPlayer(playerName);
            // then if the player hasn't used this phrase, add it to their history and give them points equal to length
            // of the phrase, then update the leaderboards
            if (!player.wasInputUsed(input)) {
                player.addScore(input.length());
                player.addInput(input);
                updateLeaderboards();
            }
        }
    }

    public static boolean isPalindrome(String string) {
        // convert phrase to lowercase and strip of whitespace
        string = string.toLowerCase();
        string = string.replaceAll("\\s+", "");

        // if the string isn't empty, the algorithm starts to check oppositely located characters,
        // converging at the middle. If at least one pair doesn't match, the phrase isn't a palindrome.
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
        // create an array out of `players` hashmap values and sort that array by the score field, reversed
        ArrayList<Player> newLeaderboards = new ArrayList<>(players.values());
        newLeaderboards.sort(Comparator.comparing(Player::getScore).reversed());
        // cut the resulting array list to the first five or less elements and reassign to the leaderboards field
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