package su.L09XmlLab.model.entity;

import java.time.LocalDate;

public class Birthday {
    private LocalDate date;
    public Birthday() {
    }

    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
}
