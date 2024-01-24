package org.vetsandshelters.animal.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "procedence_type")
public class Animal_ProcedenceType {
    @Id
    private int id;
    private String name;

    @Transient
    public static final Animal_ProcedenceType NOT_FOUND = new Animal_ProcedenceType(-1, "NO_FOUND");

    public Animal_ProcedenceType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Animal_ProcedenceType(int id) {
        this.id = id;
    }

    public Animal_ProcedenceType() {
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
        return "ProcedenceType{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
