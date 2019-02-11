package com.test.test.matches;

import java.util.Date;

public class MatchProfile {

    private Integer matchId;
    private String matchScore;
    private String setsScore;
    private Date matchDate;
    private Integer matchRound;
    private String teamAName;
    private String teamATown;
    private String teamBName;
    private String teamBTown;

    public Integer getMatchRound() {
        return matchRound;
    }

    public void setMatchRound(Integer matchRound) {
        this.matchRound = matchRound;
    }



    public Integer getMatchId() {
        return matchId;
    }

    public void setMatchId(Integer matchId) {
        this.matchId = matchId;
    }

    public String getMatchScore() {
        return matchScore;
    }

    public void setMatchScore(String matchScore) {
        this.matchScore = matchScore;
    }

    public String getSetsScore() {
        return setsScore;
    }

    public void setSetsScore(String setsScore) {
        this.setsScore = setsScore;
    }

    public Date getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(Date matchDate) {
        this.matchDate = matchDate;
    }

    public String getTeamAName() {
        return teamAName;
    }

    public void setTeamAName(String teamAName) {
        this.teamAName = teamAName;
    }

    public String getTeamATown() {
        return teamATown;
    }

    public void setTeamATown(String teamATown) {
        this.teamATown = teamATown;
    }

    public String getTeamBName() {
        return teamBName;
    }

    public void setTeamBName(String teamBName) {
        this.teamBName = teamBName;
    }

    public String getTeamBTown() {
        return teamBTown;
    }

    public void setTeamBTown(String teamBTown) {
        this.teamBTown = teamBTown;
    }
}
