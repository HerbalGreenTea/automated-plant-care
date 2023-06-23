import socket

from classifier import PlantCareClassifier


class PlantCareServer:
    def __init__(self):
        self.classifier = PlantCareClassifier()

    def send_classification_result(self, classification_result):
        tcp_socket = socket.create_connection(("192.168.1.105", 8081))

        try:
            tcp_socket.sendall(str.encode(classification_result))
        finally:
            print("Closing socket")
            tcp_socket.close()

    def start_listen(self):
        tcp_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        server_address = ('192.168.1.105', 8080)
        tcp_socket.bind(server_address)
        tcp_socket.listen(1)

        while True:
            print("Waiting for connection")
            connection, client = tcp_socket.accept()

            try:
                print("Connected to client IP: {}".format(client))

                while True:
                    data = connection.recv(32)
                    print("Received data: {}".format(data))

                    if len(str(data)) == "get_prediction":
                        result = self.classifier.get_prediction("good_violets", "violets")
                        self.send_classification_result(result)

                    if not data:
                        break
            finally:
                connection.close()


if __name__ == '__main__':
    plant_care_server = PlantCareServer()
    plant_care_server.start_listen()

