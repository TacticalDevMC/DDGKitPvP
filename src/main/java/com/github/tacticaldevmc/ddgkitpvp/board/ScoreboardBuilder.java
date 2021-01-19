package com.github.tacticaldevmc.ddgkitpvp.board;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

import static com.github.tacticaldevmc.ddgkitpvp.DDGKitpvp.replaceColor;

public class ScoreboardBuilder {
    private Scoreboard board;
    private String name;
    private HashMap<Integer, String> lineID = new HashMap();
    private String blank = "";
    private int slot;

    public ScoreboardBuilder(String name, int lines) {
        this.slot = lines;
        Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = board.registerNewObjective("Scoreboard", "dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(replaceColor(name));
        this.name = name;
        this.board = board;
    }

    public void addLine(String line) {
        Score score = this.board.getObjective(DisplaySlot.SIDEBAR).getScore(line);
        score.setScore(this.slot);
        this.lineID.put(this.slot, line);
        --this.slot;
    }

    public void addBlankLine() {
        Score score = this.board.getObjective(DisplaySlot.SIDEBAR).getScore(this.blank);
        score.setScore(this.slot);
        this.lineID.put(this.slot, this.blank);
        this.blank = this.blank + " ";
        --this.slot;
    }

    public void setName(String newName) {
        this.board.getObjective(DisplaySlot.SIDEBAR).setDisplayName(replaceColor(newName));
    }

    public Scoreboard getBoard() {
        return this.board;
    }

    public String getName() {
        return this.name;
    }
}
