//Homework 4 submission by Maria Slimani
//Lab on numbers in different bases, in 5 easy steps :)

public class BaseConverter {

    // Step 1.1 – char to int
    public static int charToInt(char c) {
        switch (Character.toLowerCase(c)) {
            case '0': return 0;
            case '1': return 1;
            case '2': return 2;
            case '3': return 3;
            case '4': return 4;
            case '5': return 5;
            case '6': return 6;
            case '7': return 7;
            case '8': return 8;
            case '9': return 9;
            case 'a': return 10;
            case 'b': return 11;
            case 'c': return 12;
            case 'd': return 13;
            case 'e': return 14;
            case 'f': return 15;
            default: throw new IllegalArgumentException("Invalid character for base conversion: " + c);
        }
    }

    // Step 1.2 – int to char
    public static char intToChar(int d) {
        switch (d) {
            case 0: return '0';
            case 1: return '1';
            case 2: return '2';
            case 3: return '3';
            case 4: return '4';
            case 5: return '5';
            case 6: return '6';
            case 7: return '7';
            case 8: return '8';
            case 9: return '9';
            case 10: return 'a';
            case 11: return 'b';
            case 12: return 'c';
            case 13: return 'd';
            case 14: return 'e';
            case 15: return 'f';
            default: throw new IllegalArgumentException("Invalid digit for base conversion: " + d);
        }
    }

    // Step 2 – Read a number in any base (as String), convert to base 10
    public static int readBaseAndNumber(int base, String number) {
        int result = 0;
        int power = 1;
        for (int i = number.length() - 1; i >= 0; i--) {
            int digitValue = charToInt(number.charAt(i));
            if (digitValue >= base) {
                throw new IllegalArgumentException("Digit " + number.charAt(i) + " is invalid for base " + base);
            }
            result += digitValue * power;
            power *= base;
        }
        return result;
    }

    // Step 3 – Convert base 10 number to given base
    public static String produceNumberInBase(int number, int base) {
        if (number == 0) return "0";
        StringBuilder sb = new StringBuilder();
        while (number > 0) {
            int remainder = number % base;
            sb.insert(0, intToChar(remainder));
            number /= base;
        }
        return sb.toString();
    }

    // STEP 4 – Final converting function
    public static String fromBaseToBase(int a, int b, String number) {
        int decimal = readBaseAndNumber(a, number);
        return produceNumberInBase(decimal, b);
    }

    // STEP 5 – Testing
    public static void main(String[] args) {
        // Provided test cases
        System.out.println("PROVIDED TEST CASES");

        System.out.print("--- 102 from base 8 to base 12\n\tshould return 56");
        System.out.println("\n\t-> obtained value: " + fromBaseToBase(8, 12, "102"));

        System.out.print("--- 11100101 from binary to hex\n\tshould return e5");
        System.out.println("\n\t-> obtained value: " + fromBaseToBase(2, 16, "11100101"));


        // Custom test cases
        System.out.println("\nCUSTOM TEST CASES");

        System.out.print("--- 10212 from base 3 to base 13\n\tshould return 80");
        System.out.println("\n\t-> obtained value: " + fromBaseToBase(3, 13, "10212"));

        System.out.print("--- 347 from base 10 to base 16\n\tshould return 15b");
        System.out.println("\n\t-> obtained value: " + fromBaseToBase(10, 16, "347"));

        System.out.print("--- 365 from base 7 to base 2\n\tshould return 11000010");
        System.out.println("\n\t-> obtained value: " + fromBaseToBase(7, 2, "365"));
    }
}
