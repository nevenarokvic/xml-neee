package com.example.demo.model;


import com.sun.xml.internal.ws.developer.Serialization;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Smestaj implements Serializable {

    @Id
    long id;
    String naziv;
    String opis;
    String usluge;
    String tip;
    String kategorija;
    String lokacija;
    //triger polje koje se racuna svaki put kada korisnik ostavi/promeni ocenu
    float ocena;


    @ManyToOne
    User agent;

    public Smestaj(){}

    public Smestaj(String naziv, String opis, String usluge, String tip, String kategorija, String lokacija, float ocena) {
        this.naziv = naziv;
        this.opis = opis;
        this.usluge = usluge;
        this.tip = tip;
        this.kategorija = kategorija;
        this.lokacija = lokacija;
        this.ocena = ocena;
    }

    public User getAgent() {
        return agent;
    }

    public void setAgent(User agent) {
        this.agent = agent;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getUsluge() {
        return usluge;
    }
    public void setUsluge(String usluge) {
        this.usluge = usluge;
    }
    public String getTip() {
        return tip;
    }
    public void setTip(String tip) {
        this.tip = tip;
    }
    public String getKategorija() {
        return kategorija;
    }
    public void setKategorija(String kategorija) {
        this.kategorija = kategorija;
    }
    public float getOcena() {
        return ocena;
    }
    public void setOcena(float ocena) {
        this.ocena = ocena;
    }
    public String getNaziv() {
        return naziv;
    }
    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
    public String getOpis() {
        return opis;
    }
    public void setOpis(String opis) {
        this.opis = opis;
    }
    public String getLokacija() { return lokacija; }
    public void setLokacija(String lokacija) { this.lokacija = lokacija; }

}