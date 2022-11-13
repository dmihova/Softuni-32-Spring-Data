package Entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name ="bets")
public class Bet extends BaseEntity{

    @Column(name = "bet_money")
    private BigDecimal betMoney;

    @Column (name = "date_time")
    private Date dateTime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    public Bet() {
        super();
    }

    public Bet(BigDecimal betMoney, Date dateTime, User user) {
        this();
        this.betMoney = betMoney;
        this.dateTime = dateTime;
        this.user = user;
    }

    public BigDecimal getBetMoney() {
        return betMoney;
    }

    public void setBetMoney(BigDecimal betMoney) {
        this.betMoney = betMoney;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
