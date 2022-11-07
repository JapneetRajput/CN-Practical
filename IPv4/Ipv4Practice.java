package IPv4;
import java.util.*;

public class Ipv4Practice {
    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter a dot separated Decimal IPv4 Address: ");
        String input = scan.nextLine();
        String[] words = input.split("\\.");
        int flag=0;
        int classNumber = Integer.parseInt(words[0]);
        if(words.length!=4){
            System.out.println("Enter a valid IP Address!");
        }
        else{
            // System.out.println("Success");
            for(int i=0; i<words.length; i++){
                int n = Integer.parseInt(words[i]);
                if(n<0 || n>255){
                    System.out.println("Every word should be a 16bit number.");
                    flag=1;
                }
                if(words[i].substring(0,1).equals('0') && n !=1){
                    System.out.println("Remove the leading zeros'");
                    flag=1;
                }
            }
            if(flag==1){
                System.out.println("Enter a valid IPv4 Address!");
            }
            else{
                if(classNumber<128){
                    System.out.println("IP Address belongs to Class A");
                    System.out.println("Network Mask : "+ "");
                    System.out.println("Network ID : " + words[0] +".0.0.0");
                    System.out.println("Subnet mask : 255.0.0.0");
                    System.out.println("No. of hosts per network: 2^24 = " + Math.pow(2,24));
                    System.out.println("Host ID : 0." + words[1] +"."+ words[2] +"."+ words[3]);
                    System.out.println("First Host ID : "+words[0]+".0.0.1");
                    System.out.println("Last Host ID : "+words[0]+".255.255.254");
                    System.out.println("Broadcast ID : "+words[0]+".255.255.255");
                }
                else if(classNumber<192){
                    System.out.println("IP Address belongs to Class B");
                    System.out.println("Network Mask : "+ "");
                    System.out.println("Network ID : " + words[0] +"." +words[1]+".0.0");
                    System.out.println("Subnet mask : 255.255.0.0");
                    System.out.println("No. of hosts per network: 2^16 = " + Math.pow(2,16));
                    System.out.println("Host ID : 0.0."+ words[2] +"."+ words[3]);
                    System.out.println("First Host ID : "+words[0]+"."+words[1]+".0.1");
                    System.out.println("Last Host ID : "+words[0]+"."+words[1]+".255.254");
                    System.out.println("Broadcast ID : "+words[0]+"."+words[1]+".255.255");
                }
                else if(classNumber<224){
                    System.out.println("IP Address belongs to Class C");
                    System.out.println("Network Mask : "+ "");
                    System.out.println("Network ID : " + words[0] +"." +words[1]+"." +words[2]+".0");
                    System.out.println("Subnet mask : 255.255.255.0");
                    System.out.println("No. of hosts per network: 2^8 = " + Math.pow(2,8));
                    System.out.println("Host ID : 0."+ words[1] + words[2] +"."+ words[3]);
                    System.out.println("First Host ID : "+words[0]+"."+words[1]+"."+words[2]+".1");
                    System.out.println("Last Host ID : "+words[0]+"."+words[1]+"."+words[2]+".254");
                    System.out.println("First Host ID : "+words[0]+"."+words[1]+"."+words[2]+".1");
                    System.out.println("Broadcast ID : "+words[0]+"."+words[1]+"."+words[2]+".255");
                }
                else if(classNumber<240){
                    System.out.println("IP Address belongs to Class D");
                }
                else{
                    System.out.println("IP Address belongs to Class E");
                }
            }
        }
    }
}
