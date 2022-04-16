package com.model.high_score;

import java.io.*;
import java.util.*;

public class HighScoreWriter 
{
    private String makeTableRaw(TableToken token)
    {
        StringBuilder sb = new StringBuilder();
        sb.append(token.getUserName());
        sb.append(" ");
        sb.append(token.getScore());
        sb.append("\n");
        return sb.toString();
    }   

    public void writeFile(String filename, Set<TableToken> highScoreTable)
    {
        Writer out = null;
        try {
            out = new OutputStreamWriter(new FileOutputStream(filename));
            
            for (TableToken token: highScoreTable)
            {
                out.write(makeTableRaw(token));
            }
        }
        catch (IOException e) 
        {
            System.err.println("Error while writing file: " + e.getLocalizedMessage());
        }
        finally 
        {
            if (out != null) 
            {
                try {
                    out.close();
                }
                catch (IOException e) 
                {
                    e.printStackTrace(System.err);
                }
            }
        }
    }
}
