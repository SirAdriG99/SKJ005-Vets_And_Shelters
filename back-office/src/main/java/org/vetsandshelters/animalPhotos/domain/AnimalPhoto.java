package org.vetsandshelters.animalPhotos.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import jakarta.persistence.*;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Entity
@Table(name = "animal_photos")
public class AnimalPhoto {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "animal_photos_id_seq")
    private Integer id;
    private String name;
    private String url;
    @Column(name = "animal_id")
    private int animalId;

    @Transient
    public static final AnimalPhoto NOT_FOUND = new AnimalPhoto(-1, "PHOTO NOT FOUND", "NOT FOUND", -1);

    public AnimalPhoto(Integer id, String name, String url, int animalId) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.animalId = animalId;
    }

    public AnimalPhoto() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUrl(){
        return url;
    }

    public int getAnimalId(){
        return animalId;
    }

    @Override
    public String toString() {
        return "AnimalPhoto{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", animalId='" + animalId + '\'' +
                '}';
    }
}
