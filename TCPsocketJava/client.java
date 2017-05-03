//TCP Server
import java.io.*;
import java.net.*;

public class client
{
  public static void main(String[] args) throws Exception
  {
    if(args.length < 2)
    {
      System.out.println("Please include ip address and port number");
      System.exit(1);
    }
    String ip = args[0];
    int port = Integer.parseInt(args[1]);
    String message;
    int i = 0;
    boolean connected = true;

    //used to get input from the user
    BufferedReader fromUser = new BufferedReader(new InputStreamReader(System.in));
    //Create the client socket
    Socket myClientSocket = new Socket(ip, port);

    //loop continously until exit or terminate is entered by the user
    while(connected)
    {
      BufferedReader fromServer = new BufferedReader(new InputStreamReader(myClientSocket.getInputStream()));
      DataOutputStream toServer = new DataOutputStream(myClientSocket.getOutputStream());

      //server will send "Hello!" after connecting
      if(i == 0)
      {
        message = fromServer.readLine();
        System.out.println("Server: " + message);
        i++;
      }

      //System.out.println("Please enter message for server:");
      //get input from user
      message = fromUser.readLine();
      //send user input to server
      toServer.writeBytes(message + '\n');
      //read from server
      message = fromServer.readLine();
      //if error code -1: incorrect operation command.
      if(message.equals("-1"))
      {
        System.out.println("receive: incorrect operation command.");
      }
      //if error code -2: number of inputs is less than two.
      else if(message.equals("-2"))
      {
        System.out.println("receive: number of inputs is less than two.");
      }
      //if error code -3: number of inputs is more than four.
      else if(message.equals("-3"))
      {
        System.out.println("receive: number of inputs is more than four.");        
      }
      //if error code -4: one or more of the inputs contain(s) non-number(s).
      else if(message.equals("-4"))
      {
        System.out.println("receive: one or more of the inputs contain(s) non-number(s).");       
      }
      //if error code -5: exit.
      else if(message.equals("-5"))
      {
        System.out.println("receive: exit.");
        myClientSocket.close();
        connected = false;
      }
      //if no error print the result
      else
      {
        System.out.println("receive: " + message);
      }
    }
  }
}
