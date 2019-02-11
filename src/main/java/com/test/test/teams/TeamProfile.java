package com.test.test.teams;

import com.test.test.clubs.ClubProfile;
import org.springframework.jdbc.core.JdbcTemplate;

public class TeamProfile {

    private Integer teamId;
    private Integer points;



    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {

        this.teamId = teamId;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }
}
