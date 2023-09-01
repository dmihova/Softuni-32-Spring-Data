package su.L09XmlLab.model.dto;

import com.google.gson.annotations.Expose;

import java.time.LocalDate;

public class DateDTO {
    @Expose
    private LocalDate date;

    public DateDTO() {
    }
    public DateDTO(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
