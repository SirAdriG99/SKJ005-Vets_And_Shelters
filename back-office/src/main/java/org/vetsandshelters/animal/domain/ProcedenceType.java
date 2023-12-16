package org.vetsandshelters.animal.domain;

import com.oracle.graal.compiler.enterprise.phases.strings.s;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ProcedenceType {
    @Id
    private int id;
    private String name;

    public ProcedenceType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public ProcedenceType() {
        super();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
