package org.softuni.entities.dto;
import org.softuni.entities.enums.AgeRestriction;
import org.softuni.entities.enums.EditionType;

import java.math.BigDecimal;
public interface BookTitleTypeRestrictionPrice {

    String getTitle();
    EditionType getEditionType();
    AgeRestriction getAgeRestriction();
    BigDecimal getPrice();

    default String getInfo() {
        return String.format( "%s %s %s %.2f",
                getTitle(), getEditionType(), getAgeRestriction(), getPrice());
    }
}
