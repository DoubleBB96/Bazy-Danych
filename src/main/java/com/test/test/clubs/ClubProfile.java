package com.test.test.clubs;

import com.test.test.profile.PlayerProfile;

import java.util.Date;
import java.util.List;

public class ClubProfile {

    private Integer clubId;
    private String clubName;
    private Date establishDate;
    private Date closingDate;
    private String adressKod;
    private String adressTown;
    private String adressStreet;
    private List<PlayerProfile> belongingPlayers;

    public ClubProfile() {
    }

    public Integer getClubId() {
        return clubId;
    }

    public void setClubId(Integer clubId) {
        this.clubId = clubId;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public Date getEstablishDate() {
        return establishDate;
    }

    public void setEstablishDate(Date establishDate) {
        this.establishDate = establishDate;
    }

    public Date getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(Date closingDate) {
        this.closingDate = closingDate;
    }

    public String getAdressKod() {
        return adressKod;
    }

    public void setAdressKod(String adressKod) {
        this.adressKod = adressKod;
    }

    public String getAdressTown() {
        return adressTown;
    }

    public void setAdressTown(String adressTown) {
        this.adressTown = adressTown;
    }

    public String getAdressStreet() {
        return adressStreet;
    }

    public void setAdressStreet(String adressStreet) {
        this.adressStreet = adressStreet;
    }

    public List<PlayerProfile> getBelongingPlayers() {
        return belongingPlayers;
    }

    public void setBelongingPlayers(List<PlayerProfile> belongingPlayers) {
        this.belongingPlayers = belongingPlayers;
    }
}
