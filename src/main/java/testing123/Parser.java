
// Parser.java - Obtain input safely, without any crashing. By Aaron Franke. 
// Supports all integer types, both float types, non-numeric text, single characters, and colors.
// Supports both direct console input and parsing of existing strings (for most types). 
// https://gist.github.com/aaronfranke/4772dece4b8c9876d6b1e2d7e0739747 

package testing123;

import java.util.Scanner; 
import java.util.regex.*; 

public final class Parser { 
    
    private static final String DECIMALPATTERN = "([0-9]*)\\.([0-9]*)"; 
    private static final String DECIMALPATTERNNEG = "-([0-9]*)\\.([0-9]*)"; 
    private static final Scanner KEYBOARD = new Scanner(System.in); 
    
    // Determines whether the String is a valid floating number. Used in the floating methods. 
    private static boolean floatPattern(String input) { 
        boolean isNumber = false;
        
        if (Pattern.matches(DECIMALPATTERN, input)){ 
            isNumber = true; 
            // This code below is important for preventing "." from crashing the program. 
            char[] chars = input.toCharArray();
            if (chars.length == 1) {
                return false;
            }
            
        } else if (Pattern.matches(DECIMALPATTERNNEG, input)) { 
            isNumber = true; 
            // This code below is important for preventing "-." from crashing the program. 
            char[] chars = input.toCharArray();
            if (chars.length == 2) {
                isNumber = false;
            }
            
        } else if (input.matches("\\d+")) { 
            isNumber = true; 
        } else if (input.matches("-\\d+")) { 
            isNumber = true; 
        } 
        return isNumber; 
    } 
    
    // Start of console input methods. 
    
    public static final long integer64() { //Returns whole numbers up to about 9 quintillion. 
        boolean invalid = true; 
        long number = 0; 
        while (invalid) { 
            String input = KEYBOARD.next(); 
            if (input.matches("\\d+") || input.matches("-\\d+")){ 
                invalid = false; 
                try { 
                    number = Long.parseLong(input); 
                } catch (java.lang.NumberFormatException e) { 
                    invalid = true; 
                    System.out.print("Are you trying to break the program? Try again: "); 
                } 
            } else { 
                System.out.println("That's not a whole number! "); 
                System.out.print("Try again: "); 
            } 
        } 
        return number; 
    } 
    
    public static final int integer32() { //Returns whole numbers up to about 2 billion. Depends on integer64(). 
        boolean invalid = true; 
        int number = 0; 
        while (invalid) { 
            long inputNumber = integer64();
            if (inputNumber < 2147483601 && inputNumber > -2147483601) { 
                invalid = false; 
                number = (int)inputNumber; 
            } else { 
                if (inputNumber > 2147483601) { 
                    System.out.println("That's too big! "); 
                } else { 
                    System.out.println("That's too small! "); 
                } 
                System.out.print("Try again: "); 
            } 
        } 
        return number; 
    } 
    
    public static final short integer16() { //Returns whole numbers up to about 30 thousand. Depends on integer64(). 
        boolean invalid = true; 
        short number = 0; 
        while (invalid) { 
            long inputNumber = integer64();
            if (inputNumber < 32761 && inputNumber > -32761) { 
                invalid = false; 
                number = (short)inputNumber; 
            } else { 
                if (inputNumber > 32761) { 
                    System.out.println("That's too big! "); 
                } else { 
                    System.out.println("That's too small! "); 
                } 
                System.out.print("Try again: "); 
            } 
        } 
        return number; 
    } 

    public static final byte integer8() { //Returns whole numbers up to 125. Depends on integer64(). 
        boolean invalid = true; 
        byte number = 0; 
        while (invalid) { 
            long inputNumber = integer64();
            if (inputNumber < 126 && inputNumber > -126) { 
                invalid = false; 
                number = (byte)inputNumber; 
            } else { 
                if (inputNumber > 126) { 
                    System.out.println("That's too big! "); 
                } else { 
                    System.out.println("That's too small! "); 
                } 
                System.out.print("Try again: "); 
            } 
        } 
        return number; 
    } 
    
