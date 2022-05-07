 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsul.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author 20201PF.CC0165
 */
public class Band implements Serializable {
    private String name;
    private List<Musician> members;
    
    public Band(String name) {
        this.name = name;
        this.members = new ArrayList<>();
    }
    
    public void addMember(Musician member) {
        this.members.add(member);
    }
    
    public void addMembers(String[] members) {
        for (String member : members) {
            String[] member_info = member.split(",");
            
            for (String info : member_info) {
                info = info.trim();
            }
            
            Musician musician = new Musician(member_info[0], member_info[1]);
            
            addMember(musician);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Musician> getMembers() {
        return members;
    }

    public void setMembers(List<Musician> members) {
        this.members = members;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.name);
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
        final Band other = (Band) obj;
        return Objects.equals(this.name, other.name);
    }
}
