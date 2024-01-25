package org.vetsandshelters.procedenceType.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Entity
@Table(name = "procedence_type")
public class ProcedenceType {
    @Id
    private int id;
    private String name;

    @Transient
    public static final ProcedenceType NOT_FOUND = new ProcedenceType(-1, "");

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

    @Override
    public String toString() {
        return "ProcedenceType{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
