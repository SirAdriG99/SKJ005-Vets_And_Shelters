package org.vetsandshelters.breed.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import jakarta.persistence.*;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Entity
public class Breed {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "animal_id_seq")
    private Integer id;
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

    public Breed(
            Integer id,
            String name,
            boolean exotic,
            String spaceNeed,
            String activityNeed,
            String alimentationNeed,
            boolean dangerous,
            String timeDedicationNeed
    ) {
        this.id = id;
        this.name = name;
        this.exotic = exotic;
        this.spaceNeed = spaceNeed;
        this.activityNeed = activityNeed;
        this.alimentationNeed = alimentationNeed;
        this.dangerous = dangerous;
        this.timeDedicationNeed = timeDedicationNeed;
    }

    public Breed() {
        super();
    }

    @Transient
    public static final Breed NOT_FOUND = new Breed(-1, "Not found", false, "", "", "", false, "");

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isExotic() {
        return exotic;
    }

    public String getSpaceNeed() {
        return spaceNeed;
    }

    public String getActivityNeed() {
        return activityNeed;
    }

    public String getAlimentationNeed() {
        return alimentationNeed;
    }

    public boolean isDangerous() {
        return dangerous;
    }

    public String getTimeDedicationNeed() {
        return timeDedicationNeed;
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
