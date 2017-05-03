# TCPsocketJava

Example of how to create a TCP connection using Java

Performs basic math functions including addition, subtraction, and multiplication on two to four numbers in order

Addition

    add 1 2 3 4

Subtraction

    sub 10 3

Multiplication

    multiply 2 5 7

The command "bye" will close the client socket, while allowing the server socket to wait for another client connection

    bye

The command "terminate" will close the client and server sockets

    terminate

## Server

First compile the server.java file and run it specifying the port to listen to:

    javac server.java

    java server 9999

## Client

After the server is running, open a new terminal, then compile the client.java file and run it specifying the hostname and the port to connect to:

    javac client.java

    java client 127.0.0.1 9999
