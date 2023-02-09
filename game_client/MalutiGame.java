import java.io.*;
import java.net.*;

public class MalutiGame
{
    private int port;
    private String address;
    private Socket sc;
    private BufferedReader reader;
    private PrintWriter writer;

    public MalutiGame(String address)
    {
        this.address = address;
        this.port = 19021;
    }

    public void runMultiHost()
    {
        try
        {
            ServerSocket sSocket = new ServerSocket();
            sSocket.bind(new InetSocketAddress(this.address, this.port))

            this.sc = sSocket.accept();
            this.reader = new BufferedReader(new InputStreamReader(sc.getInputStream()));
            this.writer = new PrintWriter(sc.getOutputStream(), true);
        }
    }

    public void runMultiClient()
    {
        try
        {
            this.sc = sSocket.accept();
            this.reader = new BufferedReader(new InputStreamReader(sc.getInputStream()));
            this.writer = new PrintWriter(sc.getOutputStream(), true);
        }
    }
    public void closeContact()
    {
        try
        {
            if(this.reader != null)
            {
                this.reader.close();
            }
            if(this.writer != null)
            {
                this.writer.close();
            }
            if(this.sc != null)
            {
                this.sc.close();
            }
        }
    }

}