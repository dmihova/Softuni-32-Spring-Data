package Entities;

import jakarta.persistence.*;

@Entity
@Table(name ="bet_games")
public class BetGame {
    @Id
    @ManyToOne
    @JoinColumn(name ="game_id")
    private Game game;

    @Id
    @ManyToOne
    @JoinColumn(name ="bet_id")
    private Bet bet;

    @ManyToOne
    @JoinColumn(name ="result_prediction")
    private ResultPrediction resultPrediction;

    public BetGame() {
    }

    public BetGame(Game game, Bet bet, ResultPrediction resultPrediction) {
        this();
        this.game = game;
        this.bet = bet;
        this.resultPrediction = resultPrediction;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Bet getBet() {
        return bet;
    }

    public void setBet(Bet bet) {
        this.bet = bet;
    }

    public ResultPrediction getResultPrediction() {
        return resultPrediction;
    }

    public void setResultPrediction(ResultPrediction resultPrediction) {
        this.resultPrediction = resultPrediction;
    }
}
