package com.model.high_score;

public class TableToken
{
    private UserName userName;
    private int score;

    public TableToken(UserName userName, int score)
    {
        this.userName = userName;
        this.score = score;
    }

    public String getUserName()
    {
        return userName.getValue();
    }

    public int getScore()
    {
        return score;
    }
}
