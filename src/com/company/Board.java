package com.company;

public class Board {
    final static int numOfRows = 6;
    final static int numOfCols = 7;
    Token[][] grid = new Token[numOfRows][numOfCols];
    int[] nxtAvailRow = new int[numOfCols];

    Board() {
        resetBoard();
    }

    public void resetBoard() {
        for (int i = 0; i < numOfRows; ++i) {
            for (int j = 0; j < numOfCols; ++j) {
                grid[i][j] = null;
                nxtAvailRow[j] = numOfRows - 1;
            }
        }
    }

    // take the column that the player wants to move to and the player's number
    public Coordinate move (int col, Player.PlayerNumber playerNumber) {
        Coordinate coordinate = new Coordinate();
        // check if you can make this move otherwise return something that won't work
        if (col >= 0 && col < numOfCols && nxtAvailRow[col] >= 0) {
            grid[nxtAvailRow[col]][col] = new Token(this, playerNumber);
            coordinate.c = col;
            coordinate.r = nxtAvailRow[col];
            coordinate.check = true;
            nxtAvailRow[col] -= 1;
            return coordinate;
        }
        coordinate.check = false;
        return coordinate;
    }

    // print the board
    public void printBoard() {
        for (int i = 0; i < numOfCols; ++i)
            System.out.print("  " + i + " ");
        System.out.println();
        System.out.print("  -");
        for (int i = 0; i < numOfCols * 2 - 1; ++i)
            System.out.print(" -");
        System.out.println();
        for (int i = 0; i < numOfRows; ++i) {
            System.out.print("| ");
            for (int j = 0; j < numOfCols; ++j)
            {
                if (grid[i][j] != null)
                    System.out.print(grid[i][j].print() + " | ");
                else
                    System.out.print("  | ");
            }
            System.out.println();
            if (i != numOfRows - 1)
                System.out.print("|");
            else
                System.out.print("-");
            for (int j = 0; j < numOfCols; ++j) {
                System.out.print(" - ");
                if (i == numOfRows - 1)
                    System.out.print("-");
                else if (j == numOfCols - 1)
                    System.out.print("|");
                else
                    System.out.print("+");
            }
            System.out.println();
        }
    }

    // check if somebody won vertically then return 1 if player 1 won, 2 if player 2 won, and 0 if nobody won
    public int checkWinnerVertical(Coordinate coordinate) {
        int p1PiecesInARow = 0;
        int p2PiecesInARow = 0;
        int maxP1PiecesInARow = 0;
        int maxP2PiecesInARow = 0;

        for (int i = coordinate.r; i < Math.min(numOfRows, coordinate.r + 4); ++i) {
            if (this.grid[i][coordinate.c].getPlayerNumber() == Player.PlayerNumber.Player1) {
                p1PiecesInARow += 1;
                p2PiecesInARow = 0;
                if (p1PiecesInARow > maxP1PiecesInARow)
                    maxP1PiecesInARow = p1PiecesInARow;
            }
            else if (this.grid[i][coordinate.c].getPlayerNumber() == Player.PlayerNumber.Player2) {
                p2PiecesInARow += 1;
                p1PiecesInARow = 0;
                if (p2PiecesInARow > maxP2PiecesInARow)
                    maxP2PiecesInARow = p2PiecesInARow;
            }
            else {
                p1PiecesInARow = 0;
                p2PiecesInARow = 0;
            }
        }

        // initialize the variable to return
        int ret;

        if (maxP1PiecesInARow >= 4)
            ret = 1;
        else if (maxP2PiecesInARow >= 4)
            ret = 2;
        else
            ret = 0;

        return ret;
    }

