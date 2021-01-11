package com.github.fakheet.palindrome;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestCore {

    @Test
    public void testAddPlayer() throws Exception {
        List<Player> expectedPlayers = Arrays.asList(
                new Player("Jack"),
                new Player("Victor"),
                new Player("John")
        );

        Core game = new Core();
        game.addPlayer(new Player("Jack"));
        game.addPlayer(new Player("Victor"));
        game.addPlayer(new Player("John"));

        for (Player p: expectedPlayers) {
            assertEquals(p.getName(), game.getPlayer(p.getName()).getName());
        }
    }

    @Test
    public void testTakeInput() throws Exception {
        Core game = new Core();
        game.addPlayer(new Player("Jack"));

        game.takeInput("topot", "Jack");
        assertEquals(game.getPlayer("Jack").getScore(), 5);

        game.takeInput("kompot", "Jack");
        assertEquals(game.getPlayer("Jack").getScore(), 5);

        game.takeInput("topot", "Jack");
        assertEquals(game.getPlayer("Jack").getScore(), 5);

        game.takeInput("А роза упала на лапу Азора", "Jack");
        assertEquals(game.getPlayer("Jack").getScore(), 31);
    }

    @Test
    public void testGetLeaderboards() throws Exception {
        Core game = new Core();

        game.addPlayer(new Player("Jack"));
        game.addPlayer(new Player("Victor"));
        game.addPlayer(new Player("John"));

        game.takeInput("Дом мод", "Jack");
        game.takeInput("Город дорог", "Victor");
        game.takeInput("На доме чемодан", "John");

        List<Player> leaderboards = game.getLeaderboards();

        assertEquals(leaderboards.get(0).getName(), "John");
        assertEquals(leaderboards.get(1).getName(), "Victor");
        assertEquals(leaderboards.get(2).getName(), "Jack");

        game.addPlayer(new Player("Rachel"));
        game.addPlayer(new Player("Jane"));
        game.addPlayer(new Player("Mike"));

        game.takeInput("Ешь немытого ты меньше", "Rachel");
        game.takeInput("Олесе весело", "Jane");
        game.takeInput("У лип Лёша нашел пилу", "Mike");

        assertEquals(game.leaderboards.get(0).getName(), "Rachel");
        assertEquals(game.leaderboards.get(1).getName(), "John");
        assertEquals(game.leaderboards.get(2).getName(), "Jane");
        assertEquals(game.leaderboards.get(3).getName(), "Victor");
        assertEquals(game.leaderboards.get(4).getName(), "Jack");

        game.printLeaderboards();
    }
}
