package Entities;

import jakarta.persistence.*;

@Entity
@Table(name ="player_statistics")
public class PlayerStatistic {
    @Id
    @ManyToOne
    @JoinColumn(name ="game_id")
    private Game game;

    @Id
    @ManyToOne
    @JoinColumn(name ="player_id")
    private Player player;

    @Column (name = "scored_goals")
    private short scoredGoals;

    @Column (name = "player_assists")
    private short playerAssists;

    @Column (name = "played_minutes")
    private short played_minutes_during_game;

    public PlayerStatistic() {

    }

    public PlayerStatistic(Game game, Player player, short scoredGoals, short playerAssists, short played_minutes_during_game) {
        this();
        this.game = game;
        this.player = player;
        this.scoredGoals = scoredGoals;
        this.playerAssists = playerAssists;
        this.played_minutes_during_game = played_minutes_during_game;
    }
}
