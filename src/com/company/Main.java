package com.company;

public class Main {

    public static void main(String[] args) {
        // create the new players and game
        Player player1 = new Player(Player.PlayerNumber.Player1);
        Player player2 = new Player(Player.PlayerNumber.Player2);
        Game game = new Game(player1, player2);
        // start the game
        game.play();
    }
}
