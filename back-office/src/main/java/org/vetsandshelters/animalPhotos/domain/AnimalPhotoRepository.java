package org.vetsandshelters.animalPhotos.domain;


public interface AnimalPhotoRepository {
    public AnimalPhoto getById(int id);

    public AnimalPhotoCollection getByCriteria(AnimalPhotoCriteria criteria);

//    public AnimalPhoto getAnimalPhotoByCriteria(AnimalPhotoCriteria criteria);

    public int store(AnimalPhoto animalPhoto);

    public boolean storeAll(AnimalPhotoCollection animalPhotoCollection);
}
