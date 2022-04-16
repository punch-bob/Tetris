package com.model.high_score;

public class UserName
{
    private String value;

    public UserName(String value)
    {
        if (!value.chars().allMatch(Character::isJavaIdentifierPart))
        {
            throw new IllegalArgumentException("Incorrect name!");
        }
        this.value = value;
    }

    public String getValue()
    {
        return value;
    }
}
