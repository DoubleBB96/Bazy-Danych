SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS amatorzy;
DROP TABLE IF EXISTS cwiczenia;
DROP TABLE IF EXISTS druzyny;
DROP TABLE IF EXISTS druzyny_mecze;
DROP TABLE IF EXISTS druzyny_turnieje;
DROP TABLE IF EXISTS fizjoterapeuci;
DROP TABLE IF EXISTS kluby;
DROP TABLE IF EXISTS kluby_druzyna_prof;
DROP TABLE IF EXISTS kluby_nalezy_profesjonalisci;
DROP TABLE IF EXISTS mecze;
DROP TABLE IF EXISTS osoby;
DROP TABLE IF EXISTS pracownicy;
DROP TABLE IF EXISTS pracownicy_kluby;
DROP TABLE IF EXISTS pracownicy_szkolenia;
DROP TABLE IF EXISTS prezesi;
DROP TABLE IF EXISTS prof_druzyna_gral_w_sezon;
DROP TABLE IF EXISTS pozycje;
DROP TABLE IF EXISTS profesjonalisci;
DROP TABLE IF EXISTS sezony;
DROP TABLE IF EXISTS statystycy;
DROP TABLE IF EXISTS szkolenia;
DROP TABLE IF EXISTS trenerzy;
DROP TABLE IF EXISTS treningi;
DROP TABLE IF EXISTS treningi_cwiczenia;
DROP TABLE IF EXISTS turnieje;
DROP TABLE IF EXISTS turnieje_mecze;
DROP TABLE IF EXISTS druzyny_treningi;
DROP TABLE IF EXISTS dostep_role;
DROP TABLE IF EXISTS user_role;
DROP TABLE IF EXISTS rundy;



SET FOREIGN_KEY_CHECKS = 1;

#==================TWORZENIE TABELI=====================

SET FOREIGN_KEY_CHECKS = 0;

create table osoby (
osoba_id       int NOT NULL unique auto_increment,
pesel          varchar(50) NOT NULL unique,
imie           varchar(50) NOT NULL default '',
nazwisko       varchar(50) NOT NULL default '',
data_urodzenia date NOT NULL,
adres_kod      varchar(50) NOT NULL default '00-000',
adres_miasto   varchar(50) NOT NULL default '',
adres_ulica    varchar(50) NOT NULL default '',
plec           enum('k','m') NOT NULL default 'm',
user_name      varchar(50) NOT NULL unique,
password       varchar(100) NOT NULL,
email          varchar(50) NOT NULL unique, 
primary key (osoba_id)
)
engine = InnoDB
default charset = utf8;

create table dostep_role(
role_id         int NOT NULL unique auto_increment,
role            varchar(25) NOT NULL unique,

primary key (role_id)
)
engine = InnoDB
default charset = utf8;

create table user_role(
user_id         int NOT NULL,
role_id         int NOT NULL,

primary key (user_id),
foreign key (user_id) references osoby(osoba_id),
foreign key (role_id) references dostep_role(role_id)
)
  engine = InnoDB
  default charset = utf8;


create table pracownicy(
pracownik_id    int NOT NULL,
licencja        varchar(50) NOT NULL unique,
pensja          int NOT NULL,

primary key (pracownik_id),
foreign key (pracownik_id) references osoby(osoba_id)
)
engine = InnoDB
default charset = utf8;

create table prezesi (
prezes_id       int NOT NULL,
partia          varchar(50) NOT NULL,
motywacja       varchar(50) NOT NULL default '',
wyksztalcenie   varchar(50) NOT NULL,

primary key (prezes_id),
foreign key (prezes_id) references pracownicy(pracownik_id)
)
engine = InnoDB
default charset = utf8;

create table trenerzy (
trener_id       int NOT NULL,
aktywny         bool NOT NULL default true,

primary key (trener_id),
foreign key (trener_id) references pracownicy(pracownik_id)
)
engine = InnoDB
default charset = utf8;

create table statystycy (
statystyk_id    int NOT NULL,


primary key (statystyk_id),
foreign key (statystyk_id) references pracownicy(pracownik_id)
)
engine = InnoDB
default charset = utf8;

create table fizjoterapeuci (
fizjo_id        int NOT NULL,
wyksztalcenie   varchar(50) NOT NULL,

primary key (fizjo_id),
foreign key (fizjo_id) references pracownicy(pracownik_id)
)
engine = InnoDB
default charset = utf8;

create table pozycje(
  pos_id       int NOT NULL unique auto_increment,
  position     varchar(50) NOT NULL,

  primary key (pos_id)
)
engine = InnoDB
default charset = utf8;

