package org.vetsandshelters.animal.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Entity
@Table(name = "breed")
public class Animal_Breed {
    @Id
    private int id;
    private String name;

    @Transient
    public static final Animal_Breed NOT_FOUND = new Animal_Breed(-1, "NO_FOUND");

    public Animal_Breed(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Animal_Breed(int id) {
        this.id = id;
    }

    public Animal_Breed() {
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
