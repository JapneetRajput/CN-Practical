// import java.math.BigInteger;
import java.util.Scanner;

public class CRCError {

    public static String xor(String x1, String x2) {
        String value = "";
        for (int i=0; i<x1.length(); i++) {
            if (x1.charAt(i) == x2.charAt(i)) {
                value += "0";
            }
            else {
                value += "1";
            }
        }
        return value;
    }

    public static String binaryDivision(String dividend, String divisor) {
        StringBuilder dividendMutable = new StringBuilder(dividend);
        String quotient = "";
        String remainder = "";
        int divisorLength = divisor.length();
        
        for (int i = 0; i < dividend.length() - divisorLength + 1; i++) {
            String dividend_ = dividendMutable.substring(i, i+divisorLength);
            String temp = "";
            
            if (dividend_.charAt(0) == divisor.charAt(0)) {
                quotient += "1";
                temp = xor(dividend_, divisor).substring(1);
            }

            else {
                quotient += "0";
                for (int j=0; j<divisorLength; j++) {
                    temp += "0";
                }
                temp = xor(dividend_, temp).substring(1);
            }
            int x = 0;
            for (int j=i+1; j < i+divisorLength; j++) {
                dividendMutable.setCharAt(j, temp.charAt(x));
                x++;
            }
            remainder = temp;
        }

        System.out.println("Quotient calculated is : " + quotient);
        return remainder;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // SENDER END
        System.out.print("Enter the data word to send : ");
        String dataword = sc.next();
        String datawordCopy = dataword;

        System.out.print("Enter the divisor : ");
        String divisor = sc.next();

        String redundantBits = "";
        for (int i = 0; i < divisor.length() - 1; i++) {
            redundantBits += '0';
        }
        
        System.out.println("\nThe number of redundant bits required : " + (divisor.length()-1));
        datawordCopy += redundantBits;
        System.out.print("The dataword after appending redundant bits is : ");
        System.out.println(datawordCopy);

        String remainder = binaryDivision(datawordCopy, divisor);
        System.out.println("The calculated remainder is : " + remainder);
        dataword += remainder;
        System.out.println("\nThe transmitted word is : " + dataword);

        // RECEIVER END
        System.out.print("\n\nEnter the codeword received : ");
        dataword = sc.next();
        System.out.print("Enter the divisor : ");
        divisor = sc.next();

        remainder = binaryDivision(dataword, divisor);
        if (remainder.contains("1")) {
            System.out.println("Remainder is : " + remainder + " which is not zero.");
            System.out.println("The data is corrupted. Ask for re-transmission.");
        }
        else {
            dataword = dataword.substring(0, dataword.length() - divisor.length()+1);
            System.out.println("The data transmitted was : " + dataword);
        }
        sc.close();
    }
}
