package com.assignment.constants;

import com.assignment.models.MyTableColumn;

public class LeaderboardConfig {
    public  static  final  MyTableColumn[] columns = new MyTableColumn[]{
            new MyTableColumn("Rank", "playerRank"),
            new MyTableColumn("Name", "playerName"),
            new MyTableColumn("Score", "playerScore")
    };
}