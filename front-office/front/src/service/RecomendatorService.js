import io from 'socket.io-client';

class RecomendatorService {
    constructor() {
        this.socket = io.connect('http://localhost:4321');
    }

    sendData(jsonData) {
        this.socket.emit('message', jsonData);
    }

    receiveData() {
        return new Promise((resolve, reject) => {
            this.socket.on('message', (data) => {
                if (typeof data === 'number') {
                    resolve(data);
                } else {
                    reject(new Error('Received data is not a number'));
                }
            });
        });
    }
}

export default RecomendatorService;