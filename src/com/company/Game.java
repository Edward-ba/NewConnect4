package com.company;

public class Game {
    Board board = new Board();
    Player p1;
    Player p2;

    Game(Player p1, Player p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public void play() {
        Player curPlayer = p1;
        // state the rules and what this is
        System.out.println("1. This is connect 4.");
        System.out.println("2. Player 1 goes first.");
        System.out.println("4. Player 1 is, O Player 2 is @");

        // create new coordinates which is used for checking if somebody wins
        Coordinate coordinate;
        // print the board so the players know what the board looks like
        board.printBoard();
        do {
            // take the coordinates of where the current player moved
            coordinate = curPlayer.move(board, curPlayer);
            curPlayer = (curPlayer == p1) ? p2 : p1; // swap between players
            board.printBoard(); // print the board so the players know what it looks like
            // check if somebody won
        } while (!board.checkWinner(coordinate));
    }
}
