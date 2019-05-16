import java.io.*;
import java.net.ServerSocket;
import java.net.UnknownHostException;

public class Server extends SocketConnection {

    private ServerSocket serverSocket;

    public Server(int port) {

        try {

            serverSocket = new ServerSocket(port);
            userInput = new InputStreamReader(System.in);

            System.out.println("Waiting for a client ...");

            socket = serverSocket.accept();
            System.out.println("Client accepted");


            // takes input from the sockets
            inputStream = socket.getInputStream();

            // sends output to the socket
            outputStream = socket.getOutputStream();

        } catch (UnknownHostException u) {
            System.out.println(u);
        } catch (IOException i) {
            System.out.println(i);
        }
    }

    public void communicate() {
        int data;

        try {
            // keep reading until "Over" is input
            while ((data = inputStream.read()) != -1) {

                Character caps = Character.toUpperCase((char)data);

                outputStream.write(caps);
                outputStream.flush();
                System.out.print(caps);
            }
        }
        catch (IOException i){
            System.out.println("Error "+i);
        }
    }


    public static void main(String[] args) {

        Server server = new Server(5000);
        server.communicate();
        server.closeConnections();

    }


}
