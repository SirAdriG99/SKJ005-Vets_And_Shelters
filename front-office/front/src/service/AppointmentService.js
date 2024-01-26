import axios from 'axios';
export default class AppointmentService {
    storeAppointment(appointmentData) {
        let url = '/appointment';
        let appointmentResponse = {};
        axios.post(url, appointmentData)
            .then((response) => {
                console.log(response.data)
                appointmentResponse = response.data;
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
            response: appointmentResponse
        };
    }
}