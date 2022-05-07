/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsul.model;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author 20201PF.CC0165
 */
public class Client implements Serializable {
    private String name;
    private String email;
    private Song current_song;
    
    public Client() {
        
    }
    
    public void Listen(Song song) {
        
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Song getCurrent_song() {
        return current_song;
    }

    public void setCurrent_song(Song current_song) {
        this.current_song = current_song;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.name);
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
        final Client other = (Client) obj;
        return Objects.equals(this.name, other.name);
    }
    
    
}
