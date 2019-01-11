package testing123;

// Base object class. Make everything inherit from this.
// You can add useful code here to make it available everywhere.
public abstract class Base {

    // Makes "print()" a short-hand for "System.out.println()"
    public static void print(Object o) {
        System.out.println(o);
    }

    // Makes "printN()" a short-hand for "System.out.print()"
    public static void printN(Object o) {
        System.out.print(o);
    }

    // For when you don't want to print out a bajillion decimals.
    public static java.text.DecimalFormat df = new java.text.DecimalFormat("0.000");
    // Same as above, but two decimals, for money.
    public static java.text.DecimalFormat mf = new java.text.DecimalFormat("0.00");

}
