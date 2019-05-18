import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.net.*;


public class evenSimplerGuiClient implements ActionListener {
    private JTextField user = new JTextField("user",20);
    private JTextArea server = new JTextArea("server",5,20);
    private JScrollPane sp =new JScrollPane(server);
    private Socket s;
    private OutputStreamWriter p;
    private InputStream i;
    private JFrame window = new JFrame("client");


    class serverReader extends Thread
    {

        public void run()
        {
            String s="";
            int c;
            try
            {
                while ((c=i.read())!=-1)
                {
                    s=s+ ((char)c);
                    server.setText(s);
                    System.out.println(s);
                }
            }
            catch(Exception e){};
        }
    }

    public evenSimplerGuiClient() throws Exception
    {
        try
        {
            s = new Socket("localhost",5000);
            p =new  OutputStreamWriter(s.getOutputStream());
            i =  s.getInputStream();
            new serverReader().start();
        }

        catch (Exception e){System.out.println("error");};

        window.setSize(300,300);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(new FlowLayout());
        window.add(sp);
        window.add(user);

        user.addActionListener(this);

        window.setVisible(true);
    }

    public void actionPerformed(ActionEvent a)
    {

        String s= user.getText();
        try
        {
            p.write(s+'\n',0,s.length()+1);
            p.flush();
            user.setText("");
        }
        catch (Exception e){};
    }

    public static void main(String[] args) throws Exception
    {

        new evenSimplerGuiClient();
    }
}