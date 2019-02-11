package com.test.test.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

@Service
public class PlayerService {

    @Autowired
    PlayerRepo playerRepo;

    public List<PlayerProfile> getPlayerProfile(){

        List<PlayerProfile> players = new ArrayList<>();
        List<Map<String,Object>> results = playerRepo.getPlayerProfile();

        for(Map result : results){

            PlayerProfile player = new PlayerProfile();
            player.setId((Integer) result.get("prof_id"));
            player.setName((String) result.get("imie"));
            player.setSurname((String) result.get("nazwisko"));
            player.setBirth_date((Date) result.get("data_urodzenia"));
            player.setLicense((String) result.get("licencja"));
            player.setOwningClubName((String) result.get("klub"));
            player.setOwningClubTown((String) result.get("klub_msc"));
            player.setClubName((String) result.get("druzyna"));
            player.setClubTown((String) result.get("druzyna_msc"));
            player.setPosition((String) result.get("position"));

            players.add(player);
            }


        return players;

    }


}
