package su.gamestore.model.game;

import java.math.BigDecimal;

public interface GameNamePriceDTO {
    String getTitle();
    BigDecimal getPrice();

    default String displayInfo() {
        return getTitle() + " " +getPrice();
    }
}
