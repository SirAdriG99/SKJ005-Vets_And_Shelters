// TODO: Use of validations
import axios from 'axios';
export default class AnimalService {
    getAnimalById(id) {
        let url = '/animal/' + id;
        let animalData = {};
        let toreturn = {}
        axios.get(url)
            .then((response) => {
                console.log(response.data)
                animalData = response.data.animal;
                toreturn = {
                    status: "OK",
                    response: animalData
                };
            })
            .catch((error) => {
                console.log(error);
                toreturn ={
                    status: "ERROR",
                    response: error
                }
            });
        return toreturn;
    }

    // TODO: put specific parameters
    getFilteredAnimals(pageNum, size, order, sort, name, color, sexId, breedId, procedenceTypeId, animalStatusId) {
        let url = '/animal/filter';
        let animalData = {};
        axios.get(url, { params: {
                pageNum: pageNum,
                size: size,
                order: order,
                sort: sort,
                name: name,
                color: color,
                sexId: sexId,
                breedId: breedId,
                procedenceTypeId: procedenceTypeId,
                animalStatusId: animalStatusId
            } })
            .then((response) => {
                console.log(response.data)
                animalData = response.data.animals;
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