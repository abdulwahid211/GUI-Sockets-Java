import java.io.*;
import java.net.Socket;

public abstract class SocketConnection {

    protected Socket socket;

    protected InputStream inputStream;

    protected OutputStream outputStream;

    protected InputStreamReader userInput;


    public abstract void communicate();

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

}
