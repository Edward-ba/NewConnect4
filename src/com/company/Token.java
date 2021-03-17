package com.company;

public class Token {
    Board board;
    Player.PlayerNumber type;

    public Token(Board board, Player.PlayerNumber type)
    {
        this.board = board;
        this.type = type;
    }

    public String print()
    {
        if (this.type == Player.PlayerNumber.Player1)
            return "O";
        else
            return "@";
    }

    public Player.PlayerNumber getPlayerNumber()
    {
        return this.type;
    }

}
