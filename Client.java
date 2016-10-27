import java.net.*;
import java.util.*;
import java.net.Socket;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.SocketException;

public class Client{

  public static void main(String [ ] args) throws IOException{

    //Check correct number of arguments
    if(args.length != 2){
      System.out.println("To use type : java Client ip_adress_of_server port message");
      System.exit(1);
    }

    BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
    char[] inputArray;

    try{
      //Create and connect the socket
      DatagramSocket socket = new DatagramSocket();
      InetAddress address = InetAddress.getByName(args[0]);
      int port = Integer.parseInt(args[1]);

      do{
        byte[] car = new byte[1];
        //Ask the user what he want to send to the server
        inputArray = (bufferRead.readLine()).toCharArray();
        for (int i = 0; i<inputArray.length; i++){
          car[0] = (byte)inputArray[i];
          DatagramPacket dp = new DatagramPacket(car, 1, address, port);
          //Send to the server
          socket.send(dp);
        }




        //Listen for answer
        for(int i = 0 ; i<inputArray.length ; i++){
          byte[] buffer = new byte[1];
          DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
          socket.receive(reply);
          String recv = new String(reply.getData(), 0, reply.getLength());
          System.out.println("Recieved echo: " + recv);
        }

      }while(inputArray[0] != 'q');
      //End when end of client character

      System.out.println("Disconnected from the server");

      //Close the connection
      socket.close();
    }
    //Catch the possible errors
    catch(ConnectException e){
      System.out.println("Error : can't connect to the server");
      System.exit(1);
    }
    catch(SocketException e){
      System.out.println("Disconnected from the server");
    }
    catch(IOException e)
    {
      e.printStackTrace();
    }
  }
}
