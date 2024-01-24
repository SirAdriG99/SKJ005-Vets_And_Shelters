package org.vetsandshelters.breed.domain;

public interface BreedRepository {
    public Breed getById(int id);
    public BreedCollection getByCriteria(BreedCriteria criteria);
    public int store(Breed breed);
    public int update(Breed breed);
}
