package br.edu.ifsul.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * Musician is one of the persons that compose a Band
 * 
 * @author Huriel Ferreira Lopes
 */
public class Musician implements Serializable {
    private String name;
    private String role;
    
    public Musician(String name, String role) {
        this.name = name;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.name);
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
        final Musician other = (Musician) obj;
        return Objects.equals(this.name, other.name);
    }    
}
