package su.l07objectmappinglab.model.dto;

import com.google.gson.annotations.Expose;

import java.time.LocalDate;

public class DateDTO {
    @Expose
    private LocalDate date;

    public DateDTO(LocalDate date) {
        this.date = date;
    }

    public DateDTO() {
    }
}
