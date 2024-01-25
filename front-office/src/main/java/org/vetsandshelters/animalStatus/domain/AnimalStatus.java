package org.vetsandshelters.animalStatus.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Entity
@Table(name = "animal_status")
public class AnimalStatus {
    @Id
    private int id;
    private String name;

    @Transient
    public static final AnimalStatus NOT_FOUND = new AnimalStatus(-1, "");

    public AnimalStatus(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public AnimalStatus() {
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
        return "AnimalStatus{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
