package Hamming;

import java.util.Scanner;

public class HammingCode {
    public static boolean isPowerOf2(int num) {
        int i = 0;
        while (num >= (int) Math.pow(2, i)) {
            if (num == Math.pow(2, i)) {
                return true;
            }
            i++;
        }
        return false;
    }

    public static int XOR(int[] code, int place) {
        // Select place bits starting from place and next (place-1) bits and skip the
        // next place bits
        int start = place;
        int count = 0;
        while (start < code.length) {
            for (int i = start; i <= (start + place - 1); i++) {
                if (i < code.length) {
                    count += code[i];
                }
            }
            start = start + (2 * place);
        }
        return count % 2;
    }

    public static String generateCodeWithParity(String data, int dataLength, int parityBits) {
        StringBuilder dataBuilder = new StringBuilder(data);
        dataBuilder = dataBuilder.reverse();
        data = dataBuilder.toString();

        int[] code = new int[dataLength + parityBits + 1];
        StringBuilder codeString = new StringBuilder();
        int j = 0;
        for (int i = 1; i < (dataLength + parityBits + 1); i++) {
            if (isPowerOf2(i)) {
                // Parity bit
                code[i] = 0;
            } else {
                // Data word;
                code[i] = (int) (data.charAt(j) - '0');
                j++;
            }
        }

        // Calculate Parity Bits value
        for (int i = 1; i < code.length; i++) {
            if (isPowerOf2(i)) {
                code[i] = XOR(code, i);
                System.out.println("Value of parity bit " + i + " is " + code[i]);
            }
        }

        for (int i = 1; i < code.length; i++) {
            codeString.append(code[i]);
        }
        return codeString.reverse().toString();
    }

    public static String verify(String datawordReceived, int noOfParityBits) {
        StringBuilder dwRec = new StringBuilder(datawordReceived);
        dwRec = dwRec.reverse();
        String dw = dwRec.toString();
        int[] dwIntArr = new int[dw.length() + 1];
        StringBuilder result = new StringBuilder();
        dwIntArr[0] = 0;

        for (int i = 0; i < dw.length(); i++) {
            dwIntArr[i + 1] = (int) (dw.charAt(i) - '0');
        }

        for (int i = 0; i < noOfParityBits; i++) {
            int pow = (int) Math.pow(2, i);
            System.out.println("Value of parity bit " + pow + " is " + XOR(dwIntArr, pow));
            result.append((char) (XOR(dwIntArr, pow) + '0'));
        }
        return result.reverse().toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // SENDER SIDE
        System.out.print("Enter the data word to be transmitted : ");
        String data = sc.next();
        int n = data.length();
        int r = 1;

        while (Math.pow(2, r) < (n + r + 1)) {
            r++;
        }
        System.out.println("The number of parity bits required is : " + r);
        System.out.println("The dataword transmitted is : " + generateCodeWithParity(data, n, r));
        // RECEIVER SIDE
        System.out.print("\nEnter the dataword received : ");
        String dataRec = sc.next();
        String result = verify(dataRec, r);
        if (result.contains("1")) {
            System.out.println("The data is corrupted. The error bit is : " + result);
            int index = Integer.parseInt(result, 2);
            System.out.println("The transmitted data is : " + dataRec.substring(0, dataRec.length() - index)
                    + ((int) dataRec.charAt(dataRec.length() - index) + 1) % 2
                    + dataRec.substring(dataRec.length() - index + 1));
        } else {
            System.out.println("No error, hence accepted");
        }
        sc.close();
    }
}