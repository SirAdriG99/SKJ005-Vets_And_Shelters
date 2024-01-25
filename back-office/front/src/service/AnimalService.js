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
                return {
                    status: "ERROR",
                    response: error
                }
            });
        return {
            status: "OK",
            response: animalData
        };
    }

    // TODO: put specific parameters
    getFilteredAnimals(filter, pagination) {
        let url = '/animal';
        let animalData = {};
        axios.get(url, { params: { filter: filter, pagination: pagination } })
            .then((response) => {
                console.log(response.data)
                animalData = response.data;
            })
            .catch((error) => {
                console.log(error);
                return {
                    status: "ERROR",
                    response: error
                }
            });
        return {
            status: "OK",
            response: animalData
        };
    }

    async storeAnimal(animal) {
        console.log(animal)
        let url = '/animal';
        axios.post(url, animal)
            .then((response) => {
                console.log(response.data)
                return {
                    status: "OK",
                    response: response.data.id
                };
            })
            .catch((error) => {
                console.log(error);
                return {
                    status: "ERROR",
                    response: error
                }
            });
    }

    updateAnimal(animal) {
        let url = '/animal/' + animal.id;
        axios.put(url, animal)
            .then((response) => {
                console.log(response.data)
                return {
                    status: "OK",
                    response: response.data.id
                };
            })
            .catch((error) => {
                console.log(error);
                return {
                    status: "ERROR",
                    response: error
                }
            });
    }

}