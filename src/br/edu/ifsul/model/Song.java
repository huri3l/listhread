package br.edu.ifsul.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Objects;

/**
 * Song is the class that carry the informations about the song that the client
 * will listen to
 * 
 * @author Huriel Ferreira Lopes
 */
public class Song implements Serializable {
    private String name;
    private String album;
    private String lyrics;
    private Band band;
    private Calendar release_moment;
    
    public Song(String name, String album, Band band, String lyrics) {
        this.release_moment = Calendar.getInstance();
        this.name = name;
        this.album = album;
        this.band = band;
        this.lyrics = lyrics;
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
    
    public String getLyrics() {
        return lyrics;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
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
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Song other = (Song) obj;
        return Objects.equals(this.name, other.name);
    }   
}
 