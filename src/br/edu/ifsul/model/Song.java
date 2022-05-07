/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsul.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Objects;

/**
 *
 * @author 20201PF.CC0165
 */
public class Song implements Serializable {
    private String name;
    private String album;
    private Band band;
    private Calendar release_moment;
    
    public Song(String name, String album, Band band) {
        this.release_moment = Calendar.getInstance();
        this.name = name;
        this.album = album;
        this.band = band;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public Band getBand() {
        return band;
    }

    public void setBand(Band band) {
        this.band = band;
    }

    public Calendar getRelease_moment() {
        return release_moment;
    }

    public void setRelease_moment(Calendar release_moment) {
        this.release_moment = release_moment;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Song other = (Song) obj;
        return Objects.equals(this.name, other.name);
    }
}
 