create table profesjonalisci (
prof_id             int NOT NULL,
posiada_karte       bool NOT NULL default false,
pos_id              int NOT NULL,

primary key (prof_id),
foreign key (prof_id) references pracownicy(pracownik_id),
foreign key (pos_id) references pozycje(pos_id)
)
engine = InnoDB
default charset = utf8;

create table amatorzy (
amator_id       int NOT NULL,
gra_od          date NOT NULL,

primary key (amator_id),
foreign key (amator_id) references osoby(osoba_id)
)
engine = InnoDB
default charset = utf8;

create table kluby (
klub_id         int NOT NULL unique auto_increment,
nazwa    	    varchar(50) NOT NULL unique,
rok_zalozenia   date NOT NULL,
rok_zakonczenia date             default NULL,
adres_kod       varchar(50) NOT NULL default '00-000',
adres_miasto    varchar(50) NOT NULL default '',
adres_ulica     varchar(50) NOT NULL default '',

primary key (klub_id)
)
engine = InnoDB
default charset = utf8;

create table pracownicy_kluby (
pracownicy_kluby_id     int NOT NULL unique auto_increment,
pracownik_id            int NOT NULL,
klub_id                 int NOT NULL,
umowa_od                date NOT NULL,
umowa_do                date NOT NULL,

primary key (pracownicy_kluby_id),
foreign key (pracownik_id) references pracownicy(pracownik_id),
foreign key (klub_id) references kluby(klub_id)
)
engine = InnoDB
default charset = utf8;

create table szkolenia(
szkolenie_id              int NOT NULL unique auto_increment,
opis                      text NOT NULL,
jednostka_prowadzaca      varchar(50) NOT NULL,

primary key (szkolenie_id)
)
engine = InnoDB
default charset = utf8;

create table pracownicy_szkolenia(
pracownicy_szkolenia_id     int NOT NULL unique auto_increment,
pracownik_id                int NOT NULL,
szkolenie_id                int NOT NULL,
wazny_od                    date NOT NULL,
wazny_do                    date NOT NULL,

primary key (pracownicy_szkolenia_id),
foreign key (pracownik_id) references pracownicy (pracownik_id),
foreign key (szkolenie_id) references szkolenia (szkolenie_id)
)
engine = InnoDB
default charset = utf8;

create table druzyny(
druzyna_id                int NOT NULL unique auto_increment,
punkty                    int           default 0,
wystawiona_do_rozgrywek   bool NOT NULL default true,
profesjonalna             bool NOT NULL default true,
amatorska                 bool NOT NULL default false,

primary key (druzyna_id)
)
engine = InnoDB
default charset = utf8;

create table kluby_druzyna_prof(
kluby_druzyna_prof_id    int NOT NULL unique auto_increment,
klub_id                  int NOT NULL,
druzyna_id               int NOT NULL,
data_wystawienia         date NOT NULL,

primary key (kluby_druzyna_prof_id),
foreign key (klub_id) references kluby (klub_id),
foreign key (druzyna_id) references druzyny(druzyna_id)
)
engine = InnoDB
default charset = utf8;

create table kluby_nalezy_profesjonalisci(
kluby_prof_id        int NOT NULL unique auto_increment,
profesjonalista_id   int NOT NULL,
klub_id              int NOT NULL,
nalezy_od            date NOT NULL,
nalezal_do           date            default NULL,

primary key (kluby_prof_id),
foreign key (profesjonalista_id) references profesjonalisci(prof_id),
foreign key (klub_id) references kluby(klub_id)
)
engine = InnoDB
default charset = utf8;

create table sezony(
sezon_id                    int NOT NULL unique auto_increment,
rok_1                        year NOT NULL,
rok_2                        year NOT NULL,

primary key (sezon_id)
)
engine = InnoDB
default charset = utf8;

create table prof_druzyna_gral_w_sezon(
prof_druzyna_gral_w_sezon_id  int NOT NULL unique auto_increment,
prof_id                       int NOT NULL,
druzyna_id                    int NOT NULL,
sezon_id                      int NOT NULL,
zajete_msc                    int NOT NULL,
liga                          int NOT NULL,

primary key (prof_druzyna_gral_w_sezon_id),
foreign key (prof_id) references profesjonalisci(prof_id),
foreign key (druzyna_id) references druzyny(druzyna_id),
foreign key (sezon_id) references sezony(sezon_id)
)
engine = InnoDB
default charset = utf8;

create table druzyna_amatorzy(
druzyna_amatorzy_id        int NOT NULL unique auto_increment,
amator_id                  int NOT NULL,
druzyna_id                 int NOT NULL,

primary key (druzyna_amatorzy_id),
foreign key (amator_id) references amatorzy (amator_id),
foreign key (druzyna_id) references druzyny(druzyna_id)
)
engine = InnoDB
default charset = utf8;

