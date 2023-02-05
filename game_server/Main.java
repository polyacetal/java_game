public class Main
{
    public static void main(String[] args)
    {
        SocketServer sServer = new SocketServer("localhost", 10000);
        sServer.runServer();
    }
}