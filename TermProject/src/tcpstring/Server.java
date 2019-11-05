package tcpstring;

import util.NetworkUtil;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private ServerSocket serverSocket;


    public Server() {
        try {
            serverSocket = new ServerSocket(33333);
            while (true) {
                //System.out.println("waiting for connection");
                Socket clientSocket = serverSocket.accept();
                serve(clientSocket);
            }
        } catch (Exception e) {
            System.out.println("Server starts:" + e);
        }
    }

    public void serve(Socket clientSocket) {
        NetworkUtil nc = new NetworkUtil(clientSocket);
        new ReadThread(nc);
        new WriteThread(nc, "Server");
    }

    public static void main(String args[]) {
        Server server = new Server();
    }
}
