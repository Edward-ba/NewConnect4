package com.company;

public class Token {
    Board board;
    Player.PlayerNumber type;

    // constructor for the token
    public Token(Board board, Player.PlayerNumber type)
    {
        this.board = board;
        this.type = type;
    }

    // two type of tokens to print O and @
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
