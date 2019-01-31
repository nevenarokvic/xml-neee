package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Data
public class Zauzetost implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "smestaj_id", nullable = false)
    Smestaj smestaj;
    Date startDate;
    Date endDate;

    public Zauzetost(){}

    public Zauzetost(Smestaj smestaj, Date startDate, Date endDate) {
        this.smestaj = smestaj;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public long getId() {
        return id;
    }

    public Smestaj getSmestaj() {
        return smestaj;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }



    public void setId(long id) {
        this.id = id;
    }

    public void setSmestaj(Smestaj smestaj) {
        this.smestaj = smestaj;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }


    @Override
    public String toString() {
        return "Zauzetost{" +
                "id=" + id +
                ", smestaj=" + smestaj +
                ", startDate=" + startDate +
                ", endDate=" + endDate +

                '}';
    }
}
