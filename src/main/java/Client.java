import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client implements SocketConnection {


    protected Socket socket;

    protected ObjectInputStream inputStream;

    protected ObjectOutputStream outputStream;

    protected Scanner userInput;

    protected static String name;


    class serverReader extends Thread {

        public void run() {
            try {
                Object p;
                while ((p = inputStream.readObject()) != null) {
                    System.out.println(p);
                }

            } catch (Exception e) {
            }

        }
    }


    public Client(String hostName, int port) {

        try {

            socket = new Socket(hostName, port);

            userInput = new Scanner(System.in);

            System.out.println("Please enter your name? ");
            name = userInput.next();

            // sends output to the socket
            outputStream = new ObjectOutputStream(socket.getOutputStream());

            // takes input from the sockets
            inputStream = new ObjectInputStream(socket.getInputStream());


            new serverReader().start();

        } catch (UnknownHostException u) {
            System.out.println(u);
        } catch (IOException i) {
            System.out.println(i);
        }
    }


    public void communicate() {

        try {

            while (userInput.hasNextLine()) {
                String message = userInput.next();
                outputStream.writeObject(new Person(name, message));
                outputStream.flush();
            }
        } catch (IOException i) {
            System.out.println("Error " + i);
        }
    }


    //override close method
    public void closeConnections() {
        try {
            inputStream.close();
            outputStream.close();
            userInput.close();
            this.socket.close();
        } catch (IOException i) {
            System.out.println(i);
        }
    }

    public static void main(String[] args) {

        Client client = new Client("127.0.0.1", 5000);
        client.communicate();
        client.closeConnections();
    }

}