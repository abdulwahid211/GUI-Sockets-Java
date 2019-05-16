import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client implements SocketConnection {


    protected Socket socket;

    protected InputStream inputStream;

    protected OutputStream outputStream;

    protected InputStreamReader userInput;


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
