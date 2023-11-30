package com.example.radio.model;

public class Playlist {
    private String playlistid;
    private int dauer;
    private String genre;
    private int tag;

    public Playlist() {
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        tag = tag;
    }

    public String getPlaylistid() {
        return playlistid;
    }

    public void setPlaylistid(String playlistid) {
        this.playlistid = playlistid;
    }

    public int getDauer() {
        return dauer;
    }

    public void setDauer(int dauer) {
        this.dauer = dauer;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}

