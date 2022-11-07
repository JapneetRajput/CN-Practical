package TcpUdp;

import java.net.*;
import java.io.*;

public class Client {
    public static void main(String[] args) throws Exception {
        int ch = 0, a, b, c;
        Socket s = new Socket("localhost", 1024);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintStream ps = new PrintStream(s.getOutputStream());

        System.out.println("Please Enter Number 1:");
        a = Integer.parseInt(br.readLine());

        System.out.println("Please Enter Number 2:");
        b = Integer.parseInt(br.readLine());

        System.out.println("Please Enter The Operation to Be Performed\n");

        System.out.println("1.Addition 2.Subtraction 3.Multiplication 4.Divison 5.Modulo 0.Exit");

        ch = Integer.parseInt(br.readLine());
        ps.println(ch);
        ps.println(a);
        ps.println(b);
        BufferedReader br1 = new BufferedReader(new InputStreamReader(s.getInputStream()));

        c = Integer.parseInt(br1.readLine());
        System.out.println("Answer: " + c);
        s.close();
    }
}