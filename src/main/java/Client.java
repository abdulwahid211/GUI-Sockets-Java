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
                    System.out.print(p);
                }

            } catch (Exception e) {
            }

        }
    }


    public Client(String hostName, int port) {

        try {

            socket = new Socket(hostName, port);

            name = "A";

            // takes input from the sockets
            inputStream = new ObjectInputStream(socket.getInputStream());

            // sends output to the socket
            outputStream = new ObjectOutputStream(socket.getOutputStream());

           new serverReader().start();

        } catch (UnknownHostException u) {
            System.out.println(u);
        } catch (IOException i) {
            System.out.println(i);
        }
    }


    public void communicate() {

        try {

            userInput = new Scanner(System.in);

            while (userInput.hasNext()) {
                System.out.print(900000);
                String message = userInput.nextLine();
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

        Client client = new Client("127.0.0.1", 5001);
        client.communicate();
        client.closeConnections();
    }

}
