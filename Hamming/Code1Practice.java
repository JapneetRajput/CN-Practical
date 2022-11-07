package Hamming;
import java.util.*;

public class Code1Practice {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int[] sender = new int[7];
        int[] receiver = new int[7];
        int[] check= new int[3];
        int c;

        // Sender's Side
        System.out.print("Enter the data bits: ");
        for(int i=6; i>=0; i--)
            if(i!=3 && i!=1 && i!=0)
                sender[i]=scan.nextInt();
        
        // Calculating Parity 1
        c=0;
        if(sender[2]==1) c++;
        if(sender[4]==1) c++;
        if(sender[6]==1) c++;

        if(c%2==0)  sender[0]=0;
        else  sender[0]=1;
        
        // Calculating Parity 2
        c=0;
        if(sender[2]==1) c++;
        if(sender[5]==1) c++;
        if(sender[6]==1) c++;

        if(c%2==0)  sender[1]=0;
        else  sender[1]=1;
        
        // Calculating Parity 3
        c=0;
        if(sender[4]==1) c++;
        if(sender[5]==1) c++;
        if(sender[6]==1) c++;

        if(c%2==0)  sender[3]=0;
        else  sender[3]=1;

        System.out.print("Encoded message : ");
        for(int i=6; i>=0; i--){
            System.out.print(sender[i]+" ");
        }

        // Receiver's Side
        System.out.println("");
        System.out.print("Enter the reciever's code : ");
        for(int i=6; i>=0; i--){
            receiver[i] = scan.nextInt();
        }

        // Validating Parity 1
        c=0;
        if(receiver[0]==1) c++;
        if(receiver[2]==1) c++;
        if(receiver[4]==1) c++;
        if(receiver[6]==1) c++;

        if(c%2==0)  check[0]= 0;
        else  check[0]= 1;

        // Validating Parity 2
        c=0;
        if(receiver[1]==1) c++;
        if(receiver[2]==1) c++;
        if(receiver[5]==1) c++;
        if(receiver[6]==1) c++;

        if(c%2==0)  check[1]= 0;
        else  check[1]= 1;

        // Validating Parity 3
        c=0;
        if(receiver[3]==1) c++;
        if(receiver[4]==1) c++;
        if(receiver[5]==1) c++;
        if(receiver[6]==1) c++;

        if(c%2==0)  check[2]= 0;
        else  check[2]= 1;

        int value = check[0] + check[1]*2 + check[2]*4;

        if(value==0){
            System.out.println("Received message has no errors!");
        }
        else{
            if(receiver[value-1]==0) receiver[value-1] = 1;
            else if(receiver[value-1]==1) receiver[value-1] = 0;
        }
        System.out.println(value);
        System.out.print("Received message : ");
        for(int i=6; i>=0; i--){
            System.out.print(receiver[i]+" ");
        }
    }
}