    public static final double floating64() { //Returns floating numbers with 64 bits of precision. 
        boolean invalid = true; 
        double number = 0; 
        while (invalid) { 
            String input = KEYBOARD.next(); 
            if (floatPattern(input)){ 
                number = Double.parseDouble(input); 
                invalid = false; 
            } else { 
                System.out.println("That's not a number! "); 
                System.out.print("Try again: "); 
            } 
        } 
        return number; 
    } 
    
    public static final float floating32() { //Returns floating numbers with 32 bits of precision. 
        boolean invalid = true; 
        float number = 0; 
        while (invalid) { 
            String input = KEYBOARD.next(); 
            if (floatPattern(input)){ 
                number = Float.parseFloat(input); 
                invalid = false; 
            } else { 
                System.out.println("That's not a number! "); 
                System.out.print("Try again: "); 
            } 
        } 
        return number; 
    } 
    
    public static final String text() { //Returns text that isn't composed of numbers. 
        boolean invalid = true; 
        String text = " "; 
        while (invalid) { 
            String input = KEYBOARD.nextLine(); 
            if (floatPattern(input)){ 
                System.out.println("That's a number! Type some text instead. "); 
                System.out.print("Try again: "); 
            } else {
                text = input; 
                invalid = false; 
            }
        } 
        return text; 
    } 
    
    public static final char character() { //Returns a single character and requests more input if the input is invalid. 
        boolean invalid = true; 
        char character = ' '; 
        while (invalid) { 
            String input = KEYBOARD.next(); 
            if (input.toLowerCase().equals("none") || input.toLowerCase().equals("space")) { 
                character = ' '; 
                invalid = false; 
            } else if (input.length() == 1){ 
                character = input.charAt(0); 
                invalid = false; 
            } else { 
                System.out.println("That's not a single character! "); 
                System.out.print("Try again: "); 
            } 
        } 
        return character; 
    } 
    
    public static final boolean bool() { //Returns a simple true or false boolean. 
        while (true) { 
            String input = KEYBOARD.next().toLowerCase(); 
            
            // Accept True, Yes, Affirmative, Accept, Allow, Oui, Proceed, and Positive. 
            if (input.startsWith("t") || input.startsWith("y")
                            || input.startsWith("a") || input.startsWith("o") || input.startsWith("p")) { 
                return true;
            // Accept False, No, Negative, Cancel, Decline, Deny, Reject, and Refuse. 
            } else if (input.startsWith("f") || input.startsWith("n")
                            || input.startsWith("c") || input.startsWith("d") || input.startsWith("r")){ 
                return false;
            } 
            System.out.println("That's not a boolean value! (True/False, Yes/No, etc) "); 
            System.out.print("Try again: "); 
        } 
    } 
    
    public static final char color() { //Returns a color based on name or letter and requests more input if the input is invalid. 
        boolean invalid = true; 
        char character = 's'; 
        while (invalid) { 
            String input = KEYBOARD.next(); 
            input = input.toLowerCase(); 
            if (input.equals("reset")) { //Default color 
                invalid = false; 
                character = 's'; 
            } else if (input.startsWith("bla") || input.startsWith("k")) { //Black 
                invalid = false; 
                character = 'k'; 
            } else if (input.startsWith("r")) { //Red 
                invalid = false; 
                character = 'r'; 
            } else if (input.startsWith("b")) { //Blue 
                invalid = false; 
                character = 'b'; 
            } else if (input.startsWith("g")) { //Green 
                invalid = false; 
                character = 'g'; 
            } else if (input.startsWith("w")) { //White 
                invalid = false; 
                character = 'w'; 
            } else if (input.startsWith("c")) { //Cyan 
                invalid = false; 
                character = 'c'; 
            } else if (input.startsWith("m")) { //Magenta 
                invalid = false; 
                character = 'm'; 
            } else if (input.startsWith("o")) { //Orange 
                invalid = false; 
                character = 'y'; 
            } else if (input.startsWith("y")) { //Yellow 
                invalid = false; 
                character = 'y'; 
            } else { 
                System.out.println("That's not a color! "); 
                System.out.print("Try again: "); 
            } 
        } 
        return character; 
    } 
    
