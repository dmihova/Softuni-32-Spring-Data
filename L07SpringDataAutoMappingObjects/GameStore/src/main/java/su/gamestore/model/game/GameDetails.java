package su.gamestore.model.game;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface GameDetails {
    String getTitle();

    BigDecimal getPrice();

    String getDescription();

    LocalDate getReleaseDate();


    default String displayInfo() {
        return String.format("Title: %s%n" +
                "Price: %.2f%n" +
                "Description: %s%n" +
                "Release date: %s", getTitle(), getPrice(), getDescription(), getReleaseDate());
    }
}
