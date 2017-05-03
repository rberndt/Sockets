# TCPsocketCPP

Example of how to create a TCP connection using C++

## Server

First compile and run the server.cpp file specifying the port to listen to:

    g++ server.cpp -o server.o

    ./server.o 20001

## Client

After the server is running, open a new terminal, then compile and run the
client.cpp file specifying the hostname and the port to connect to:

    g++ client.cpp -o client.o

    ./client.o 127.0.0.1 20001
