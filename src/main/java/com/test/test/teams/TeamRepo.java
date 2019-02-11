package com.test.test.teams;

import com.test.test.clubs.ClubProfile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class TeamRepo {


    final JdbcTemplate jdbcTemplate;

    final String GET_CLUB_PROFILE_BYTEAM_ID="SELECT k.klub_id,k.nazwa,k.rok_zalozenia,k.rok_zakonczenia,k.adres_kod,k.adres_miasto,k.adres_ulica\n" +
            "FROM kluby as k\n" +
            "left join kluby_druzyna_prof kdp on k.klub_id = kdp.klub_id\n" +
            "where kdp.druzyna_id = ?";

    public TeamRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public ClubProfile getClubProfileByTeamId(final long teamId)
    {
        return jdbcTemplate.query(GET_CLUB_PROFILE_BYTEAM_ID,new String[]{String.valueOf(teamId)},this::extractData);
    }

    ClubProfile extractData(ResultSet rs) throws SQLException {

        ClubProfile club = new ClubProfile();
        while(rs.next()){

            club.setClubId(rs.getInt("klub_id"));
            club.setClubName(rs.getString("nazwa"));
            club.setEstablishDate(rs.getDate("rok_zalozenia"));
            club.setClosingDate(rs.getDate("rok_zakonczenia"));
            club.setAdressKod(rs.getString("adres_kod"));
            club.setAdressTown(rs.getString("adres_miasto"));
            club.setAdressStreet(rs.getString("adres_ulica"));

        }

        return club;
    }
}
