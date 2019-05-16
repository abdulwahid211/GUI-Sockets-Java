import java.io.*;
import java.net.ServerSocket;

public class Server {

    private ServerSocket serverSocket;

    private Transaction transactions;

    public Server(int port) {

        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException i) {
            System.out.println(i);
        }
    }

    public void run() {

        try {
            while (true) {
                transactions = new Transaction(serverSocket.accept());
                transactions.start();
            }
        } catch (Exception i) {
            System.out.println("Transaction failed: " + i);
        }


    }


    public static void main(String[] args) {

        Server server = new Server(5000);
        server.run();

    }

}


