package com.company;

import java.util.Scanner;

public class Player {
    private final PlayerNumber playerNumber;
    Scanner scanner = new Scanner(System.in);

    int readInt() {
        System.out.println("put in the col that you want your piece to go into: ");
        int ret;
        try {
            String string = scanner.nextLine();
            ret = Integer.parseInt(string);
            return ret;
        } catch (Exception e) {
            return -1;
        }
    }

    public enum PlayerNumber {
        Player1,
        Player2,
    }

    public PlayerNumber getPlayerNumber() {
        return playerNumber;
    }

    public Player(PlayerNumber pt) {
        this.playerNumber = pt;
    }

    public Coordinate move(Board board, Player player) {
        int col = readInt();
        Coordinate coordinate = board.move(col, player.playerNumber);
        if (!coordinate.check) {
            System.out.println("that doesn't work try again");
            player.move(board, player);
        }
        return coordinate;
    }
}
