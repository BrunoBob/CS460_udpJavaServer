import java.net.*;
import java.io.*;


public class Server implements Runnable {
  //create the client socket
  DatagramSocket serverSocket;
  Server(DatagramSocket serverSocket) {
    this.serverSocket = serverSocket;
  }


  public static void main(String args[])

  //create the server socket and start listening for client connections
  throws Exception {
    DatagramSocket serverSocket = new DatagramSocket(1234); //Create the socket
    new Thread(new Server(serverSocket)).start(); //Start the reading thread
  }
  public void run() {

    try{
      byte[] car = new byte[1];
      System.out.println("Ready to echo:");

      while(true){
        DatagramPacket receivedPacket = new DatagramPacket(car, car.length);
        serverSocket.receive(receivedPacket); //Read a new packet
        String data = new String(receivedPacket.getData()); //Get the character in the packet
        System.out.println("Received character : " + data);
        car = data.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(car, car.length, receivedPacket.getAddress(), receivedPacket.getPort());
        serverSocket.send(sendPacket); //Send back the character read
      }
    }catch(IOException e){
      System.out.println(e);
    }
  }
}
