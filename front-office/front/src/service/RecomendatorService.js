import Manager from 'socket.io-client';
const manager = Manager('http://localhost:4321');
export default class RecomendatorService {
    getRecomendations(formData) {
        let recomendations = {};
        manager.emit('', formData);
        manager.on('', (data) => {
            console.log(data);
            recomendations = data;
        });
        return {
            status: "OK",
            response: recomendations
        };
    }
}