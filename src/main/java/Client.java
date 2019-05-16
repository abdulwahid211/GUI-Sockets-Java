import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client extends SocketConnection {


    public Client(String hostName, int port) {

        try {

            socket = new Socket(hostName, port);

            userInput = new InputStreamReader(System.in);

            // takes input from the sockets
            inputStream = socket.getInputStream();

            // sends output to the socket
            outputStream =  socket.getOutputStream();

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
            while ((data = userInput.read()) != -1) {
                outputStream.write(data);
                outputStream.flush();
                System.out.print((char) inputStream.read());
            }
        }
        catch (IOException i){
            System.out.println("Error "+i);
        }
    }

    public static void main(String[] args) {

        Client client = new Client("127.0.0.1", 5000);
        client.communicate();
        client.closeConnections();
    }

}