create table rundy(
round_id        int NOT NULL unique auto_increment,
round           int NOT NULL,

primary key (round_id)
)
  engine = InnoDB
  default charset = utf8;

create table mecze (
mecz_id           int NOT NULL unique auto_increment,
round_id          int NOT NULL,
data_meczu        date NOT NULL,
godzina           time not NULL,
wynik             varchar(10) NOT NULL,
wyniki_setow      varchar(50) NOT NULL,

primary key (mecz_id),
foreign key (round_id) references rundy(round_id)

)
engine = InnoDB
default charset = utf8;

create table druzyny_mecze (
druzyny_mecze_id    int NOT NULL unique auto_increment,
mecz_id             int NOT NULL,
druzyna_a_id        int NOT NULL,
druzyna_b_id        int NOT NULL,

primary key (druzyny_mecze_id),
foreign key (mecz_id) references mecze (mecz_id),
foreign key (druzyna_a_id) references druzyny (druzyna_id),
foreign key (druzyna_b_id) references druzyny (druzyna_id)
)
engine = InnoDB
default charset = utf8;

create table sezon_mecze(
sezon_mecze_id              int NOT NULL unique auto_increment,
sezon_id                    int NOT NULL,
mecz_id                     int NOT NULL,

primary key (sezon_mecze_id),
foreign key (sezon_id) references sezony(sezon_id),
foreign key (mecz_id) references mecze(mecz_id)
)
engine = InnoDB
default charset = utf8;

create table turnieje(
turniej_id              int NOT NULL unique auto_increment,
termin_od               date NOT NULL,
termin_do               date NOT NULL,
nagrody                 varchar(50) NOT NULL default '',
profesjonalny           bool NOT NULL default true,
amatorski               bool NOT NULL default false,

primary key (turniej_id)
)
engine = InnoDB
default charset = utf8;

create table turnieje_mecze(
turnieje_mecze_id              int NOT NULL unique auto_increment,
turniej_id                     int NOT NULL,
mecz_id                        int NOT NULL,

primary key (turnieje_mecze_id),
foreign key (turniej_id) references turnieje(turniej_id),
foreign key (mecz_id) references mecze(mecz_id)
)
engine = InnoDB
default charset = utf8;

create table druzyny_turnieje(
druzyny_turnieje_id              int NOT NULL unique auto_increment,
turniej_id                       int NOT NULL,
druzyna_id                       int NOT NULL,
zajete_miejsce                   int          default NULL,
wniesiona_oplata                 bool NOT NULL default false,

primary key (druzyny_turnieje_id),
foreign key (turniej_id) references turnieje(turniej_id),
foreign key (druzyna_id) references druzyny(druzyna_id)
)
engine = InnoDB
default charset = utf8;

create table treningi(
trening_id              int NOT NULL unique auto_increment,
termin                  date NOT NULL,
czas                    int NOT NULL,
typ                     varchar(11) NOT NULL,
sala_adres_miasto       varchar(50) NOT NULL,
sala_adres_ulica        varchar(50) NOT NULL,
sala_adres_kod          varchar(50) NOT NULL,
tworca_id               int NOT NULL,

primary key (trening_id),
foreign key (tworca_id) references trenerzy(trener_id)
)
engine = InnoDB
default charset = utf8;

create table druzyny_treningi(
druzyny_treningi_id    int NOT NULL unique auto_increment,
druzyna_id             int NOT NULL,
trening_id             int NOT NULL,

primary key (druzyny_treningi_id),
foreign key (druzyna_id) references druzyny(druzyna_id),
foreign key (trening_id) references treningi(trening_id)
)
engine = InnoDB
default charset = utf8;


create table cwiczenia(
cwiczenie_id            int NOT NULL unique auto_increment,
opis                    varchar(50) NOT NULL default '',

primary key (cwiczenie_id)
)
engine = InnoDB
default charset = utf8;

create table treningi_cwiczenia(
treningi_cwiczenia_id              int NOT NULL unique auto_increment,
trening_id                         int NOT NULL,
cwiczenie_id                       int NOT NULL,

primary key (treningi_cwiczenia_id),
foreign key (trening_id) references treningi(trening_id),
foreign key (cwiczenie_id) references cwiczenia(cwiczenie_id)
)
engine = InnoDB
default charset = utf8;

SET FOREIGN_KEY_CHECKS = 1;

#==================IMPORT_DANYCH=====================
SET FOREIGN_KEY_CHECKS = 0;
LOAD DATA LOCAL INFILE '/var/lib/mysql-files/amatorzy.csv'
INTO TABLE amatorzy
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\n';

