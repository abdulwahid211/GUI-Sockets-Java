import java.io.OutputStream;
import java.util.ArrayList;

public class SynchList
{
   private ArrayList <OutputStream> list;

    SynchList()
    {
       list=new ArrayList<OutputStream>();
    }

    synchronized OutputStream get(int i)
    {
        return list.get(i);
    }

    synchronized void add(OutputStream o)
    {
       list.add(o);
    }

    synchronized int size()
    {
        return list.size();
    }
}