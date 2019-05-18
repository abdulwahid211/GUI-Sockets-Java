import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.io.IOException;

class Transaction extends Thread implements SocketConnection {
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    private Socket socket;
    private SynchList outputs;
    private int n;

    public Transaction(int i, SynchList o, Socket s) throws Exception {
        this.outputStream = new ObjectOutputStream(s.getOutputStream());
        this.inputStream = new ObjectInputStream(s.getInputStream());
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

        try {
            Object p;
            while((p=inputStream.readObject())!=null) {

                for (int j = 0; j < outputs.size(); j++) {
                        outputs.get(j).writeObject(p);
                        outputs.get(j).flush();

                }
                System.out.println(p.toString());

            }
        } catch (Exception e) {
            System.out.println("Error " + e);

            System.out.print("client " + n + " left loop");
            this.outputs.remove(outputStream);
            System.out.println("size of ArrayList :" + outputs.size());


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