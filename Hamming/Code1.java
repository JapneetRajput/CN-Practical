package Hamming;

import java.util.*;

public class Code1 {
    public static void main(String args[]) throws Exception {
        Scanner read = new Scanner(System.in);
        int i, value;
        int a[] = new int[7];
        int r[] = new int[7];
        int v[] = new int[3];
        a[0] = -1;
        a[1] = -1;
        a[3] = -1;
        value = 0;
        System.out.println("Enter data in bits:");
        for (i = 6; i >= 0; i--)
            if (i != 3 && i != 1 && i != 0)
                a[i] = read.nextInt();
        int c;
        System.out.println("No of parity bits required is: 3");
        // calculating parity 1
        c = 0;
        if (a[2] == 1)
            c++;
        if (a[4] == 1)
            c++;
        if (a[6] == 1)
            c++;
        if (c % 2 == 0)
            a[0] = 0;
        else
            a[0] = 1;
        System.out.println("P1=" + a[0]);
        // calculating parity 2
        c = 0;
        if (a[2] == 1)
            c++;
        if (a[5] == 1)
            c++;
        if (a[6] == 1)
            c++;
        if (c % 2 == 0)
            a[1] = 0;
        else
            a[1] = 1;
        System.out.println("P2=" + a[1]);
        // calculating parity 3
        c = 0;
        if (a[4] == 1)
            c++;
        if (a[5] == 1)
            c++;
        if (a[6] == 1)
            c++;
        if (c % 2 == 0)
            a[3] = 0;
        else
            a[3] = 1;
        System.out.println("P4=" + a[3]);

        System.out.println("The encoded message is:");
        for (i = 6; i >= 0; i--)
            System.out.print(a[i]);
        System.out.println();
        System.out.println("Received message:");
        for (i = 6; i >= 0; i--)
            r[i] = read.nextInt();
        c = 0;
        if (r[0] == 1)
            c++;
        if (r[2] == 1)
            c++;
        if (r[4] == 1)
            c++;
        if (r[6] == 1)
            c++;
        if (c % 2 == 0)
            v[0] = 0;
        else
            v[0] = 1;

        c = 0;
        if (r[1] == 1)
            c++;
        if (r[2] == 1)
            c++;
        if (r[5] == 1)
            c++;
        if (r[6] == 1)
            c++;
        if (c % 2 == 0)
            v[1] = 0;
        else
            v[1] = 1;

        c = 0;
        if (r[3] == 1)
            c++;
        if (r[4] == 1)
            c++;
        if (r[5] == 1)
            c++;
        if (r[6] == 1)
            c++;
        if (c % 2 == 0)
            v[2] = 0;
        else
            v[2] = 1;
        value = v[0] + v[1] * 2 + v[2] * 4;
        if (value == 0)
            System.out.println("NO ERROR!!");
        else {
            System.out.println("Error detected at: " + value + "th position");
            if (r[value - 1] == 0)
                r[value - 1] = 1;
            else
                r[value - 1] = 0;
            System.out.println("Corrected message:");
            for (i = 6; i >= 0; i--)
                System.out.print(r[i]);
        }
    }
}