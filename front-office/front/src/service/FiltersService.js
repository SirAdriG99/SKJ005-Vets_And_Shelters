import axios from 'axios';
export default class FiltersService {
    getAnimalStatusSelector() {
        let url = '/animalStatus/select';
        let animalStatusData = {};
        axios.get(url)
            .then((response) => {
                console.log(response.data)
                animalStatusData = response.data;
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
            response: animalStatusData
        };
    }
    getBreedSelector() {
        let url = '/breed/selectList';
        let breedData = {};
        axios.get(url)
            .then((response) => {
                console.log(response.data)
                breedData = response.data;
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
            response: breedData
        };
    }
    getProcedenceTypeSelector() {
        let url = '/procedenceType/selectList';
        let procedenceTypeData = {};
        axios.get(url)
            .then((response) => {
                console.log(response.data)
                procedenceTypeData = response.data;
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
            response: procedenceTypeData
        };
    }
    getSexSelector() {
        let url = '/sex/select';
        let sexData = {};
        axios.get(url)
            .then((response) => {
                console.log(response.data)
                sexData = response.data;
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