package tcpstring;

import util.NetworkUtil;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class WriteThread implements Runnable {

    private Thread thr;
    private NetworkUtil nc;
    String name;

    public WriteThread(NetworkUtil nc, String name) {
        this.nc = nc;
        this.name = name;
        this.thr = new Thread(this);
        thr.start();
    }

    public void run() {
        try {
            BufferedReader br=new BufferedReader(new FileReader("SortedScore.txt"));
            while (true) {
                String s = br.readLine();
                nc.write(s);
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            nc.closeConnection();
        }
    }
}



