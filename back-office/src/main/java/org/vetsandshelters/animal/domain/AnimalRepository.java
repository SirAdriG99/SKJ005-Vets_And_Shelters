package org.vetsandshelters.animal.domain;

public interface AnimalRepository {
    public Animal getById(Integer id);

    public AnimalCollection getBy(AnimalCriteria criteria);

    public int store(Animal animal);

    public int update(Animal animal);
}
