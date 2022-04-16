package com.model.high_score;

import java.util.Comparator;

public class TableTokenComparator implements Comparator<TableToken>
{
    @Override
    public int compare(TableToken o1, TableToken o2) 
    {
        if (o1.getScore() == o2.getScore())
        {
            return o2.getUserName().compareTo(o1.getUserName());
        }
        return o2.getScore() - o1.getScore();
    }
}
