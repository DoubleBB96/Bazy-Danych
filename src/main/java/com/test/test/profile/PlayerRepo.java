package com.test.test.profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
public class PlayerRepo {


    private static final String GET_PLAYER_PROFILES = "select p.prof_id,o.imie, o.nazwisko, o.data_urodzenia,pracownicy.licencja,k2.nazwa as klub,k2.adres_miasto as klub_msc, k1.nazwa as druzyna,k1.adres_miasto as druzyna_msc, poz.position\n" +
            "            from profesjonalisci as p\n" +
            "              left join pozycje poz on p.pos_id = poz.pos_id\n" +
            "                   left join pracownicy  on p.prof_id = pracownicy.pracownik_id\n" +
            "                   left join osoby as o on pracownicy.pracownik_id = o.osoba_id\n" +
            "                   left join pracownicy_kluby as pk on pracownicy.pracownik_id = pk.pracownik_id\n" +
            "                   left join kluby as k1 on pk.klub_id = k1.klub_id\n" +
            "                   left join kluby_nalezy_profesjonalisci as knp on p.prof_id = knp.profesjonalista_id\n" +
            "                   left join kluby as k2 on knp.klub_id = k2.klub_id\n" +
            "                   order by p.prof_id";

    private static final String GET_PLAYERS_BY_CLUB_ID = "select prof.prof_id,o.imie,o.nazwisko,poz.position ,p.licencja, knp.nalezy_od,knp.nalezal_do\n" +
            "from profesjonalisci as prof\n" +
            "left join pozycje poz on prof.pos_id = poz.pos_id\n" +
            "left join kluby_nalezy_profesjonalisci as knp on prof.prof_id = knp.profesjonalista_id\n" +
            "left join kluby on knp.klub_id = kluby.klub_id\n" +
            "left join pracownicy p on prof.prof_id = p.pracownik_id\n" +
            "left join osoby o on p.pracownik_id = o.osoba_id\n" +
            "where kluby.klub_id = ?";

    private final JdbcTemplate jdbcTemplate;

    public PlayerRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Map<String,Object>> getPlayerProfile() {

        return jdbcTemplate.queryForList(GET_PLAYER_PROFILES);
    }

    public List<PlayerProfile> getPlayersByClubId ( final long clubId){

        return jdbcTemplate.query(GET_PLAYERS_BY_CLUB_ID, new String[]{String.valueOf(clubId)}, this::extractDataForPlayers);
    }


    List<PlayerProfile> extractDataForPlayers(final ResultSet rs) throws SQLException {
        final List<PlayerProfile> players = new LinkedList<>();
        while(rs.next()){
            PlayerProfile player = new PlayerProfile();
            player.setId(rs.getInt("prof_id"));
            player.setName(rs.getString("imie"));
            player.setSurname(rs.getString("nazwisko"));
            player.setLicense(rs.getString("licencja"));
            player.setPosition(rs.getString("position"));
            player.setOwningFromDate(rs.getDate("nalezy_od"));
            player.setOwningToDate(rs.getDate("nalezal_do"));

            players.add(player);

        }

        return players;
    }



//         return jdbcTemplate.query(GET_PLAYER_PROFILES, new RowMapper<PlayerProfile>() {
//             @Override
//        public PlayerProfile mapRow(ResultSet rs, int i) throws SQLException {
//            PlayerProfile pp = new PlayerProfile();
//            pp.setPesel(rs.getString(1));
//            pp.setName(rs.getString(2));
//            pp.setSurname(rs.getString(3));
//            pp.setBirth_date(LocalDate.parse(rs.getString(4)));
//            pp.setKod(rs.getString(5));
//            pp.setTown(rs.getString(6));
//            pp.setStreet(rs.getString(7));
//            pp.setSex(rs.getString(8));
//            pp.setUsername(rs.getString(9));
//            pp.setClubName(rs.getString(10));
//            pp.setClubTown(rs.getString(11));
//            pp.setCardOwner(rs.getBoolean(12));
//            pp.setOwningClubName(rs.getString(13));
//            pp.setOwningClubTown(rs.getString(14));
//            pp.setLicense(rs.getString(15));
//            return pp;
//
//             }
//         });

//    , new RowMapper<PlayerProfile>() {
//        @Override
//        public PlayerProfile mapRow(ResultSet rs, int i) throws SQLException {
//            PlayerProfile pp = new PlayerProfile();
//            pp.setPesel(rs.getString(1));
//            pp.setName(rs.getString(2));
//            pp.setSurname(rs.getString(3));
//            pp.setBirth_date(LocalDate.parse(rs.getString(4)));
//            pp.setKod(rs.getString(5));
//            pp.setTown(rs.getString(6));
//            pp.setStreet(rs.getString(7));
//            pp.setSex(rs.getString(8));
//            pp.setUsername(rs.getString(9));
//            pp.setClubName(rs.getString(10));
//            pp.setClubTown(rs.getString(11));
//            pp.setCardOwner(rs.getBoolean(12));
//            pp.setOwningClubName(rs.getString(13));
//            pp.setOwningClubTown(rs.getString(14));
//            pp.setLicense(rs.getString(15));
//            return pp;
//        }
//    }
//
//    PlayerProfile extractDataForPlayer(final ResultSet rs) throws SQLException {
//        if(rs.next()){
//            val pesel = rs.getString("pesel");
//            val name = rs.getString("imie");
//            val surname = rs.getString("nazwisko");
//            val birth_date = LocalDate.parse(rs.getString("data_urodzenia"));
//            val kod = rs.getString("adres_kod");
//            val town = rs.getString("adres_miasto");
//            val street = rs.getString("adres_ulica");
//            val sex = rs.getString("plec");
//            val username = rs.getString("user_name");
//            val clubName = rs.getString("nazwa");
//            val clubTown = rs.getString("adres_miasto");
//            val isCardOwner = rs.getBoolean("posiada_karte");
//            val owningClubName = rs.getString("nazwa");
//            val owningClubTown = rs.getString("adres_miasto");
//            val license = rs.getString("licencja");
//
//            return PlayerProfile.builder()
//                    .pesel(pesel)
//                    .name(name)
//                    .surname(surname)
//                    .birth_date(birth_date)
//                    .kod(kod)
//                    .town(town)
//                    .street(street)
//                    .sex(sex)
//                    .username(username)
//                    .clubName(clubName)
//                    .clubTown(clubTown)
//                    .isCardOwner(isCardOwner)
//                    .owningClubName(owningClubName)
//                    .owningClubTown(owningClubTown)
//                    .license(license)
//                    .build();
//        }
//        return null;
//    }


}