    public boolean checkWinner(Coordinate coordinate) {

        int vertical = checkWinnerVertical(coordinate);

        int p1PiecesInARow = 0;
        int p2PiecesInARow = 0;
        int maxP1PiecesInARow = 0;
        int maxP2PiecesInARow = 0;

        for (int i = coordinate.r; i < Math.min(numOfRows, coordinate.r + 4); ++i) {
            if (this.grid[i][coordinate.c].getPlayerNumber() == Player.PlayerNumber.Player1) {
                p1PiecesInARow += 1;
                p2PiecesInARow = 0;
                if (p1PiecesInARow > maxP1PiecesInARow)
                    maxP1PiecesInARow = p1PiecesInARow;
            }
            else if (this.grid[i][coordinate.c].getPlayerNumber() == Player.PlayerNumber.Player2) {
                p2PiecesInARow += 1;
                p1PiecesInARow = 0;
                if (p2PiecesInARow > maxP2PiecesInARow)
                    maxP2PiecesInARow = p2PiecesInARow;
            }
            else {
                p1PiecesInARow = 0;
                p2PiecesInARow = 0;
            }
        }

        p1PiecesInARow = 0;
        p2PiecesInARow = 0;

        for (int i = Math.max(coordinate.c - 4, 0); i < Math.min(coordinate.c + 4, numOfCols); ++i) {
            if (null == grid[coordinate.r][i]) {
                p1PiecesInARow = 0;
                p2PiecesInARow = 0;
            }
            else if (this.grid[coordinate.r][i].getPlayerNumber() == Player.PlayerNumber.Player1) {
                p1PiecesInARow += 1;
                p2PiecesInARow = 0;
                if (p1PiecesInARow > maxP1PiecesInARow)
                    maxP1PiecesInARow = p1PiecesInARow;
            }
            else if (this.grid[coordinate.r][i].getPlayerNumber() == Player.PlayerNumber.Player2) {
                p2PiecesInARow += 1;
                p1PiecesInARow = 0;
                if (p2PiecesInARow > maxP2PiecesInARow)
                    maxP2PiecesInARow = p2PiecesInARow;
            }
        }

        Coordinate start = new Coordinate();
        for (int i = 0; i < 5; ++i) {
            if (coordinate.r - (4 - i) >= 0 && coordinate.c - (4 - i) >= 0) {
                start.r = coordinate.r - (4 - i);
                start.c = coordinate.c - (4 - i);
                break;
            }
        }

        Coordinate end = new Coordinate();
        for (int i = 0; i < 5; ++i) {
            if (coordinate.r + (4 - i) < numOfRows && coordinate.c + (4 - i) < numOfCols) {
                end.r = coordinate.r + (4 - i);
                end.c = coordinate.c + (4 - i);
                break;
            }
        }

        while (start.r < end.r && start.c < end.c) {
            if (null == grid[start.r][start.c]) {
                p1PiecesInARow = 0;
                p2PiecesInARow = 0;
            }
            else if (this.grid[start.r][start.c].getPlayerNumber() == Player.PlayerNumber.Player1) {
                p1PiecesInARow += 1;
                p2PiecesInARow = 0;
                if (p1PiecesInARow > maxP1PiecesInARow)
                    maxP1PiecesInARow = p1PiecesInARow;
            }
            else if (this.grid[start.r][start.c].getPlayerNumber() == Player.PlayerNumber.Player2) {
                p2PiecesInARow += 1;
                p1PiecesInARow = 0;
                if (p2PiecesInARow > maxP2PiecesInARow)
                    maxP2PiecesInARow = p2PiecesInARow;
            }
            start.r += 1;
            start.c += 1;
        }

        for (int i = 0; i < 5; ++i) {
            if (coordinate.r - (4 - i) >= 0 && coordinate.c + (4 - i) < numOfCols) {
                start.r = coordinate.r - (4 - i);
                start.c = coordinate.c + (4 - i);
                break;
            }
        }

        for (int i = 0; i < 5; ++i) {
            if (coordinate.r + (4 - i) < numOfRows && coordinate.c - (4 - i) >= 0) {
                end.r = coordinate.r + (4 - i);
                end.c = coordinate.c - (4 - i);
                break;
            }
        }

        while (start.r < end.r && start.c < end.c) {
            if (null == grid[start.r][start.c]) {
                p1PiecesInARow = 0;
                p2PiecesInARow = 0;
            }
            else if (this.grid[start.r][start.c].getPlayerNumber() == Player.PlayerNumber.Player1) {
                p1PiecesInARow += 1;
                p2PiecesInARow = 0;
                if (p1PiecesInARow > maxP1PiecesInARow)
                    maxP1PiecesInARow = p1PiecesInARow;
            }
            else if (this.grid[start.r][start.c].getPlayerNumber() == Player.PlayerNumber.Player2) {
                p2PiecesInARow += 1;
                p1PiecesInARow = 0;
                if (p2PiecesInARow > maxP2PiecesInARow)
                    maxP2PiecesInARow = p2PiecesInARow;
            }
            start.r += 1;
            start.c -= 1;
        }

        if (maxP1PiecesInARow >= 4) {
            System.out.println("Player 1 wins");
            return true;
        }
        else if (maxP2PiecesInARow >= 4) {
            System.out.println("Player 2 wins");
            return true;
        }
        return false;
    }
}
