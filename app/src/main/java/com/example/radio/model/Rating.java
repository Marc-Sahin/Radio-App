package com.example.radio.model;

public class Rating {
    private String userid;
    private String kommentar;
    private int sterne;

    public Rating() {

    }

    public Rating(String userid, String kommentar, int sterne) {
        this.userid = userid;
        this.kommentar = kommentar;
        this.sterne = sterne;
    }
    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
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
