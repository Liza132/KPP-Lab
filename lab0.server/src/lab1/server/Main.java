package lab1.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static boolean isNumeric(String str) {
        try {
            int integer = Integer.parseInt(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    private static int S_PORT = 54321;

    public static void main(String[] args) {
        ServerSocket ss;
        try {

            Map TicketsCollection = new HashMap();
            TicketsCollection.put(3, "Interstellar");
            TicketsCollection.put(1, "Batman");
            TicketsCollection.put(4, "Titanik");
            TicketsCollection.put(2, "Matrix");
            //System.out.println(TicketsCollection.get(3));

            System.out.printf("Start Server\n");
            ss = new ServerSocket(S_PORT);
            System.out.printf("Server listening: %s\n", S_PORT);
            Socket c = ss.accept();
            BufferedReader bf = new BufferedReader(new InputStreamReader(c.getInputStream(), "utf-8"));
            String str = bf.readLine();
            System.out.printf("Server received %s\n", str);

            String token[];
            token = str.split("=");
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(c.getOutputStream(), "utf-8"));
            if (token[0].equals("get_ticket")
                    && isNumeric(token[1])
                    && Integer.parseInt(token[1]) <= TicketsCollection.size()
                    && Integer.parseInt(token[1]) > 0) {
                pw.println("Билет на фильм: " + TicketsCollection.get(Integer.parseInt(token[1])) + "\n");
            } else {
                pw.println("Неверный запрос!\n");
            }
            pw.flush();

            pw.close();
            bf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
