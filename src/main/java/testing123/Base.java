package testing123;

/**
 * Base object class. Make everything inherit from this. You can add useful code
 * here to make it available everywhere.
 *
 * @author Aaron Franke
 * @version None
 */
public abstract class Base {

    /**
     * A short-hand for "System.out.println()".
     */
    public static void print() {
        System.out.println();
    }

    /**
     * A short-hand for "System.out.println()"
     *
     * @param o The input.
     */
    public static void print(Object o) {
        System.out.println(o);
    }

    /**
     * A short-hand for "System.out.print()".
     *
     * @param o The input.
     */
    public static void printN(Object o) {
        System.out.print(o);
    }

    /**
     * For when you don't want to print out a ton of decimals.
     */
    public static java.text.DecimalFormat df = new java.text.DecimalFormat("0.000");

    /**
     * Same as above, but two decimals, for money.
     */
    public static java.text.DecimalFormat mf = new java.text.DecimalFormat("0.00");

}
