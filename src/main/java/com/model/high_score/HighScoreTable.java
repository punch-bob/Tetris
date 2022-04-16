package com.model.high_score;

import java.util.TreeSet;

import com.publisher.Publisher;

public class HighScoreTable extends Publisher
{
    TreeSet<TableToken> highScoreTable;
    String filename;

    public HighScoreTable(String filename)
    {
        this.filename = filename;
        HighScoreFileParser parser = new HighScoreFileParser();
        highScoreTable = parser.parseFile(filename);
    }

    public TreeSet<TableToken> getHighScoreTable() 
    {
        return highScoreTable;
    }

    public void updateHighScoreTable()
    {
        publishNotify();
    }

    public int getTotalTableLines()
    {
        return highScoreTable.size();
    }

    public void addNewScore(TableToken token)
    {
        highScoreTable.add(token);
        HighScoreWriter writer = new HighScoreWriter();
        writer.writeFile(filename, highScoreTable);
        publishNotify();
    }
}
