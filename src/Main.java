import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    /**
     * Prints a message according to a given grade.
     *
     * It is guaranteed that the grade is within the range [0, 100].
     *
     * @param grade The grade
     */
    public static void gradeMessage(int grade) {
        int gradeDivided = grade/10;
        switch (gradeDivided){
            case (7):
                System.out.println("Good");
                break;
            case (8):
                System.out.println("Very Good");
                break;
            case (9):
                System.out.println("Great");
                break;
            case (10):
                System.out.println("Excellent");
                break;
            default:
                System.out.println("OK");
                break;
        }
    }

    /**
     * Compresses a given string.
     *
     * The compression process is done by replacing a sequence of identical consecutive characters
     * with that same character followed by the length of sequence.
     *
     * It is guaranteed that the string contains only letters (lowercase and uppercase).
     *
     * @param stringToCompress The string to compress
     * @return The compressed version of the string
     */
    public static String compressString(String stringToCompress) {
        StringBuilder compressedString = new StringBuilder();
        int countConsecutive = 0;
        int stringLength = stringToCompress.length();
        for (int i = 0; i < stringLength; i++) {
            countConsecutive++;
            // If next character is different than current append this char to result
            if (i + 1 >= stringLength || stringToCompress.charAt(i) != stringToCompress.charAt(i + 1)) {
                compressedString.append(stringToCompress.charAt(i)).append(countConsecutive);
                countConsecutive = 0;
            }
        }

        return compressedString.toString();
    }

    /**
     * Decompresses a given string.
     *
     * The decompression process is done by duplicating each sequence of characters
     * according to the number which appears after the sequence.
     *
     * It is guaranteed that the string is a legal compressed string.
     *
     * @param compressedString The string to decompress
     * @return The decompressed string
     */
    public static String decompressString(String compressedString) {
        StringBuilder decompressedString = new StringBuilder();
        int front = 0, back = 0;
        while (front <= compressedString.length()) {  // while didn't reach end of string
            String subString;
            int numberFromString;
            // if current char isn't a number
            if (compressedString.charAt(front) < 48 || compressedString.charAt(front) > 57) {
                front++;
            }
            else {
                subString = compressedString.substring(back, front);  // contains the chars until the number
                int numberStringFront = front, numberStringBack = front;
                while (numberStringFront < compressedString.length() &&
                        !(compressedString.charAt(numberStringFront) < 48 ||
                                compressedString.charAt(numberStringFront) > 57)) {
                    numberStringFront++;
                }
                numberFromString = Integer.parseInt(compressedString.substring(numberStringBack, numberStringFront));
                for (int i = 0; i < numberFromString; i++) {
                    decompressedString.append(subString);
                }
                front = numberStringFront;
                back = front;
            }
            if (front == compressedString.length()) break;
        }
        return decompressedString.toString();
    }

    public static void main(String[] args) throws FileNotFoundException {
        String filePath = args[0];
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);

        // Tests for part A
        int numberOfGrades = scanner.nextInt();
        for (int i = 0; i < numberOfGrades; i++) {
            int grade = scanner.nextInt();
            gradeMessage(grade);
        }

        // Tests for part B1
        int numberOfStringsToCompress = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < numberOfStringsToCompress; i++) {
            String stringToCompress = scanner.nextLine();
            String compressedString = compressString(stringToCompress);
            System.out.println("The compressed version of " + stringToCompress + " is " + compressedString);
        }

        // Tests for part B2
        int numberOfDecompressedStrings = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < numberOfDecompressedStrings; i++) {
            String compressedString = scanner.nextLine();
            String decompressedString = decompressString(compressedString);
            System.out.println("The decompressed version of " + compressedString + " is " + decompressedString);
        }

        // Tests for both part B1 and B2
        int numberOfCombinedTests = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < numberOfCombinedTests; i++) {
            String stringToCompress = scanner.nextLine();
            String compressedString = compressString(stringToCompress);
            String decompressedString = decompressString(compressedString);
            System.out.println("decompress(compress(" + stringToCompress + ")) == " + stringToCompress + "? "
                    + stringToCompress.equals(decompressedString));
        }
    }
}
