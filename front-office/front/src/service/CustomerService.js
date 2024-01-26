import axios from 'axios';
export default class CustomerService {
    storeCustomer(customer) {
        console.log(customer)
        let toReturn = {
            status: "ERROR",
            response: -1
        }
        let url = '/customer';
        axios.post(url, customer)
            .then((response) => {
                console.log(response.data)
                toReturn = {
                    status: "OK",
                    response: response.data.id
                };
            })
            .catch((error) => {
                console.log(error);
                toReturn= {
                    status: "ERROR",
                    response: error
                }
            });
        return toReturn;
    }

}
