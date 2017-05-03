//TCP Server
import java.io.*;
import java.net.*;

public class server
{
  public static void main(String[] args) throws Exception
  {
    if(args.length < 1)
    {
      System.out.println("Please include port number");
      System.exit(1);
    }
    int port = Integer.parseInt(args[0]);
    String message;
    
    //Create Server Socket
    ServerSocket myServerSocket = new ServerSocket(port);

    //loop continuously until terminate is received
    while(true)
    {
      //Create the client connection
      Socket mySocket = myServerSocket.accept();
      System.out.println("get connection from " + mySocket.getRemoteSocketAddress().toString());
      BufferedReader fromClient = new BufferedReader(new InputStreamReader(mySocket.getInputStream()));
      DataOutputStream toClient = new DataOutputStream(mySocket.getOutputStream());

      toClient.writeBytes("Hello!\n");

      boolean connected = true;
      //loop continuously until exit or terminate is received
      while(connected)
      {
        //get the message from the client
        message = fromClient.readLine();
        //delimit the message by spaces
        String[] msg = message.split(" ");
        //if operation is add
        if(msg[0].equals("add"))
        {
          //check for less than two inputs
          if(msg.length < 3)
          {
            System.out.println("get: " + message + ", return -2");
            toClient.writeBytes("-2" + '\n');
          }
          //check for more than four inputs
          else if(msg.length > 5)
          {
            System.out.println("get: " + message + ", return -3");
            toClient.writeBytes("-3" + '\n');
          }
          else
          {
            //check for non numbers and compute addition
            boolean allNums = true;
            int sum = 0;
            for(int i = 1; i < msg.length; i++)
            {
              try
              {
                sum += Integer.parseInt(msg[i]);
              }
              catch( Exception e )
              {
                allNums = false;
              }
            }

            //if all inputs are integers
            if(allNums)
            {
              System.out.println("get: " + message + ", return " + Integer.toString(sum));
              toClient.writeBytes(Integer.toString(sum) + '\n');
            }
            //if not all inputs are integers
            else
            {
              System.out.println("get: " + message + ", return -4");
              toClient.writeBytes("-4" + '\n');
            }
          }
        }
        //if operation is subtract
        else if(msg[0].equals("subtract"))
        {
          //check for less than two inputs
          if(msg.length < 3)
          {
            System.out.println("get: " + message + ", return -2");
            toClient.writeBytes("-2" + '\n');
          }
          //check for more than four inputs
          else if(msg.length > 5)
          {
            System.out.println("get: " + message + ", return -3");
            toClient.writeBytes("-3" + '\n');
          }
          else
          {
            //check for non numbers and compute subtraction
            boolean allNums = true;
            int sub = 0;
            for(int i = 1; i < msg.length; i++)
            {
              try
              {
                if(i == 1)
                {
                  sub = Integer.parseInt(msg[i]);
                }
                else
                {
                  sub -= Integer.parseInt(msg[i]);
                }
              }
              catch( Exception e )
              {
                allNums = false;
              }
            }

            //if all inputs are integers
            if(allNums)
            {
              System.out.println("get: " + message + ", return " + Integer.toString(sub));
              toClient.writeBytes(Integer.toString(sub) + '\n');
            }
            //if not all inputs are integers
            else
            {
              System.out.println("get: " + message + ", return -4");
              toClient.writeBytes("-4" + '\n');
            }
          }
        }
        //if operation is multiply
        else if(msg[0].equals("multiply"))
        {
          //check for less than two inputs
          if(msg.length < 3)
          {
            System.out.println("get: " + message + ", return -2");
            toClient.writeBytes("-2" + '\n');
          }
          //check for more than four inputs
          else if(msg.length > 5)
          {
            System.out.println("get: " + message + ", return -3");
            toClient.writeBytes("-3" + '\n');
          }
          else
          {
            //check for non numbers and compute multiplication
            boolean allNums = true;
            int mult = 0;
            for(int i = 1; i < msg.length; i++)
            {
              try
              {
                if(i == 1)
                {
                  mult = Integer.parseInt(msg[i]);
                }
                else
                {
                  mult *= Integer.parseInt(msg[i]);
                }
              }
              catch( Exception e )
              {
                allNums = false;
              }
            }

            //if all inputs are integers
            if(allNums)
            {
              System.out.println("get: " + message + ", return " + Integer.toString(mult));
              toClient.writeBytes(Integer.toString(mult) + '\n');
            }
            //if not all inputs are integers
            else
            {
              System.out.println("get: " + message + ", return -4");
              toClient.writeBytes("-4" + '\n');
            }
          }
        }
        //Check for bye
        else if(msg[0].equals("bye"))
        {
          System.out.println("get: " + message + ", return -5");
          toClient.writeBytes("-5" + '\n');
          //close the client connection
          mySocket.close();
          connected = false;
        }
        //Check for terminate
        else if(msg[0].equals("terminate"))
        {
          System.out.println("get: " + message + ", return -5");
          toClient.writeBytes("-5" + '\n');
          //close the client connection
          mySocket.close();
          //close the server
          myServerSocket.close();
          //gracefully exit
          System.exit(1);
        }
        //Error code -1: incorrect operation command.
        else
        {
          System.out.println("get: " + message + ", return -1");
          toClient.writeBytes("-1" + '\n');
        }
      }
    }
  }
}
