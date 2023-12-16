package org.vetsandshelters.animal.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Entity
public class Sex {
    @Id
    private int id;
    private String name;

    public Sex(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Sex() {
        super();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
