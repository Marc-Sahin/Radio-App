package com.example.radio.model;

public class Song {


    public String titel="";
    public String interpret="";
    public String album="";
    public int   länge=0;
    public String url="";

    public int   veröffentlichung=0;
    public int tag=0;
    public Song() {

    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }


    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
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