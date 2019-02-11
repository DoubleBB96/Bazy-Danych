package com.test.test.profile;

import java.util.Date;


public class PlayerProfile {

    private Integer id;
    private String pesel;
    private String name;
    private String surname;
    private Date birth_date;
    private String kod;
    private String town;
    private String street;
    private String sex;
    private String username;
    private String clubName;
    private String clubTown;
    private boolean isCardOwner;
    private String owningClubName;
    private Date owningFromDate;
    private Date owningToDate;
    private String position;

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Date getOwningFromDate() {
        return owningFromDate;
    }

    public void setOwningFromDate(Date owningFromDate) {
        this.owningFromDate = owningFromDate;
    }

    public Date getOwningToDate() {
        return owningToDate;
    }

    public void setOwningToDate(Date owningToDate) {
        this.owningToDate = owningToDate;
    }

    private String owningClubTown;
    private String license;

    public PlayerProfile() {

    }

    public long getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getClubTown() {
        return clubTown;
    }

    public void setClubTown(String clubTown) {
        this.clubTown = clubTown;
    }

    public boolean isCardOwner() {
        return isCardOwner;
    }

    public void setCardOwner(boolean cardOwner) {
        isCardOwner = cardOwner;
    }

    public String getOwningClubName() {
        return owningClubName;
    }

    public void setOwningClubName(String owningClubName) {
        this.owningClubName = owningClubName;
    }

    public String getOwningClubTown() {
        return owningClubTown;
    }

    public void setOwningClubTown(String owningClubTown) {
        this.owningClubTown = owningClubTown;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }
}
