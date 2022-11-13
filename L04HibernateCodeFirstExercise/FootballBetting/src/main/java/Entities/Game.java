package Entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name ="games")
public class Game extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "home_team")
    private Team homeTeam;

    @ManyToOne
    @JoinColumn(name = "away_team")
    private Team awayTeam;

    @Column (name = "home_goals")
    private int homeGoals;

    @Column (name = "away_goals")
    private int awayGoals;

    @Column (name = "date_time")
    private Date dateTime;

    @Column (name = "home_team_win_bet_rate")
    private double homeTeamWinBetRate;

    @Column (name = "away_team_win_bet_rate")
    private double awayTeamWinBetRate;

    @Column (name = "draw_team_win_bet_rate")
    private double drawTeamWinBetRate;

    @ManyToOne
    @JoinColumn(name = "round_id")
    private Round round;

    @ManyToOne
    @JoinColumn(name = "competition_id")
    private Competition competition;

    public Game() {
        super();
    }

    public Game(Team homeTeam, Team awayTeam, int homeGoals, int awayGoals, Date dateTime, double homeTeamWinBetRate, double awayTeamWinBetRate, double drawTeamWinBetRate, Round round, Competition competition) {
        this();
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeGoals = homeGoals;
        this.awayGoals = awayGoals;
        this.dateTime = dateTime;
        this.homeTeamWinBetRate = homeTeamWinBetRate;
        this.awayTeamWinBetRate = awayTeamWinBetRate;
        this.drawTeamWinBetRate = drawTeamWinBetRate;
        this.round = round;
        this.competition = competition;
    }
}
