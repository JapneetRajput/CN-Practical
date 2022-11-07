import java.util.Scanner;

public class Checksum {

    public static String binarySum(String[] binaryNums) {
        int sum = 0;
        for (int i = 0; i < binaryNums.length; i++) {
            int num = Integer.parseInt(binaryNums[i], 2);
            sum += num;
        }

        // Wrapping the bits around
        String result = Integer.toBinaryString(sum);
        if (result.length() > binaryNums[0].length()) {
            String wrapAroundBits = result.substring(0, result.length() - binaryNums[0].length());
            String remainingBits = result.substring(result.length() - binaryNums[0].length());

            result = Integer.toBinaryString(Integer.parseInt(remainingBits, 2) + Integer.parseInt(wrapAroundBits, 2));
        }

        // If length of result is less than size of the segment, prefix 0's
        String zeroes = "";
        if (result.length() < binaryNums[0].length()) {
            for (int i = 0; i < binaryNums[0].length() - result.length(); i++) {
                zeroes += "0";
            }
        }
        return zeroes + result;
    }

    public static String complement(String binaryNum) {
        String complement = "";
        for (int i = 0; i < binaryNum.length(); i++) {
            int a = Integer.parseInt(binaryNum.substring(i, i + 1));
            a = (a + 1) % 2;
            complement += Integer.toString(a);
        }
        return complement;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // SENDER SIDE
        System.out.print("Enter the data to be transmitted : ");
        String dataword = sc.next();

        System.out.print("Enter the no. of bits in each segment : ");
        int segmentSize = sc.nextInt();

        int noOfSegments = dataword.length() / segmentSize;
        System.out.println("The number of segments required are : " + noOfSegments);

        String[] segmentArray = new String[noOfSegments];

        for (int i = 0, x = 0; i < noOfSegments; i++, x += segmentSize) {
            segmentArray[i] = dataword.substring(x, x + segmentSize);
        }

        String sum = binarySum(segmentArray);
        System.out.println("\nThe binary sum of the segments is : " + sum);

        String checksum = complement(sum);
        System.out.println("The checksum is : " + checksum);

        System.out.println("The data transmitted is : " + dataword + checksum);

        // RECEIVER SIDE
        System.out.print("\nEnter the data word received : ");
        dataword = sc.next();

        for (int i = 0, x = 0; i < noOfSegments; i++, x += segmentSize) {
            segmentArray[i] = dataword.substring(x, x + segmentSize);
        }

        String checksumRec = dataword.substring(noOfSegments * segmentSize);

        sum = binarySum(segmentArray);
        String[] temp = { sum, checksumRec };
        sum = binarySum(temp);
        checksum = complement(sum);
        System.out.println("The checksum of the receiver end is : " + checksum);

        if (checksum.contains("1")) {
            System.out.println("The data is corrupted as the checksum is non-zero. Ask for re-transmission.");
        } else {
            System.out.println("No error, hence accepted.");
        }
        sc.close();
    }
}