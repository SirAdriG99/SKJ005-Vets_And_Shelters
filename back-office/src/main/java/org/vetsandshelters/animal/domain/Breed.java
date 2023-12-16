package org.vetsandshelters.animal.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Entity
public class Breed {
    @Id
    private int id;
    private String name;

    public Breed(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Breed() {
        super();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
