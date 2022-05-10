package br.edu.ifsul.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * Client is the one using the app, that will listen to the songs
 * It holds informations about the client that is important to a few tasks
 * 
 * @author Huriel Ferreira Lopes
 */
public class Client implements Serializable {
    private String name;
    private String email;
    
    public Client(String name, String email) {
        this.name = name;
        this.email = email;
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
