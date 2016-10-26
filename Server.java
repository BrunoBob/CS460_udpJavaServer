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
    DatagramSocket serverSocket = new DatagramSocket(1234);
    System.out.println("Waiting for Client connection...");
    new Thread(new Server(serverSocket)).start(); //Start the reading thread
  }
  public void run() {

    try{
      byte[] car = new byte[1];
      System.out.println("Ready to echo:");

      while(true){
        DatagramPacket receivedPacket = new DatagramPacket(car, car.length);
        serverSocket.receive(receivedPacket);
        String data = new String(receivedPacket.getData());
        System.out.println(data);
        car = data.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(car, car.length, receivedPacket.getAddress(), receivedPacket.getPort());
        serverSocket.send(sendPacket);
      }
    }catch(IOException e){
      System.out.println(e);
    }
  }
}
