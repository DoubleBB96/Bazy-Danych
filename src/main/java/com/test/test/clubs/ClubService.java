package com.test.test.clubs;

import com.test.test.profile.PlayerProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Service
public class ClubService {

    @Autowired
    ClubRepo clubRepo;

    public List<ClubProfile> getAllClubs(){

        List<ClubProfile>clubs = new ArrayList<>();
        List<Map<String,Object>> results = clubRepo.getAllClubs();

        List<PlayerProfile> players = new ArrayList<>();

        Integer previousId = 0;

        for(Map result : results)
        {
            ClubProfile club = new ClubProfile();

            Integer clubId = (Integer) result.get("klub_id");


            if(clubId == previousId){}
            else {
                club.setClubId((Integer) result.get("klub_id"));
                club.setClubName((String) result.get("nazwa"));
                club.setEstablishDate((Date) result.get("rok_zalozenia"));
                club.setClosingDate((Date) result.get("rok_zakonczenia"));
                club.setAdressKod((String) result.get("adres_kod"));
                club.setAdressTown((String) result.get("adres_miasto"));
                club.setAdressStreet((String) result.get("adres_ulica"));

                clubs.add(club);

                previousId = clubId;
            }
        }

        return clubs;
    }

}
