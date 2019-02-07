/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testing123;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 *
 * @author aaronfranke
 */
public class ParserTest {

    private static final String[] GOODINTEGERS = {
        "5", "+5", "-5"
    };

    private static final String[] GOODFLOATS = {
        ".5", "+.5", "-.5", "+5.", "-5.", "+5.5", "-5.5"
    };

    private static final String[] BADNUMBERS = {
        "", ".", "+.", "-.", "-.", "-."
    };

    private static final String[] GOODBOOLEANS = {
        "yes", "true", "awesome", "oui"
    };

    private static final String[] BADBOOLEANS = {
        "", "no", "false", "bad", "negative"
    };

    private static final String[] GOODCOLORS = {
        "red", "green", "blue", "cyan", "magenta", "yellow", "black", "white"
    };

    private static final String[] BADCOLORS = {
        "", "s", "reset", "no", "false", "negative", "true", "awesome"
    };

    public ParserTest() {
    }

    /**
     * Test of integer64 method, of class Parser.
     */
    @Test
    public void testInteger64_String() {
        for (String s : GOODINTEGERS) {
            assertTrue(Parser.integer64(s) != -1);
        }
        for (String s : BADNUMBERS) {
            assertTrue(Parser.integer64(s) == -1);
        }
    }

    /**
     * Test of integer32 method, of class Parser.
     */
    @Test
    public void testInteger32_String() {
        for (String s : GOODINTEGERS) {
            assertTrue(Parser.integer32(s) != -1);
        }
        for (String s : BADNUMBERS) {
            assertTrue(Parser.integer32(s) == -1);
        }
    }

    /**
     * Test of integer16 method, of class Parser.
     */
    @Test
    public void testInteger16_String() {
        for (String s : GOODINTEGERS) {
            assertTrue(Parser.integer16(s) != -1);
        }
        for (String s : BADNUMBERS) {
            assertTrue(Parser.integer16(s) == -1);
        }
    }

    /**
     * Test of integer8 method, of class Parser.
     */
    @Test
    public void testInteger8_String() {
        for (String s : GOODINTEGERS) {
            assertTrue(Parser.integer8(s) != -1);
        }
        for (String s : BADNUMBERS) {
            assertTrue(Parser.integer8(s) == -1);
        }
    }

    /**
     * Test of floating64 method, of class Parser.
     */
    @Test
    public void testFloating64_String() {
        for (String s : GOODINTEGERS) {
            assertFalse(Double.isNaN(Parser.floating64(s)));
        }
        for (String s : GOODFLOATS) {
            assertFalse(Double.isNaN(Parser.floating64(s)));
        }
        for (String s : BADNUMBERS) {
            assertTrue(Double.isNaN(Parser.floating64(s)));
        }
    }

    /**
     * Test of floating32 method, of class Parser.
     */
    @Test
    public void testFloating32_String() {
        for (String s : GOODINTEGERS) {
            assertFalse(Float.isNaN(Parser.floating32(s)));
        }
        for (String s : GOODFLOATS) {
            assertFalse(Float.isNaN(Parser.floating32(s)));
        }
        for (String s : BADNUMBERS) {
            assertTrue(Float.isNaN(Parser.floating32(s)));
        }
    }

    /**
     * Test of bool method, of class Parser.
     */
    @Test
    public void testBool_String() {
        for (String s : GOODBOOLEANS) {
            assertTrue(Parser.bool(s));
        }
        for (String s : BADBOOLEANS) {
            assertFalse(Parser.bool(s));
        }
    }

    /**
     * Test of color method, of class Parser.
     */
    @Test
    public void testColor_String() {
        for (String s : GOODCOLORS) {
            assertTrue(Parser.color(s) != 's');
        }
        for (String s : BADCOLORS) {
            assertTrue(Parser.color(s) == 's');
        }
    }

}
