package Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name ="result_prediction")
public class ResultPrediction extends BaseEntity{

    @Column(name = "prediction",nullable = false)
    private String prediction;
    //Prediction (possible values - Home Team Win, Draw Game, Away Team Win)


    public ResultPrediction() {
        super();
    }

    public ResultPrediction(String prediction) {
        this();
        this.prediction = prediction;
    }

    public String getPrediction() {
        return prediction;
    }

    public void setPrediction(String prediction) {
        this.prediction = prediction;
    }
}
