import java.io.*;
import java.net.*;

public class SocketServer
{

    private int port;
    private String address;
    private Socket sc;
    private ServerSocket sSocket;
    private BufferedReader reader;
    private PrintWriter writer;

    public SocketServer(String address, int port)
    {
        this.address = address;
        this.port = port;
    }

    public void runServer()
    {
        try
        {
            this.sSocket = new ServerSocket();
            sSocket.bind(new InetSocketAddress(this.address,this.port));

            System.out.println("cliant connect wait");
            this.sc = sSocket.accept();
            System.out.println("cliant connected");

            reader = new BufferedReader(new InputStreamReader(sc.getInputStream()));

            writer = new PrintWriter(sc.getOutputStream(), true);
            
            String line = null;
            while(true)
            {
                line = reader.readLine();

                if(line.equals("exit"))
                {
                    break;
                }

                writer.println("[server] message success");
                System.out.println("cliant massege:" + line);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if(reader != null)
                {
                    reader.close();
                }
                if(writer != null)
                {
                    writer.close();
                }
                if(sc != null)
                {
                    sc.close();
                }
                if(sSocket != null)
                {
                    sSocket.close();
                }

            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
            System.out.println("cliant disconnected");
        }
    }
}