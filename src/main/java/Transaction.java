import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.io.IOException;

class Transaction extends Thread implements SocketConnection {
    private InputStream inputStream;
    private OutputStream outputStream;
    private Socket socket;

    public Transaction(Socket s) throws Exception {
        inputStream = s.getInputStream();
        outputStream = s.getOutputStream();
        socket = s;
    }

    public void run() {
        this.communicate();
    }

    @Override
    public void communicate() {
        int data;

        try {
            // keep reading until "Over" is input
            while ((data = inputStream.read()) != -1) {

                Character caps = Character.toUpperCase((char) data);

                outputStream.write(caps);
                outputStream.flush();
                System.out.print(caps);
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
            socket.close();
        } catch (IOException i) {
            System.out.println(i);
        }
    }
}