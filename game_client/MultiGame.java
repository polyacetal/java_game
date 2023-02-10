import java.io.*;
import java.net.*;

public class MultiGame
{
    private int port;
    private String address;
    private Socket sc;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private boolean wait;

    public MultiGame(String address)
    {
        this.wait = false;
        this.address = address;
        this.port = 19021;
    }

    public void runMultiHost()
    {
        this.wait = false;
        try
        {
            ServerSocket sSocket = new ServerSocket();
            sSocket.bind(new InetSocketAddress(this.address, this.port));

            this.sc = sSocket.accept();
            this.wait = true;
            this.out = new ObjectOutputStream(this.sc.getOutputStream());
            this.in = new ObjectInputStream(this.sc.getInputStream());
        }
        catch(Exception e)
        {

        }
    }

    public void runMultiClient()
    {
        this.wait = false;
        try
        {
            this.sc = new Socket(this.address, this.port);
            this.out = new ObjectOutputStream(this.sc.getOutputStream());
            this.in = new ObjectInputStream(this.sc.getInputStream());
        }
        catch(Exception e)
        {

        }
        this.wait = true;
    }
    public void closeContact()
    {
        try
        {
            if(this.in != null)
            {
                this.in.close();
            }
            if(this.out != null)
            { 
                this.out.close();
            }
            if(this.sc != null)
            {
                this.sc.close();
            }
        }
        catch(Exception e)
        {

        }
    }

    public boolean getWait()
    {
        return this.wait;
    }

    public void dataSend(SendData sd)
    {
        try
        {
            this.out.writeObject(sd);
        }
        catch(Exception e)
        {}
    }

    public SendData receiveData()
    {
        SendData sendData = new SendData();
        try
        {
            sendData = (SendData)this.in.readObject();
        }
        catch(Exception e)
        {}
        return sendData;
    }
}