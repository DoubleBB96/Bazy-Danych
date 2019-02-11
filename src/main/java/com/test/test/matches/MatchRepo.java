package com.test.test.matches;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Repository
public class MatchRepo {

    private static final String GET_MATCHES_BY_ROUND_ID="select r.round as kolejka,m.data_meczu,k1.nazwa as Anazwa,k1.adres_miasto as Amiasto,k2.nazwa as Bnazwa,k2.adres_miasto as Bmiasto,m.wynik,m.wyniki_setow\n" +
            "from mecze as m\n" +
            "left join rundy r on m.round_id = r.round_id\n" +
            "left join druzyny_mecze dm on m.mecz_id = dm.mecz_id\n" +
            "left join druzyny d1 on dm.druzyna_a_id = d1.druzyna_id\n" +
            "left join druzyny d2 on dm.druzyna_b_id = d2.druzyna_id\n" +
            "left join kluby_druzyna_prof kdp1 on d1.druzyna_id = kdp1.druzyna_id\n" +
            "left join kluby_druzyna_prof kdp2 on d2.druzyna_id = kdp2.druzyna_id\n" +
            "left join kluby k1 on kdp1.klub_id = k1.klub_id\n" +
            "left join kluby k2 on kdp2.klub_id = k2.klub_id\n" +
            "where r.round_id = ?";


    private static final String GET_ALL_MATCHES_ROUNDS="select r.round\n" +
            "from mecze\n" +
            "left join rundy r on mecze.round_id = r.round_id";

    private final JdbcTemplate jdbcTemplate;

    public MatchRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<MatchProfile> getMatchesByRoundId(final long roundId){
        return jdbcTemplate.query(GET_MATCHES_BY_ROUND_ID,new String[]{String.valueOf(roundId)},this::extractDataForMatches);
    }

    public List<Map<String,Object>> getMatchesRounds()
    {
        return jdbcTemplate.queryForList(GET_ALL_MATCHES_ROUNDS);
    }

    List<MatchProfile> extractDataForMatches(ResultSet rs) throws SQLException {

        List<MatchProfile> matches = new LinkedList<>();

        while(rs.next())
        {
            MatchProfile match = new MatchProfile();

            match.setMatchRound(rs.getInt("kolejka"));
            match.setMatchDate(rs.getDate("data_meczu"));
            match.setTeamAName(rs.getString("Anazwa"));
            match.setTeamATown(rs.getString("Amiasto"));
            match.setTeamBName(rs.getString("Bnazwa"));
            match.setTeamBTown(rs.getString("Bmiasto"));
            match.setMatchScore(rs.getString("wynik"));
            match.setSetsScore(rs.getString("wyniki_setow"));

            matches.add(match);
        }

        return matches;

    }

}
