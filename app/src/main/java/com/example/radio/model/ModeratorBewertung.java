package com.example.radio.model;

public class ModeratorBewertung {

    private String name="";
    private String kommentar="";
    private int sterne=1;

    public ModeratorBewertung(String name, String kommentar, int sterne) {
        this.name = name;
        this.kommentar = kommentar;
        this.sterne = sterne;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKommentar() {
        return kommentar;
    }

    public void setKommentar(String kommentar) {
        this.kommentar = kommentar;
    }

    public int getSterne() {
        return sterne;
    }

    public void setSterne(int sterne) {
        this.sterne = sterne;
    }
}
