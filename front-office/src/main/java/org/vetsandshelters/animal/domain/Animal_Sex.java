package org.vetsandshelters.animal.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Entity
@Table(name = "sex")
public class Animal_Sex {
    @Id
    private int id;
    private String name;

    @Transient
    public static final Animal_Sex NOT_FOUND = new Animal_Sex(-1, "");

    public Animal_Sex(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Animal_Sex(int id) {
        this.id = id;
    }

    public Animal_Sex() {
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
        return "Sex{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
