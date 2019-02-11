package com.test.test.clubs;

import com.test.test.profile.PlayerProfile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Repository
public class ClubRepo {

    private static final String GET_CLUBS ="select k.klub_id,k.nazwa,k.rok_zalozenia,k.rok_zakonczenia,k.adres_kod,k.adres_miasto,k.adres_ulica\n" +
            "from kluby as k";

    private static  final String GET_CLUB_NAME_BY_ID="select k.nazwa,k.adres_miasto\n" +
            "from kluby k\n" +
            "where k.klub_id = ?";

    private final JdbcTemplate jdbcTemplate;

    public ClubRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Map<String,Object>> getAllClubs(){
        return jdbcTemplate.queryForList(GET_CLUBS);
    }

    public ClubProfile getClubNameById(final long clubId){
        return jdbcTemplate.query(GET_CLUB_NAME_BY_ID, new String[]{String.valueOf(clubId)}, this::extractClubNameById);
    }


    ClubProfile extractClubNameById(ResultSet rs) throws SQLException {

       ClubProfile club = new ClubProfile();

        while(rs.next())
        {
            club.setClubName(rs.getString("nazwa"));
            club.setAdressTown(rs.getString("adres_miasto"));
        }

        return club;
    }

}
