package lab1.client;

import java.io.*;
import java.io.PrintWriter;
import java.net.Socket;

public class Main {

    private static String S_HOST = "localhost";
    private static int S_SERVER_PORT = 54321;

    public static void main(String[] args) {
        try {
            System.out.printf("Client is started\n");
            Socket s = new Socket(S_HOST, S_SERVER_PORT);
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(s.getOutputStream(), "utf-8"));
            pw.println("get_ticket=2");
            pw.flush();


            BufferedReader bf = new BufferedReader(new InputStreamReader(s.getInputStream(), "utf-8"));
            String str = bf.readLine();
            System.out.printf("Server send: %s\n", str);

            pw.close();
            bf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
