package org.vetsandshelters.sex.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Entity
@Table(name = "sex")
public class Sex {
    @Id
    private int id;
    private String name;

    @Transient
    public static final Sex NOT_FOUND = new Sex(-1, "");

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

    @Override
    public String toString() {
        return "Sex{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
