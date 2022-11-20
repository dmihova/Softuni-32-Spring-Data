package su.bookshopadvanced.model.entity;
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
