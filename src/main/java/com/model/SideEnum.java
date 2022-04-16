package com.model;

public enum SideEnum 
{
    L("LEFT"),
    R("RIGHT");

    private String value;

    private SideEnum(String value)
    {
        this.value = value;
    }

    public static SideEnum fromString(String value)
    {
        if (value != null)
        {
            for (SideEnum type : SideEnum.values()) 
            {
                if (value.equalsIgnoreCase(type.value)) 
                {
                    return type;
                }
            }
        }
        throw new IllegalArgumentException("No such value");
    }
}
