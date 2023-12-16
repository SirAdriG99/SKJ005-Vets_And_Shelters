package org.vetsandshelters.animal.domain;

public interface AnimalRepository {
    public Animal getById(Integer id);

    public AnimalCollection getBy(AnimalCriteria criteria);

    public int add(Animal animal);

    public int update(Animal animal);
}
