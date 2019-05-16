import java.io.*;
import java.net.ServerSocket;

public class Server {

    private ServerSocket serverSocket;

    private Transaction transactions;

    private static SynchList outputs;

    private static int num__of_clients =0;

    public Server(int port) {

        try {
            serverSocket = new ServerSocket(port);
            outputs= new SynchList();
        } catch (IOException i) {
            System.out.println(i);
        }
    }

    public void run() {

        try {
            while (true) {
                transactions = new Transaction(num__of_clients,outputs,serverSocket.accept());
                transactions.start();
                System.out.println("New client has joined...");
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