LOAD DATA LOCAL INFILE '/var/lib/mysql-files/cwiczenia.csv'
INTO TABLE cwiczenia
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\n';

LOAD DATA LOCAL INFILE '/var/lib/mysql-files/druzyny.csv'
INTO TABLE druzyny
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\n';

LOAD DATA LOCAL INFILE '/var/lib/mysql-files/druzyny_mecze.csv'
INTO TABLE druzyny_mecze
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\n';

LOAD DATA LOCAL INFILE '/var/lib/mysql-files/druzyny_turnieje.csv'
INTO TABLE druzyny_turnieje
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\n';

LOAD DATA LOCAL INFILE '/var/lib/mysql-files/fizjoterapeuci.csv'
INTO TABLE fizjoterapeuci
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\n';

LOAD DATA LOCAL INFILE '/var/lib/mysql-files/kluby.csv'
INTO TABLE kluby
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\n';

LOAD DATA LOCAL INFILE '/var/lib/mysql-files/kluby_druzyna_prof.csv'
INTO TABLE kluby_druzyna_prof
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\n';

LOAD DATA LOCAL INFILE '/var/lib/mysql-files/kluby_nalezy_profesjonalisci.csv'
INTO TABLE kluby_nalezy_profesjonalisci
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\n';

LOAD DATA LOCAL INFILE '/var/lib/mysql-files/mecze.csv'
INTO TABLE mecze
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\n';

LOAD DATA LOCAL INFILE '/var/lib/mysql-files/pracownicy.csv'
INTO TABLE pracownicy
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\n';

LOAD DATA LOCAL INFILE '/var/lib/mysql-files/pracownicy_kluby.csv'
INTO TABLE pracownicy_kluby
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\n';

LOAD DATA LOCAL INFILE '/var/lib/mysql-files/pracownicy_szkolenia.csv'
INTO TABLE pracownicy_szkolenia
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\n';

LOAD DATA LOCAL INFILE '/var/lib/mysql-files/prezesi.csv'
INTO TABLE prezesi
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\n';

LOAD DATA LOCAL INFILE '/var/lib/mysql-files/prof_druzyna_gral_w_sezon.csv'
INTO TABLE prof_druzyna_gral_w_sezon
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\n';

LOAD DATA LOCAL INFILE '/var/lib/mysql-files/profesjonalisci.csv'
INTO TABLE profesjonalisci
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\n';

LOAD DATA LOCAL INFILE '/var/lib/mysql-files/sezony.csv'
INTO TABLE sezony
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\n';

LOAD DATA LOCAL INFILE '/var/lib/mysql-files/statystycy.csv'
INTO TABLE statystycy
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\n';

LOAD DATA LOCAL INFILE '/var/lib/mysql-files/szkolenia.csv'
INTO TABLE szkolenia
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\n';

LOAD DATA LOCAL INFILE '/var/lib/mysql-files/trenerzy.csv'
INTO TABLE trenerzy
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\n';

LOAD DATA LOCAL INFILE '/var/lib/mysql-files/treningi.csv'
INTO TABLE treningi
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\n';

LOAD DATA LOCAL INFILE '/var/lib/mysql-files/treningi_cwiczenia.csv'
INTO TABLE treningi_cwiczenia
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\n';

LOAD DATA LOCAL INFILE '/var/lib/mysql-files/druzyny_treningi.csv'
INTO TABLE druzyny_treningi
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\n';

LOAD DATA LOCAL INFILE '/var/lib/mysql-files/turnieje.csv'
INTO TABLE turnieje
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\n';

LOAD DATA LOCAL INFILE '/var/lib/mysql-files/turnieje_mecze.csv'
INTO TABLE turnieje_mecze
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\n';

LOAD DATA LOCAL INFILE '/var/lib/mysql-files/dostep_role.csv'
INTO TABLE dostep_role
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\n';

LOAD DATA LOCAL INFILE '/var/lib/mysql-files/user_role.csv'
INTO TABLE user_role
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\n';

LOAD DATA LOCAL INFILE '/var/lib/mysql-files/osoby.csv'
INTO TABLE osoby
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\n';

LOAD DATA LOCAL INFILE '/var/lib/mysql-files/pozycje.csv'
INTO TABLE pozycje
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\n';

LOAD DATA LOCAL INFILE '/var/lib/mysql-files/sezon_mecze.csv'
INTO TABLE sezon_mecze
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\n';

LOAD DATA LOCAL INFILE '/var/lib/mysql-files/rundy.csv'
INTO TABLE rundy
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\n';

SET FOREIGN_KEY_CHECKS = 1;
