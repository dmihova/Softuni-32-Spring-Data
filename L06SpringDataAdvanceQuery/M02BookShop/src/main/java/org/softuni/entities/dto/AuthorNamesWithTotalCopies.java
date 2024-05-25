package org.softuni.entities.dto;

public interface AuthorNamesWithTotalCopies {
    String getFirstName();
    String getLastName();
    long getTotalCopies();

    default String getInfo() {
        return String.format("%s %s %d",
                 getFirstName(),
                 getLastName(),
                 getTotalCopies())
                ;
    }


}
