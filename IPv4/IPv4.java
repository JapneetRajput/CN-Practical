package IPv4;
import java.util.*;
public class IPv4{
    public static void main(String args[]){
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter decimal dot separated ipv4 address: ");
        String input=scan.nextLine();
        String[] words=input.split("\\.");
        int flag =0;
        if (words.length !=4){
            System.out.println("Number is not valid! Enter a 32 bit number");
        }
        else{
            for (String w:words){
                // System.out.println(w);
                int num=Integer.parseInt(w);
                if(num>255 || num<0){
                    System.out.println("Each decimal number should be beween 0 and 255");
                    flag=1;
                }
                System.out.println(w.length());
                if (w.substring(0,1).equals("0") && w.length() !=1){
                    System.out.println("Please remove the leading zeros");
                    flag=1;
                }
                if(w.length()>3){
                    System.out.println("Mixing of binary and decimal is not allowed");
                    flag=1;
                }
            }
            if(flag==1){
                System.out.println("Number is not valid!");
            }
            else{
                int classNumber=Integer.parseInt(words[0]);
                if(classNumber<128){
                    System.out.println("IP Address belongs to CLASS A");
                    System.out.println("NETWORK ID: "+ words[0]+".0.0.0");
                    System.out.println("HOST ID: 0."+ words[1]+"."+words[2]+"."+words[3]);
                    System.out.println("BROADCAST ID: "+words[0]+".255.255.255");
                    System.out.println("NO OF HOSTS: 2^24="+Math.pow(2,24));
                    System.out.println("NETWORK MASK: 255.0.0.0");
                    System.out.println("FIRST HOST OF NETWORK: "+ words[0]+".0.0.1");
                    System.out.println("LAST HOST OF NETWORK: "+ words[0]+".255.255.254");        
                }
                else if(classNumber<192){
                    System.out.println("CLASS B ip address");
                    System.out.println("NETWORK ID: "+ words[0]+"."+words[1]+".0.0");
                    System.out.println("HOST ID: 0.0."+words[2]+"."+words[3]);
                    System.out.println("BROADCAST ID: "+words[0]+"."+words[1]+".255.255");
                    System.out.println("NO OF HOSTS: 2^16="+Math.pow(2,16));
                    System.out.println("NETWORK MASK: 255.255.0.0");
                    System.out.println("FIRST HOST OF NETWORK: "+ words[0]+"."+words[1]+".0.1");
                    System.out.println("LAST HOST OF NETWORK: "+ words[0]+"."+words[1]+".255.254");    
                }
                else if(classNumber<224){
                    System.out.println("CLASS C ip address");
                    System.out.println("NETWORK ID: "+ words[0]+"."+words[1]+"."+words[2]+".0");
                    System.out.println("HOST ID: 0.0.0."+words[3]);
                    System.out.println("BROADCAST ID: "+words[0]+"."+words[1]+"."+words[2]+".255");
                    System.out.println("NO OF HOSTS: 2^8="+Math.pow(2,8));
                    System.out.println("NETWORK MASK: 255.255.255.0");
                    System.out.println("FIRST HOST OF NETWORK: "+ words[0]+"."+words[1]+"."+words[2]+".1");
                    System.out.println("LAST HOST OF NETWORK: "+ words[0]+"."+words[1]+"."+words[2]+".254");    
                }
                else if(classNumber<240){
                    System.out.println("CLASS D ip address");
                }
                else{
                    System.out.println("CLASS E ip address");
                }
            }
        }
    }
}