import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.io.IOException;

class Transaction extends Thread implements SocketConnection {
    private InputStream inputStream;
    private OutputStream outputStream;
    private Socket socket;
    private SynchList outputs;
    private int n;

    public Transaction(int i, SynchList o, Socket s) throws Exception {
        this.inputStream = s.getInputStream();
        this.outputStream = s.getOutputStream();
        this.socket = s;
        this.outputs = o;
        this.n = i;
        this.outputs.add(outputStream);
    }

    public void run() {
        this.communicate();
    }

    @Override
    public void communicate() {
        int data;

        try {
            while ((data = inputStream.read()) != -1) {

                for (int j = 0; j < outputs.size(); j++) {


                        Character caps = Character.toUpperCase((char) data);
                        outputs.get(j).write(caps);
                        outputs.get(j).flush();

                }
                System.out.print((char) data);

            }
            System.out.print("size of ArrayList :" + outputs.size());
            System.out.print("left loop");
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