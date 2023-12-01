package org.vetsandshelters.back_office.animal.domain;

public interface AnimalRepository {
    public Animal getById(Integer id);

    public AnimalCollection getBy(Criteria criteria);

    public int add(Animal animal);

    public int update(Animal animal);
}
