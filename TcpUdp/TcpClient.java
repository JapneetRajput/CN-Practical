package TcpUdp;
import java.net.*;
import java.io.*;

public class TcpClient {
    public static void main(String[] args) throws Exception {
        int choice=0, a , b;
        Socket s = new Socket("localhost", 1024);
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        PrintStream ps = new PrintStream(s.getOutputStream());
        
        System.out.println("Please Enter Number 1:");
        a = Integer.parseInt(read.readLine());

        System.out.println("Please Enter Number 2:");
        b = Integer.parseInt(read.readLine());

        System.out.println("Please Enter The Operation to Be Performed\n");

        System.out.println("1.Addition 2.Subtraction 3.Multiplication 4.Divison 5.Modulo 0.Exit");

        choice = Integer.parseInt(read.readLine());
        ps.println(choice);
        ps.println(a);
        ps.println(b);

        BufferedReader read1 = new BufferedReader(new InputStreamReader(s.getInputStream()));
        System.out.println(read1.readLine());
        s.close();
    }    
}
