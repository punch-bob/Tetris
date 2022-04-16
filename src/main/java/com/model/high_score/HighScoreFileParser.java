package com.model.high_score;

import java.util.List;
import java.util.TreeSet;

public class HighScoreFileParser 
{
    public TreeSet<TableToken> parseFile(String filename)
    {
        TreeSet<TableToken> parsedSet = new TreeSet<>(new TableTokenComparator());
        HighScoreReader reader = new HighScoreReader();
        List<String> listOfWords = reader.readFile(filename);
        UserName userName = null;
        int score = 0;
        for (String str : listOfWords)
        {
            if (userName == null)
            {
                userName = new UserName(str);
            }
            else
            {
                score = Integer.parseInt(str);
                parsedSet.add(new TableToken(userName, score));
                userName = null;
            }
        }
        return parsedSet;
    }
}
