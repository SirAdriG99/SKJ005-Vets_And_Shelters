// TODO: Use of validations
import axios from 'axios';
export default class AnimalService {
    getAnimalById(id) {
        let url = '/animal/' + id;
        let animalData = {};
        axios.get(url)
            .then((response) => {
                console.log(response.data)
                animalData = response.data.animal;
            })
            .catch((error) => {
                console.log(error);
                return error
            });
        return animalData;
    }

    getFilteredAnimals() {

    }

    storeAnimal(animal) {
        let url = '/animal';
        axios.post(url, animal)
            .then((response) => {
                console.log(response.data)
                return response.data.id;
            })
            .catch((error) => {
                console.log(error);
                return error
            });
    }

}