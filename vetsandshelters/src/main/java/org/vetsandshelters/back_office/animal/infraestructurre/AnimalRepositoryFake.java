package org.vetsandshelters.back_office.animal.infraestructurre;

import org.vetsandshelters.back_office.animal.domain.Animal;
// import org.vetsandshelters.back_office.animal.domain.AnimalCollection;
import org.vetsandshelters.back_office.animal.domain.AnimalRepository;
// import org.vetsandshelters.back_office.animal.domain.Criteria;

public class AnimalRepositoryFake implements AnimalRepository {

    @Override
    public Animal getById(Integer id) {
        return new Animal(1, "Pepito", "Pepito es un perro muy bueno", null);
    }

    // @Override
    // public AnimalCollection getBy(Criteria criteria) {
    // // TODO Auto-generated method stub
    // throw new UnsupportedOperationException("Unimplemented method 'getBy'");
    // }

    @Override
    public int add(Animal animal) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'add'");
    }

    @Override
    public int update(Animal animal) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

}
