package org.vetsandshelters.animal.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import jakarta.persistence.*;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Entity
@Table(name = "breed")
public class Animal_Breed {
    @Id
    private int id;
    private String name;
    @Column(name = "is_exotic")
    private boolean exotic;
    @Column(name = "space_need")
    private String spaceNeed;
    @Column(name = "activity_need")
    private String activityNeed;
    @Column(name = "alimentation_need")
    private String alimentationNeed;
    @Column(name = "dangerous_race")
    private boolean dangerous;
    @Column(name = "time_dedication_need")
    private String timeDedicationNeed;

    public Animal_Breed(int id, String name, boolean exotic, String spaceNeed, String activityNeed, String alimentationNeed, boolean dangerous, String timeDedicationNeed) {
        this.id = id;
        this.name = name;
        this.exotic = exotic;
        this.spaceNeed = spaceNeed;
        this.activityNeed = activityNeed;
        this.alimentationNeed = alimentationNeed;
        this.dangerous = dangerous;
        this.timeDedicationNeed = timeDedicationNeed;
    }

    @Transient
    public static final Animal_Breed NOT_FOUND = new Animal_Breed(-1, "Not found", false, "", "", "", false, "");


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
                "id=" + id + '\'' +
                ", name='" + name + '\'' +
                ", exotic='" + exotic + '\'' +
                ", spaceNeed='" + spaceNeed + '\'' +
                ", activityNeed='" + activityNeed + '\'' +
                ", alimentationNeed='" + alimentationNeed + '\'' +
                ", dangerous='" + dangerous + '\'' +
                ", timeDedicationNeed='" + timeDedicationNeed + '\'' +
                '}';
    }

}
