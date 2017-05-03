//first start the server
//then the client
//./server.o 20001
//./client.o 127.0.0.1 20001

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <netdb.h>
#include <string>

void error(const char *msg)
{
    perror(msg);
    exit(0);
}

int main(int argc, char *argv[])
{
    int sockfd, portno, n;
    struct sockaddr_in serv_addr;
    struct hostent *server;

    char buffer[256];
    std::string serverMsg;
    char replyMsg[256];
    std::string reply;

    if (argc < 3) {
       fprintf(stderr,"usage %s hostname port\n", argv[0]);
       exit(0);
    }
    portno = atoi(argv[2]);

    sockfd = socket(AF_INET, SOCK_STREAM, 0);
    if (sockfd < 0) 
        error("ERROR opening socket");

    server = gethostbyname(argv[1]);
    if (server == NULL) {
        fprintf(stderr,"ERROR, no such host\n");
        exit(0);
    }

    bzero((char *) &serv_addr, sizeof(serv_addr));
    serv_addr.sin_family = AF_INET;
    bcopy((char *)server->h_addr, 
         (char *)&serv_addr.sin_addr.s_addr,
         server->h_length);
    serv_addr.sin_port = htons(portno);

    if (connect(sockfd, (struct sockaddr *) &serv_addr, sizeof(serv_addr)) < 0) 
        error("ERROR connecting");


    //where the messages are read and written
    reply = "Knock Knock\r\n";
    strncpy(replyMsg, reply.c_str(), sizeof(replyMsg));
    replyMsg[sizeof(replyMsg) - 1] = 0;
    n = write(sockfd, replyMsg, strlen(replyMsg));
    if (n < 0) 
         error("ERROR writing to socket");

    bzero(buffer,256);
    n = read(sockfd, buffer, 255);
    if (n < 0) 
         error("ERROR reading from socket");
    printf("First server message: %s\n", buffer);
   
    reply = "Dwain\r\n";
    strncpy(replyMsg, reply.c_str(), sizeof(replyMsg));
    replyMsg[sizeof(replyMsg) - 1] = 0;
    n = write(sockfd, replyMsg, strlen(replyMsg));
    if (n < 0) 
         error("ERROR writing to socket");

    bzero(buffer,256);
    n = read(sockfd, buffer, 255);
    if (n < 0) 
         error("ERROR reading from socket");
    printf("Second server message: %s\n", buffer);
   
    reply = "Dwain the tub, I'm dwowning\r\n";
    strncpy(replyMsg, reply.c_str(), sizeof(replyMsg));
    replyMsg[sizeof(replyMsg) - 1] = 0;
    n = write(sockfd, replyMsg, strlen(replyMsg));
    if (n < 0) 
         error("ERROR writing to socket");


    close(sockfd);
    return 0;
}