    // End of console input methods. 
    
    // Next come the overloaded methods which accept strings as inputs. They return 0 when the input is invalid. 
    
    public static final long integer64(String input) { //Returns whole numbers up to about 9 quintillion. 
        long number = 0; 
        if (input.matches("\\d+") || input.matches("-\\d+")){ 
            try { 
                number = Long.parseLong(input); 
            } catch (java.lang.NumberFormatException e) { 
                System.out.print("Error: Whole number is too large for any integer type. Returning 0. "); 
            } 
        } else { 
            System.out.println("Error: String is not a whole number. Returning 0. "); 
        } 
        return number; 
    } 
    
    public static final int integer32(String input) { //Returns whole numbers up to about 2 billion. Depends on integer64(). 
        int number = 0; 
        long inputNumber = integer64(input); 
        if (inputNumber < 2147483601 && inputNumber > -2147483601) { 
            number = (int)inputNumber; 
        } else { 
            System.out.print("Error: Whole number is too large for type int. Try long instead. Returning 0. "); 
        } 
        return number; 
    } 
    
    public static final short integer16(String input) { //Returns whole numbers up to about 32 thousand. Depends on integer64(). 
        short number = 0; 
        long inputNumber = integer64(input);
        if (inputNumber < 32761 && inputNumber > -32761) { 
            number = (short)inputNumber; 
        } else { 
            System.out.print("Error: Whole number is too large for type short. Try int instead. Returning 0. "); 
        } 
        return number; 
    } 

    public static final byte integer8(String input) { //Returns whole numbers up to 125. Depends on integer64(). 
        byte number = 0; 
        long inputNumber = integer64(input); 
        if (inputNumber < 126 && inputNumber > -126) { 
            number = (byte)inputNumber; 
        } else { 
            System.out.println("Error: Whole number is too large for type byte. Try short instead. Returning 0. "); 
        } 
        return number; 
    } 
    
    public static final double floating64(String input) { //Returns floating numbers with 64 bits of precision. 
        double number = 0; 
        if (floatPattern(input)){ 
            number = Double.parseDouble(input); 
        } else { 
            System.out.println("Error: String is not a number. Returning 0. "); 
        } 
        return number; 
    } 
    
    public static final float floating32(String input) { //Returns floating numbers with 32 bits of precision. 
        float number = 0; 
        if (floatPattern(input)){ 
            number = Float.parseFloat(input); 
        } else { 
            System.out.println("Error: String is not a number. Returning 0. "); 
        } 
        return number; 
    } 
    
    public static final boolean bool(String input) { //Returns a simple true or false boolean. 
        
        String in = input.toLowerCase();
        // Accept True, Yes, Affirmative, Accept, Allow, Oui, Proceed, and Positive. 
        boolean result = in.startsWith("t") || in.startsWith("y")
                        || in.startsWith("a") || in.startsWith("o") || in.startsWith("p");
                return result;
    } 
    
    public static final char color(String input) { //Returns a color based on name or letter. Returns "s" if invalid. 
        char character = 's'; 
        input = input.toLowerCase(); 
        if (input.equals("reset") || input.startsWith("s")) { //Default color 
            character = 's'; 
        } else if (input.startsWith("bla") || input.startsWith("k")) { //Black 
            character = 'k'; 
        } else if (input.startsWith("r")) { //Red 
            character = 'r'; 
        } else if (input.startsWith("b")) { //Blue 
            character = 'b'; 
        } else if (input.startsWith("g")) { //Green 
            character = 'g'; 
        } else if (input.startsWith("w")) { //White 
            character = 'w'; 
        } else if (input.startsWith("c")) { //Cyan 
            character = 'c'; 
        } else if (input.startsWith("m")) { //Magenta 
            character = 'm'; 
        } else if (input.startsWith("o")) { //Orange 
            character = 'y'; 
        } else if (input.startsWith("y")) { //Yellow 
            character = 'y'; 
        } else { 
            System.out.println("Error: String is not a color. Returning reset color. "); 
        } 
        return character; 
    } 
    
    // End of overloaded methods. 
    
} 







