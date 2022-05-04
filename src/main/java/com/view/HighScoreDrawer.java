package com.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.swing.JTable;
import javax.swing.table.TableColumn;

import com.model.high_score.HighScoreTable;
import com.model.high_score.TableToken;
import com.publisher.ISubscriber;

public class HighScoreDrawer extends JTable implements ISubscriber
{
    HighScoreTable table;

    public HighScoreDrawer(HighScoreTable table)
    {
        super(11, 3);
        this.table = table;
        table.addSubscriber(this);
        this.setVisible(false);
    }

    @Override
    public void reactOnNotify() 
    {
        this.setValueAt("Top 10:", 0, 0);
        TableColumn column = this.getColumnModel().getColumn(0);
        column.setMaxWidth(50);
        this.setValueAt("User name:", 0, 1);
        this.setValueAt("Score:", 0, 2);
        
        Set<TableToken> highScoreTable = table.getHighScoreTable();
        List<TableToken> highScoreList = new ArrayList<>(highScoreTable); 
        int row = 1;
        for (int i = 0; i < chooseTotalLines(table.getTotalTableLines()); ++i)
        {
            TableToken token = highScoreList.get(i);
            this.setValueAt(row, row, 0);
            this.setValueAt(token.getUserName(), row, 1);
            this.setValueAt(token.getScore(), row, 2);
            row++;
        }
        repaint();
    }

    private static int chooseTotalLines(int tableLines)
    {
        if (tableLines < 10)
        {
            return tableLines;
        }
        return 10;
    }
}