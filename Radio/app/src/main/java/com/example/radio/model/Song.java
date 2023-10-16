package com.example.radio.model;

import java.util.HashMap;
import java.util.Map;

public class Song {

    public String avgbewertung;
    public String title;
    public String interpret;
    public String album;
    public int länge;
    public int comment = 0;

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

    public int getComment() {
        return comment;
    }

    public void setComment(int comment) {
        this.comment = comment;
    }
}