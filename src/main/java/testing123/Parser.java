// Parser.java - Obtain input safely, without any crashing. By Aaron Franke. 
// Supports all integer types, both float types, non-numeric text, single characters, and colors.
// Supports both direct console input and parsing of existing strings (for most types). 
// https://gist.github.com/aaronfranke/4772dece4b8c9876d6b1e2d7e0739747 
package testing123;

import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Obtain input safely, without any crashing.
 *
 * @author Aaron Franke
 */
public final class Parser {

    private static final Pattern DECIMALPATTERN = Pattern.compile("([0-9]*)\\.([0-9]*)");
    private static final Pattern DECIMALPATTERNNEG = Pattern.compile("[-+]?([0-9]*)\\.([0-9]*)");
    private static final Pattern INTEGERPATTERN = Pattern.compile("([0-9]*)");
    private static final Pattern INTEGERPATTERNNEG = Pattern.compile("[-+]?([0-9]*)");

    private static final Scanner KEYBOARD = new Scanner(System.in);

    // Determines whether the String is a valid floating number. Used in the floating methods. 
    private static boolean floatPattern(String input) {
        int length = input.length();
        if (length < 1) {
            return false;
        }

        if (DECIMALPATTERN.matcher(input).matches()) {
            // This code below is important for preventing "." from crashing the program. 
            return length != 1;

        } else if (DECIMALPATTERNNEG.matcher(input).matches()) {
            // This code below is important for preventing "-." from crashing the program. 
            return length != 2;

        }
        // If it doesn't contain a decimal point, check just for an integer pattern.
        return integerPattern(input);
    }

    // Determines whether the String is a valid integer number. Used in many places. 
    private static boolean integerPattern(String input) {
        return INTEGERPATTERN.matcher(input).matches()
                || INTEGERPATTERNNEG.matcher(input).matches();
    }

    // Start of console input methods. 
    /**
     * Parses longs from console input.
     *
     * @return A whole number up to about 9 quintillion.
     */
    public static final long integer64() {
        while (true) {
            String input = KEYBOARD.next();
            if (integerPattern(input)) {
                try {
                    long number = Long.parseLong(input);
                    return number;
                } catch (java.lang.NumberFormatException e) {
                    System.out.print("Are you trying to break the program? Try again: ");
                }
            } else {
                System.out.println("That's not a whole number! ");
                System.out.print("Try again: ");
            }
        }
    }

    /**
     * Parses integers from console input.
     *
     * @return A whole number up to about 2 billion.
     */
    public static final int integer32() {
        while (true) {
            long inputNumber = integer64();
            int number = (int) inputNumber;
            if (number == inputNumber) {
                return number;
            } else {
                if (inputNumber > 0) {
                    System.out.println("That's too big! ");
                } else {
                    System.out.println("That's too small! ");
                }
                System.out.print("Try again: ");
            }
        }
    }

    /**
     * Parses shorts from console input.
     *
     * @return A whole number up to about 30 thousand.
     */
    public static final short integer16() {
        while (true) {
            long inputNumber = integer64();
            short number = (short) inputNumber;
            if (number == inputNumber) {
                return number;
            } else {
                if (inputNumber > 0) {
                    System.out.println("That's too big! ");
                } else {
                    System.out.println("That's too small! ");
                }
                System.out.print("Try again: ");
            }
        }
    }

    /**
     * Parses bytes from console input.
     *
     * @return A whole number up to 127.
     */
    public static final byte integer8() {
        while (true) {
            long inputNumber = integer64();
            byte number = (byte) inputNumber;
            if (number == inputNumber) {
                return number;
            } else {
                if (inputNumber > 0) {
                    System.out.println("That's too big! ");
                } else {
                    System.out.println("That's too small! ");
                }
                System.out.print("Try again: ");
            }
        }
    }

    /**
     * Parses doubles from console input.
     *
     * @return A 64-bit floating-point number.
     */
    public static final double floating64() {
        while (true) {
            String input = KEYBOARD.next();
            if (floatPattern(input)) {
                return Double.parseDouble(input);
            } else {
                System.out.println("That's not a number! ");
                System.out.print("Try again: ");
            }
        }
    }

    /**
     * Parses floats from console input.
     *
     * @return A 32-bit floating-point number.
     */
    public static final float floating32() {
        while (true) {
            String input = KEYBOARD.next();
            if (floatPattern(input)) {
                return Float.parseFloat(input);
            } else {
                System.out.println("That's not a number! ");
                System.out.print("Try again: ");
            }
        }
    }

    /**
     * Parses text without numbers from console input.
     *
     * @return Text that isn't composed of numbers.
     */
    public static final String text() {
        while (true) {
            String input = KEYBOARD.nextLine();
            if (floatPattern(input)) {
                System.out.println("That's a number! Type some text instead. ");
                System.out.print("Try again: ");
            } else {
                return input;
            }
        }
    }

    /**
     * Parses characters from the given input.
     *
     * @return One character.
     */
    public static final char character() {
        while (true) {
            String input = KEYBOARD.next();
            if (input.toLowerCase().equals("none") || input.toLowerCase().equals("space")) {
                return ' ';
            } else if (input.length() == 1) {
                return input.charAt(0);
            } else {
                System.out.println("That's not a single character! ");
                System.out.print("Try again: ");
            }
        }
    }

