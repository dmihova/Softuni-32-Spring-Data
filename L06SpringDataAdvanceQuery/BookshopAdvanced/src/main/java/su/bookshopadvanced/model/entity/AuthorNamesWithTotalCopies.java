package su.bookshopadvanced.model.entity;

public interface AuthorNamesWithTotalCopies {
    String getFirstName();

    String getLastName();

    long getTotalCopies();

    default String getInfo() {
        return getFirstName() + " - " +
                getLastName() + " - " +
                getTotalCopies()
                ;
    }


}
