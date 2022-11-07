package TcpUdp;

import java.net.*;
import java.io.*;

public class Server {
    public static void main(String[] args) throws Exception {
        int choice, a, b, c = 0;
        ServerSocket ss = new ServerSocket(1024);
        Socket s = ss.accept();
        BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));

        choice = Integer.parseInt(br.readLine());
        a = Integer.parseInt(br.readLine());
        b = Integer.parseInt(br.readLine());
        switch (choice) {
            case 1:
                c = a + b;
                break;
            case 2:
                c = a - b;
                break;
            case 3:
                c = a * b;
                break;
            case 4:
                c = a / b;
                break;
            case 5:
                c = (a % b);
                break;
        }
        PrintStream pr = new PrintStream(s.getOutputStream());
        pr.println(c);
        ss.close();
        s.close();
    }
}