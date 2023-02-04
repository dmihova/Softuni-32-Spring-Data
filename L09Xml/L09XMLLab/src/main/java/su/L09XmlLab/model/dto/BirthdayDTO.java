package su.L09XmlLab.model.dto;

import java.time.LocalDate;

public class BirthdayDTO {
    private String date;

    public BirthdayDTO() {
    }

    public BirthdayDTO(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
