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

        System.out.println("1. This is connect 4.");
        System.out.println("2. Player 1 goes first.");
        System.out.println("4. Player 1 is, O Player 2 is @");

        Coordinate coordinate;
        board.printBoard();
        do {
            coordinate = curPlayer.move(board, curPlayer);
            curPlayer = (curPlayer == p1) ? p2 : p1; // swap between players
            board.printBoard();
        } while (!board.checkWinner(coordinate));
    }
}
