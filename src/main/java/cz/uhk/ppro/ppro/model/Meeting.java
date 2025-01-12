package cz.uhk.ppro.ppro.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;

@Entity
@Table(name = "meetings")
public class Meeting {

    @Id
    @GeneratedValue
    private long id;

    @NotBlank
    private Date date;

    //TODO: list of participants
    //TODO: nastavit leadera


    public Meeting(long id, Date date) {
        this.id = id;
        this.date = date;
    }

    public Meeting() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
