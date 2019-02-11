package com.test.test.matches;

import com.test.test.clubs.ClubProfile;
import com.test.test.profile.PlayerProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class MatchService {

    @Autowired
    MatchRepo matchRepo;

    public List<MatchProfile> getAllRounds()
    {

        List<MatchProfile> rounds = new ArrayList<>();

        List<Map<String,Object>> results = matchRepo.getMatchesRounds();
        Integer previousRound = 0;

        for(Map result : results)
        {
            MatchProfile match = new MatchProfile();

            Integer matchRound = (Integer) result.get("round");


            if(matchRound == previousRound){}
            else {
                match.setMatchRound((Integer) result.get("round"));
                previousRound = matchRound;
                rounds.add(match);
            }
        }

        return rounds;

    }

}
