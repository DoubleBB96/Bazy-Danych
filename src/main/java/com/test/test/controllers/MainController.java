package com.test.test.controllers;

import com.test.test.clubs.ClubProfile;
import com.test.test.clubs.ClubRepo;
import com.test.test.clubs.ClubService;
import com.test.test.matches.MatchProfile;
import com.test.test.matches.MatchRepo;
import com.test.test.matches.MatchService;
import com.test.test.profile.PlayerProfile;
import com.test.test.profile.PlayerRepo;
import com.test.test.profile.PlayerService;
import com.test.test.teams.TeamProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    PlayerService playerService;

    @Autowired
    PlayerRepo playerRepo;

    @Autowired
    ClubService clubService;

    @Autowired
    ClubRepo clubRepo;

    @Autowired
    MatchRepo matchRepo;

    @Autowired
    MatchService matchService;

    @RequestMapping("/hello")
    public String sayHello()
    {
        return "hello";
    }


    @RequestMapping("/all_players")
    public String getPlayers(Model model)
    {
        List<PlayerProfile> players = playerService.getPlayerProfile();
        model.addAttribute("players",players);

        return "all_players";
    }

    @RequestMapping("/clubs")
    public String getClubs(Model model)
    {
        List<ClubProfile> clubs = clubService.getAllClubs();
        model.addAttribute("clubs",clubs);
        return "clubs";
    }

    @RequestMapping("/players/{clubId}")
    public String getPlayers(@PathVariable("clubId") Integer clubId, Model model)
    {
        List<PlayerProfile> players = playerRepo.getPlayersByClubId(clubId);
        ClubProfile club = clubRepo.getClubNameById(clubId);
        model.addAttribute("players",players);
        model.addAttribute("club",club);

        return "club_players";
    }

    @RequestMapping("/matches/{roundId}")
    public String getMatchesByRoundId(@PathVariable("roundId") Integer roundId, Model model)
    {
        List<MatchProfile> matches = matchRepo.getMatchesByRoundId(roundId);
        model.addAttribute("matches",matches);
        model.addAttribute("round",roundId);

        return "round_matches";
    }

    @RequestMapping("/matches")
    public String getMatchesByRoundId(Model model)
    {

        List<MatchProfile> rounds = matchService.getAllRounds();
        model.addAttribute("rounds",rounds);

        return "matches";
    }

    @RequestMapping("/table")
    public String getTable()
    {

        return "table";
    }

}
