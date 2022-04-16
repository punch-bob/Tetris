package com.model.shapes;

import java.util.Random;

public enum TetrominosEnum 
{
    I("I"),
    J("J"), 
    L("L"), 
    T("T"), 
    S("S"), 
    Z("Z"), 
    O("O");
    
    private String value;

    private TetrominosEnum(String value)
    {
        this.value = value;
    }

    public static TetrominosEnum fromString(String value)
    {
        if (value != null)
        {
            for (TetrominosEnum type : TetrominosEnum.values()) 
            {
                if (value.equalsIgnoreCase(type.value)) 
                {
                    return type;
                }
            }
        }
        throw new IllegalArgumentException("No such value");
    }

    public static TetrominosEnum getRandomType()
    {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }

}
