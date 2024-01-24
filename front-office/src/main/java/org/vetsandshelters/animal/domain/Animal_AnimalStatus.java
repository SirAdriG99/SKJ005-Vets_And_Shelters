package org.vetsandshelters.animal.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Entity
@Table(name = "animal_status")
public class Animal_AnimalStatus {
    @Id
    private int id;
    private String name;

    @Transient
    public static final Animal_AnimalStatus NOT_FOUND = new Animal_AnimalStatus(-1, "NO_FOUND");

    public Animal_AnimalStatus(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Animal_AnimalStatus(int id) {
        this.id = id;
    }

    public Animal_AnimalStatus() {
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
        return "AnimalStatus{"
                + "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
