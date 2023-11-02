package com.example.radio.model;

public class Song {

    public String avgbewertung;
    public String title;
    public String interpret;
    public String album;
    public int länge;

    public int veröffentlichung;
public String url;

    public Song() {

    }

    public String getAvgbewertung() {

        return avgbewertung;
    }

    public void setAvgbewertung(String avgbewertung) {
        this.avgbewertung = avgbewertung;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {

        this.title = title;
    }

    public String getInterpret() {
        return interpret;
    }

    public void setInterpret(String interpret) {
        this.interpret = interpret;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public int getLänge() {
        return länge;
    }

    public void setLänge(int länge) {
        this.länge = länge;
    }

    public String getUrl() {
        return url;
    }

    public int getVeröffentlichung() {
        return veröffentlichung;
    }

    public void setVeröffentlichung(int veröffentlichung) {
        this.veröffentlichung = veröffentlichung;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}