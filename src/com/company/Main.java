package com.company;

public class Main {

    public static void main(String[] args) {
        Player player1 = new Player(Player.PlayerNumber.Player1);
        Player player2 = new Player(Player.PlayerNumber.Player2);
        Game game = new Game(player1, player2);
        game.play();
    }
}
