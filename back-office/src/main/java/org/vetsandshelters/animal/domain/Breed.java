package org.vetsandshelters.animal.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Entity
public class Breed {
    @Id
    private int id;
    private String name;

    @Transient
    public static final Breed NOT_FOUND = new Breed(-1, "NO_FOUND");

    public Breed(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Breed(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Breed{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

}
