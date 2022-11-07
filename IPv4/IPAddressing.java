package IPv4;
import java.util.Scanner;

public class IPAddressing {
    public static boolean verify(String ip) {
        String[] ipArr = ip.split("\\.", 0);
        if (ipArr.length != 4) {
            return false;
        }
        for (int i = 0; i < ipArr.length; i++) {
            if (ipArr[i].startsWith("0")) {
                return false;
            }
            if (ipArr[i].length() > 3) {
                return false;
            }
            if (Integer.parseInt(ipArr[i]) > 255) {
                return false;
            }
        }
        return true;
    }

    public static String getClass(String ip) {
        String[] ipArr = ip.split("\\.", 0);
        int firstByte = Integer.parseInt(ipArr[0]);
        String ipClass = "X";
        if (firstByte >= 240) {
            ipClass = "E";
        } else if (firstByte >= 224) {
            ipClass = "D";
        } else if (firstByte >= 192) {
            ipClass = "C";
        } else if (firstByte >= 128) {
            ipClass = "B";
        } else {
            ipClass = "A";
        }
        return ipClass;
    }

    public static String networkMask(String ip, String ipClass) {
        if (ipClass.toUpperCase() == "A")
            return "255.0.0.0";
        if (ipClass.toUpperCase() == "B")
            return "255.255.0.0";
        if (ipClass.toUpperCase() == "C")
            return "255.255.255.0";
        if (ipClass.toUpperCase() == "D" || ipClass.toUpperCase() == "E")
            return "NA";
        else
            return "Invalid";
    }

    static void separate(String str, String ipClass) {

        String network = "", host = "", netmask = "";
        String broadcast = "", firsthost = "", lasthost = "";
        int nhosts = 0;

        if (ipClass == "A") {
            int index = str.indexOf('.');
            network = str.substring(0, index);
            host = str.substring(index + 1, str.length());
            netmask = "255.0.0.0";
            nhosts = 167777214;
            broadcast = network + ".255.255.255";
            firsthost = network + ".0.0.1";
            lasthost = network + ".255.255.254";
            System.out.println("Network ID is " + network + ".0.0.0");
        } else if (ipClass == "B") {
            int index = -1;
            int dot = 2;
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == '.') {
                    dot -= 1;
                    if (dot == 0) {
                        index = i;
                        break;
                    }
                }
            }
            network = str.substring(0, index);
            host = str.substring(index + 1, str.length());
            netmask = "255.255.0.0";
            nhosts = 65534;
            broadcast = network + ".255.255";
            firsthost = network + ".0.1";
            lasthost = network + ".255.254";
            System.out.println("Network ID is " + network + ".0.0");
        } else if (ipClass == "C") {
            int index = -1;
            int dot = 3;
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == '.') {
                    dot -= 1;
                    if (dot == 0) {
                        index = i;
                        break;
                    }
                }
            }
            network = str.substring(0, index);
            host = str.substring(index + 1, str.length());
            netmask = "255.255.255.0";
            nhosts = 254;
            broadcast = network + ".255";
            firsthost = network + ".1";
            lasthost = network + ".254";
            System.out.println("Network ID is " + network + ".0");
        } else if (ipClass == "D") {
            System.out.println("In this Class, IP address" +
                    " is not divided into Network and Host IDs. D is used for multicast network");
            return;
        }

        else if (ipClass == "E") {
            System.out.println("In this Class, IP address" +
                    " is not divided into Network and Host IDs. E is reserved for future use");
            return;
        }

        System.out.println("Network ID is " + network);
        System.out.println("Host ID is " + host);
        System.out.println("No. of hosts per network is " + nhosts);
        System.out.println("The Subnet mask is " + netmask);
        System.out.println("Broadcast address is " + broadcast);
        System.out.println("FirstHost is " + firsthost);
        System.out.println("Lasthost is " + lasthost);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the IP address : ");
        String ip = sc.next();
        if (verify(ip)) {
            System.out.println("\nIP address is valid.");
            String ipClass = getClass(ip);
            System.out.println("The class of the IP address is : class " + ipClass);
            System.out.println("The network mask is : " + networkMask(ip, ipClass));
            separate(ip, ipClass);
        } else {
            System.out.println("IP address is invalid.");
        }

        sc.close();
    }
}