package org.vetsandshelters.breed.domain;

public interface BreedRepository {
    public Breed getById(int id);
    public BreedCollection getBy(BreedCriteria criteria);
    public BreedCollection getAll();
    public int store(Breed breed);
    public int update(Breed breed);
}
