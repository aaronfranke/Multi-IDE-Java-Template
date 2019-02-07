package testing123;

/**
 * This is the main class, which runs everything else.
 *
 * @author Aaron Franke
 * @version None
 */
public class App extends Base {

    /**
     * This is the main method, which runs everything else.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        print("Hello World! " + df.format(Parser.integer8("5")));
    }

}
