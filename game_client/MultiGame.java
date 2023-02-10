import java.io.*;
import java.net.*;

public class MultiGame
{
    private int port;
    private String address;
    private Socket sc;
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
        }
        catch(Exception e)
        {

        }
    }

    public void runMultiClient()
    {
        try
        {
            this.sc = new Socket(this.address, this.port);
        }
        catch(Exception e)
        {

        }
    }
    public void closeContact()
    {
        try
        {
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
            ObjectOutputStream out = new ObjectOutputStream(this.sc.getOutputStream());
            out.writeObject(sd);
        }
        catch(Exception e)
        {
        }
    }

    public SendData receiveData()
    {
        SendData sendData = new SendData();
        try
        {
            ObjectInputStream in = new ObjectInputStream(this.sc.getInputStream());
            sendData = (SendData)in.readObject();
        }
        catch(Exception e)
        {
        }
        return sendData;
    }
}