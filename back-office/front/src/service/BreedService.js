import axios from 'axios';
export default class BreedService {
    getBreedById(id) {
        let url = '/breed/' + id;
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

    getFilteredBreeds(pageNum, size, order, sort, name, dangerous, exotic, activityNeed, spaceNeed, alimentationNeed, timeDedicationNeed){
        let url = '/animal/filter';
        let animalData = {};
        axios.get(url, { params: {
                pageNum: pageNum,
                size: size,
                order: order,
                sort: sort,
                name: name,
                dangerous: dangerous,
                exotic: exotic,
                activityNeed: activityNeed,
                spaceNeed: spaceNeed,
                alimentationNeed: alimentationNeed,
                timeDedicationNeed: timeDedicationNeed
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
}