    /**
     * Parses booleans from console input.
     *
     * @return Either true or false.
     */
    public static final boolean bool() {
        while (true) {
            String input = KEYBOARD.next().toLowerCase();

            if (input.startsWith("t") || input.startsWith("y")
                    || input.startsWith("a") || input.startsWith("o")
                    || input.startsWith("p")) {
                // Accept: True, Yes, Affirmative, Allow, Oui, Proceed, and Positive. 
                return true;
            } else if (input.startsWith("f") || input.startsWith("n")
                    || input.startsWith("c") || input.startsWith("d")
                    || input.startsWith("r")) {
                // Accept: False, No, Negative, Cancel, Decline, Deny, Reject, and Refuse. 
                return false;
            }
            System.out.println("That's not a boolean value! (True/False, Yes/No, etc) ");
            System.out.print("Try again: ");
        }
    }

    /**
     * Parses colors from console input.
     *
     * @return A char representing a color, can be r, g, b, c, m, y, k, w, or s.
     */
    public static final char color() {
        while (true) {
            String input = KEYBOARD.next();
            input = input.toLowerCase();
            if (input.equals("reset")) { // Reset color 
                return 's';
            } else if (input.startsWith("bla") || input.startsWith("k")) { // Black 
                return 'k';
            } else if (input.startsWith("w")) { // White 
                return 'w';
            } else if (input.startsWith("r")) { // Red 
                return 'r';
            } else if (input.startsWith("b")) { // Blue 
                return 'b';
            } else if (input.startsWith("g")) { // Green 
                return 'g';
            } else if (input.startsWith("c")) { // Cyan 
                return 'c';
            } else if (input.startsWith("m")) { // Magenta 
                return 'm';
            } else if (input.startsWith("y")) { // Yellow 
                return 'y';
            } else if (input.startsWith("o")) { // Orange 
                return 'y'; // Yes this is intentional
            } else {
                System.out.println("That's not a color! ");
                System.out.print("Try again: ");
            }
        }
    }

    // End of console input methods. 
    // Next come the overloaded methods which accept strings as inputs. 
    // They return -1 when the input is invalid. 
    /**
     * Parses longs from the given input.
     *
     * @param input The input string.
     * @return A whole number up to about 9 quintillion.
     */
    public static final long integer64(String input) {
        long number = -1;
        if (integerPattern(input)) {
            try {
                number = Long.parseLong(input);
            } catch (java.lang.NumberFormatException e) {
                System.out.print("Error: Number is too large for any integer type. ");
                return -1;
            }
        } else {
            System.out.println("Error: String is not a whole number. ");
            return -1;
        }
        return number;
    }

    /**
     * Parses longs from an input string.
     *
     * @param input The input string.
     * @return A whole number up to about 2 billion.
     */
    public static final int integer32(String input) {
        long inputNumber = integer64(input);
        int number = (int) inputNumber;
        if (number != inputNumber) {
            System.out.print("Error: Whole number is too large for type int. ");
            return -1;
        }
        return number;
    }

    /**
     * Parses shorts from an input string.
     *
     * @param input The input string.
     * @return A whole number up to about 32 thousand.
     */
    public static final short integer16(String input) {
        long inputNumber = integer64(input);
        short number = (short) inputNumber;
        if (number != inputNumber) {
            System.out.print("Error: Whole number is too large for type short. ");
            return -1;
        }
        return number;
    }

    /**
     * Parses bytes from an input string.
     *
     * @param input The input string.
     * @return A whole number up to 127.
     */
    public static final byte integer8(String input) {
        long inputNumber = integer64(input);
        byte number = (byte) inputNumber;
        if (number != inputNumber) {
            System.out.print("Error: Whole number is too large for type byte. ");
            return -1;
        }
        return number;
    }

    /**
     * Parses doubles from an input string.
     *
     * @param input The input string.
     * @return A 64-bit floating-point number.
     */
    public static final double floating64(String input) {
        double number = Double.NaN;
        if (floatPattern(input)) {
            number = Double.parseDouble(input);
        } else {
            System.out.println("Error: String is not a number. ");
        }
        return number;
    }

    /**
     * Parses floats from an input string.
     *
     * @param input The input string.
     * @return A 32-bit floating-point number.
     */
    public static final float floating32(String input) {
        float number = Float.NaN;
        if (floatPattern(input)) {
            number = Float.parseFloat(input);
        } else {
            System.out.println("Error: String is not a number. ");
        }
        return number;
    }

    /**
     * Parses booleans from an input string.
     *
     * @param input The input string.
     * @return True or false.
     */
    public static final boolean bool(String input) {
        String in = input.toLowerCase();
        // Accept: True, Yes, Affirmative, Allow, Oui, Proceed, and Positive. 
        boolean result = in.startsWith("t") || in.startsWith("y")
                || in.startsWith("a") || in.startsWith("o") || in.startsWith("p");
        return result;
    }

    /**
     * Parses colors from an input string.
     *
     * @param input The input string.
     * @return A char representing a color, or 's' if invalid.
     */
    public static final char color(String input) {
        input = input.toLowerCase();
        if (input.startsWith("res") || input.startsWith("s")) { // Reset color 
            return 's';
        } else if (input.startsWith("bla") || input.startsWith("k")) { // Black 
            return 'k';
        } else if (input.startsWith("w")) { // White 
            return 'w';
        } else if (input.startsWith("r")) { // Red 
            return 'r';
        } else if (input.startsWith("b")) { // Blue 
            return 'b';
        } else if (input.startsWith("g")) { // Green 
            return 'g';
        } else if (input.startsWith("c")) { // Cyan 
            return 'c';
        } else if (input.startsWith("m")) { // Magenta 
            return 'm';
        } else if (input.startsWith("y")) { // Yellow 
            return 'y';
        } else if (input.startsWith("o")) { // Orange 
            return 'y'; // Yes this is intentional
        } else {
            System.out.println("Error: String is not a color. Returning reset color. ");
        }
        return 's';
    }

    // End of overloaded methods. 
}
