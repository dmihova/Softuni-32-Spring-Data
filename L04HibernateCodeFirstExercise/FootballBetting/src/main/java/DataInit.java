import Entities.*;
import jakarta.persistence.EntityManager;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataInit {

    public static void initContinents(EntityManager entityManager) {
        entityManager.getTransaction().begin();
        Continent continent = entityManager.createQuery("FROM Continent WHERE name = 'Europe'", Continent.class)
                .getResultList().stream().findFirst().orElse(null);
        if (continent == null) {
            Continent continentEurope = new Continent("Europe");
            entityManager.persist(continentEurope);
            Continent continentAfrica = new Continent("Africa");
            entityManager.persist(continentAfrica);
            Continent continentAsia = new Continent("Asia");
            entityManager.persist(continentAsia);
            Continent continentAmerica = new Continent("America");
            entityManager.persist(continentAmerica);

        }
        entityManager.getTransaction().commit();
    }

    public static void initCountries(EntityManager entityManager) {
        entityManager.getTransaction().begin();
        Country firstCountry = entityManager.createQuery("FROM Country WHERE id = 'FDR'", Country.class)
                .getResultList().stream().findFirst().orElse(null);
        if (firstCountry == null) {
            Continent continent = entityManager.createQuery("FROM Continent WHERE name = 'Europe'", Continent.class)
                    .getResultList().stream().findFirst().orElse(null);
            Country germany = new Country("FDR", "Germany");
            germany.addContinent(continent);
            entityManager.persist(germany);

            Country france = new Country("FR", "France");
            france.addContinent(continent);
            entityManager.persist(france);
        }

        entityManager.getTransaction().commit();
    }

    public static void initTowns(EntityManager entityManager) {
        entityManager.getTransaction().begin();
        Town firstTown = entityManager.createQuery("FROM Town WHERE name = 'Munich'", Town.class)
                .getResultList().stream().findFirst().orElse(null);
        if (firstTown == null) {
            Country germany = entityManager.createQuery("FROM Country WHERE id = 'FDR'", Country.class)
                    .getResultList().stream().findFirst().orElse(null);
            Town munich = new Town("Munich", germany);
            entityManager.persist(munich);
            Town dortmund = new Town("Dortmund", germany);
            entityManager.persist(dortmund);
            Town bremen = new Town("Bremen", germany);
            entityManager.persist(bremen);

        }
        entityManager.getTransaction().commit();
    }

    public static void initColors(EntityManager entityManager) {
        entityManager.getTransaction().begin();
        TeamColor fcolor = entityManager.find(TeamColor.class, 1);
        if (fcolor == null) {
            TeamColor red = new TeamColor("red");
            entityManager.persist(red);
            TeamColor blue = new TeamColor("blue");
            entityManager.persist(blue);
            TeamColor black = new TeamColor("black");
            entityManager.persist(black);
            TeamColor yellow = new TeamColor("yellow");
            entityManager.persist(yellow);
        }
        entityManager.getTransaction().commit();
    }

    public static void initPosition(EntityManager entityManager) {
        entityManager.getTransaction().begin();
        Position first = entityManager.createQuery("FROM Position WHERE id = 'GK'", Position.class)
                .getResultList().stream().findFirst().orElse(null);
        if (first == null) {
            Position posGK = new Position("GK", "goal keeper");
            entityManager.persist(posGK);
            Position posDF = new Position("DF", "defender");
            entityManager.persist(posDF);
            Position posMF = new Position("MF", "MF");
            entityManager.persist(posMF);
            Position posFW = new Position("FW", "FW");
            entityManager.persist(posFW);
        }
        entityManager.getTransaction().commit();
    }


    public static void initTeams(EntityManager entityManager) {
        entityManager.getTransaction().begin();
        Team fteam = entityManager.find(Team.class, 1);
        if (fteam == null) {

            Town munich = entityManager.createQuery("FROM Town WHERE name = 'Munich'", Town.class)
                    .getResultList().stream().findFirst().orElse(null);
            Town dortmund = entityManager.createQuery("FROM Town WHERE name = 'Dortmund'", Town.class)
                    .getResultList().stream().findFirst().orElse(null);
            Town bremen = entityManager.createQuery("FROM Town WHERE name = 'Bremen'", Town.class)
                    .getResultList().stream().findFirst().orElse(null);
            TeamColor fcolor = entityManager.find(TeamColor.class, 1);
            TeamColor scolor = entityManager.find(TeamColor.class, 2);
            TeamColor tcolor = entityManager.find(TeamColor.class, 3);

            Team bm = new Team("Bayern Munich", "BM", "BM",
                    BigDecimal.valueOf(2222222.0), fcolor, scolor, munich);
            Team bd = new Team(" Borussia Dortmund", "BD", "BD",
                    BigDecimal.valueOf(3333333.0), tcolor, scolor, dortmund);
            Team bw = new Team("Bremen Werder", "BW", "BW",
                    BigDecimal.valueOf(1111111.0), scolor, fcolor, bremen);
            entityManager.persist(bm);
            entityManager.persist(bd);
            entityManager.persist(bw);
        }
        entityManager.getTransaction().commit();

    }

    public static void initPlayers(EntityManager entityManager) {
        entityManager.getTransaction().begin();
        Player fplayer = entityManager.find(Player.class, 1);
        if (fplayer == null) {
            Team bm = entityManager.createQuery("FROM Team WHERE name = 'Bayern Munich'", Team.class)
                    .getResultList().stream().findFirst().orElse(null);

            Position posGK = entityManager.find(Position.class, "GK");
            Position posDF = entityManager.find(Position.class, "DF");
            Position posMF = entityManager.find(Position.class, "MF");

            Player newPlayer1 = new Player("Sven Ulreich", 1, bm, posGK, false);
            entityManager.persist(newPlayer1);
            Player newPlayer2 = new Player("Leon Goretzka", 11, bm, posMF, false);
            entityManager.persist(newPlayer2);
            Player newPlayer3 = new Player("Benjamin Pavard", 21, bm, posDF, false);
            entityManager.persist(newPlayer3);

        }

        entityManager.getTransaction().commit();
    }

    public static void initCompetiotionType(EntityManager entityManager) {
        entityManager.getTransaction().begin();
        CompetitionType fct = entityManager.find(CompetitionType.class, 1);
        if (fct == null) {
            CompetitionType local = new CompetitionType("local");
            entityManager.persist(local);
            CompetitionType national = new CompetitionType("national");
            entityManager.persist(national);
            CompetitionType international = new CompetitionType("international");
            entityManager.persist(international);
        }
        entityManager.getTransaction().commit();
    }


    public static void initCompetiotion(EntityManager entityManager) {
        entityManager.getTransaction().begin();
        Competition fc = entityManager.find(Competition.class, 1);
        if (fc == null) {
            CompetitionType fct = entityManager.find(CompetitionType.class, 1);
            Competition c1 = new Competition("competion1", fct);
            entityManager.persist(c1);
            Competition c2 = new Competition("competion2", fct);
            entityManager.persist(c2);
        }
        entityManager.getTransaction().commit();

    }

    public static void initRounds(EntityManager entityManager) {
        entityManager.getTransaction().begin();
        Round fRound = entityManager.find(Round.class, 1);
        if (fRound == null) {
            Round r1 = new Round("Groups");
            entityManager.persist(r1);
            Round r2 = new Round("League");
            entityManager.persist(r2);
            Round r3 = new Round("1/8 Final");
            entityManager.persist(r3);
            Round r4 = new Round("1/4 Final");
            entityManager.persist(r4);
            Round r5 = new Round("Final");
            entityManager.persist(r5);
        }
        entityManager.getTransaction().commit();
    }


    public static void initGames(EntityManager entityManager) throws ParseException {
        entityManager.getTransaction().begin();
        Game fGame = entityManager.find(Game.class, 1);
        if (fGame == null) {
            Team bm = entityManager.createQuery("FROM Team WHERE initials = 'BM'", Team.class)
                    .getResultList().stream().findFirst().orElse(null);
            Team bd = entityManager.createQuery("FROM Team WHERE initials = 'BD'", Team.class)
                    .getResultList().stream().findFirst().orElse(null);
            Team bw = entityManager.createQuery("FROM Team WHERE initials = 'BW'", Team.class)
                    .getResultList().stream().findFirst().orElse(null);
            Date date = new SimpleDateFormat("dd/MM/yyyy").parse("01/10/2022");
            Competition fcompetion = entityManager.find(Competition.class, 1);
            Round fRound = entityManager.find(Round.class, 1);
            Game game1 = new Game(bm, bd, 3, 2, date,
                    0.23, 0.77, 0.43,
                    fRound, fcompetion);
            entityManager.persist(game1);
            Game game2 = new Game(bm, bw, 3, 2, date,
                    0.23, 0.77, 0.43,
                    fRound, fcompetion);
            entityManager.persist(game2);
            Game game3 = new Game(bd, bw, 1, 2, date,
                    0.83, 0.77, 0.43,
                    fRound, fcompetion);
            entityManager.persist(game3);

        }
        entityManager.getTransaction().commit();

    }

    public static void initPlayerStatistics(EntityManager entityManager) {
        entityManager.getTransaction().begin();
        PlayerStatistic fps = entityManager.createQuery("FROM PlayerStatistic", PlayerStatistic.class)
                .getResultList().stream().findFirst().orElse(null);
        if (fps == null) {
            Player fplayer = entityManager.find(Player.class, 1);
            Game fGame = entityManager.find(Game.class, 1);

            PlayerStatistic ps1 = new PlayerStatistic(fGame, fplayer, (short) 1, (short) 12, (short) 45);
            entityManager.persist(ps1);

        }

        entityManager.getTransaction().commit();
    }

    public static void initPredictions(EntityManager entityManager) {
        entityManager.getTransaction().begin();
        ResultPrediction  fPrediction = entityManager.find(ResultPrediction.class, 1);
        if (fPrediction == null) {
            ResultPrediction r1 = new ResultPrediction("Home Team Win");
            entityManager.persist(r1);
            ResultPrediction r2 = new ResultPrediction("Draw Game");
            entityManager.persist(r2);
            ResultPrediction r3 = new ResultPrediction("Away Team Win");
            entityManager.persist(r3);
        }
        entityManager.getTransaction().commit();

    }

    public static void initUsers(EntityManager entityManager) {
        entityManager.getTransaction().begin();
        User  fUser = entityManager.find(User.class, 1);
        if (fUser == null) {
            User u1 = new User("asdd","","wqq@ad.com","Full name",BigDecimal.valueOf(1000.0));
            entityManager.persist(u1);
            User u2 = new User("EEE1","","aaq@ad.com","Full name2",BigDecimal.valueOf(1000.0));
            entityManager.persist(u2);
        }

        entityManager.getTransaction().commit();
    }


    public static void initBets(EntityManager entityManager) {
        entityManager.getTransaction().begin();
        Bet  fBet = entityManager.find(Bet.class, 1);
        if (fBet == null) {
            User  user1 = entityManager.find(User.class, 1);
            User  user2 = entityManager.find(User.class, 2);
            Bet bet1 = new Bet(BigDecimal.valueOf(100),new Date(),user1);
            Bet bet2 = new Bet(BigDecimal.valueOf(200),new Date(),user2);
            entityManager.persist(bet1);
            entityManager.persist(bet2);

        }
        entityManager.getTransaction().commit();
    }

    public static void initBetGames(EntityManager entityManager) {
        entityManager.getTransaction().begin();
        BetGame  fBetGame =  entityManager.createQuery("FROM BetGame", BetGame.class)
                .getResultList().stream().findFirst().orElse(null);
        if (fBetGame == null) {
            Bet  bet1 = entityManager.find(Bet.class, 1);
            Bet  bet2 = entityManager.find(Bet.class, 2);
            Game fGame1 = entityManager.find(Game.class, 1);
            Game fGame2 = entityManager.find(Game.class, 2);
            ResultPrediction  fPrediction = entityManager.find(ResultPrediction.class, 1);

            BetGame betGame1 = new BetGame(fGame1,bet1,fPrediction);
            BetGame betGame2 = new BetGame(fGame2 ,bet2,fPrediction);
            entityManager.persist(betGame1);
            entityManager.persist(betGame2);

        }

        entityManager.getTransaction().commit();
    }


}

