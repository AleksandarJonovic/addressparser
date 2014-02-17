/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package addressparser;

import ExceptionPackage.InvalidInputException;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertArrayEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 *
 * @author Christian
 */

@RunWith(Parameterized.class)

public class AddressMatcherTest {
/*    
    private static String[] input = new String[]{"Rued Langgards Vej 7, 2300 København S", 
                             "Rued Langgaards Vej 7, 5. sal. København S",
                             "Rued Langgaards Vej 7A København S",
                             "Rued Langgaards Vej i København",
                             "2300 København S, Rued Langgaards Vej 7",
                             "København S 2300, Rued Langgaards Vej 7",
                             "10. Juli Vej, 6070 Christiansfeld",
                             "Haveforeningen af 10. maj 1918 7, 8260 Viby J",
                             "Ved Mønten 10 st 4, 2300 København S",
                             "H C Andersens Vej",
                             "Aalgade"
                             };
    */
    
    @BeforeClass
    public static void setUpClass() {
        Parser p = new Parser();
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        //Build and return Arrays as lists in format; String[] <Test Input>, String[] <Expected Output>.
        return Arrays.asList(new Object[][]{
            // old tests, the tests in the top half of the expectansy table
            {new String[]{"Brøndby Nord Vej"}, new String[]{"brøndbynordvej#####"}},
            {new String[]{"Rued Langgaards Vej"}, new String[]{"ruedlanggaardsvej#####"}},
            {new String[]{"Rued Langgaards Vej, 7, 5. sal København S"}, new String[]{"ruedlanggaardsvej#7##5##københavn s"}},
            {new String[]{"Rued Langgaards Vej, 7, 2300 København S"}, new String[]{"ruedlanggaardsvej#7###2300#københavn s"}},
            {new String[]{"Rued Langgaards Vej, 7, 2300 København S"}, new String[]{"ruedlanggaardsvej#7###2300#københavn s"}},
            {new String[]{"Rued Langgaards Vej, 7A, København S"}, new String[]{"ruedlanggaardsvej#7#a###københavn s"}},
            {new String[]{"Rued Langgaards Vej, i København"}, new String[]{"ruedlanggaardsvej#####københavn"}},
            {new String[]{"2300 København S, Rued Langgaards Vej 7"}, new String[]{"Invalid address"}},
            //next is test 8
            {new String[]{"København S 2300, Rued Langgaards Vej 7"}, new String[]{"Invalid address"}},
            {new String[]{"10. Juli Vej, 6070 Christiansfeld"}, new String[]{"10julivej####6070#christiansfeld"}},
            {new String[]{"Haveforeningen af 10. maj 1918 7, 8260 Viby J"}, new String[]{"haveforeningenaf10maj1918#7###8260#viby j"}},
            {new String[]{"Ved Mønten 10 st 2300 København S"}, new String[]{"vedmønten#10##0#2300#københavn s"}},
            {new String[]{"H C Andersens Vej"}, new String[]{"hcandersensvej#####"}},
            {new String[]{"aalgade"}, new String[]{"Invalid address"}},
            // next is test 14
            // from Structure tests and down:
            {new String[]{"2 Rued Langgaards Vej"}, new String[]{"Invalid address"}},
            {new String[]{"København S Rued Langgaards Vej"}, new String[]{"Invalid address"}},
            {new String[]{"Rued Langgaards Vej"}, new String[]{"ruedlanggaardsvej#####"}},
            {new String[]{"Rued Langgaards Vej i København S"}, new String[]{"ruedlanggaardsvej#####københavn s"}},
            {new String[]{"Rued Langgaards Vej 2"}, new String[]{"ruedlanggaardsvej#2####"}},
            {new String[]{"Rued Langgaards Vej 2300"}, new String[]{"ruedlanggaardsvej####2300#"}},
            {new String[]{"Rued Langgaards Vej 2e"}, new String[]{"ruedlanggaardsvej#2#e###"}},
            {new String[]{"Rued Langgaards Vej 2 2300"}, new String[]{"ruedlanggaardsvej#2###2300#"}},
            {new String[]{"Rued Langgaards Vej 2 København S"}, new String[]{"ruedlanggaardsvej#2####københavn s"}},
            {new String[]{"Rued Langgaards Vej 2 3sal"}, new String[]{"ruedlanggaardsvej#2##3##"}},
            {new String[]{"Rued Langgaards Vej 2e 3sal"}, new String[]{"ruedlanggaardsvej#2#e#3##"}},
            {new String[]{"Rued Langgaards Vej 2e 2300"}, new String[]{"ruedlanggaardsvej#2#e##2300#"}},
            {new String[]{"Rued Langgaards Vej 2e København S"}, new String[]{"ruedlanggaardsvej#2#e###københavn s"}},
            {new String[]{"Rued Langgaards Vej 2e 3sal 2300"}, new String[]{"ruedlanggaardsvej#2#e#3#2300#"}},
            {new String[]{"Rued Langgaards Vej 2e 3sal København S"}, new String[]{"ruedlanggaardsvej#2#e#3##københavn s"}},
            {new String[]{"Rued Langgaards Vej 2e 3sal 2300 København S"}, new String[]{"ruedlanggaardsvej#2#e#3#2300#københavn s"}},
            // next is test 30
            // Streetname tests
            {new String[]{"Vej der ikke eksisterer"}, new String[]{ "Invalid address"}},
            {new String[]{"10. Juli Vej"}, new String[]{ "10julivej#####"}},
            {new String[]{"Haveforeningen af 10. maj 1918"}, new String[]{ "haveforeningenaf10maj1918#####"}},
            {new String[]{"Åvägen"}, new String[]{ "åvägen#####"}},
            // next is test 34
            // Building number tests
            {new String[]{"Rued Langgaards Vej 22"}, new String[]{ "ruedlanggaardsvej#22####"}},
            {new String[]{"Rued Langgaards Vej 923"}, new String[]{ "ruedlanggaardsvej#923####"}},
            {new String[]{"Rued Langgaards Vej 9,"}, new String[]{ "ruedlanggaardsvej#9####"}},
            // next is test 37
            // Building letter tests
            {new String[]{"Rued Langgaards Vej 9z"}, new String[]{ "ruedlanggaardsvej#9#z###"}},
            // next is test 38
            // Floor tests
            {new String[]{"Rued Langgaards Vej 9e stuen"}, new String[]{ "ruedlanggaardsvej#9#e#0##"}},
            {new String[]{"Rued Langgaards Vej 9e st"}, new String[]{ "ruedlanggaardsvej#9#e#0##"}},
            {new String[]{"Rued Langgaards Vej 9e st."}, new String[]{ "ruedlanggaardsvej#9#e#0##"}},
            {new String[]{"Rued Langgaards Vej 9e 1."}, new String[]{ "ruedlanggaardsvej#9#e#1##"}},
            {new String[]{"Rued Langgaards Vej 9e 12."}, new String[]{ "ruedlanggaardsvej#9#e#12##"}},
            {new String[]{"Rued Langgaards Vej 9e 1 sal"}, new String[]{ "ruedlanggaardsvej#9#e#1##"}},
            {new String[]{"Rued Langgaards Vej 9e 1-sal"}, new String[]{ "ruedlanggaardsvej#9#e#1##"}},
            // next is test 45
            // Postal code tests

            {new String[]{"Rued Langgaards Vej 9e 1-sal 1991"}, new String[]{ "ruedlanggaardsvej#9#e#1#1991#"}},
            // next is test 46
            // City tests
            {new String[]{"Rued Langgaards Vej 9e 1-sal 2300 København"}, new String[]{ "ruedlanggaardsvej#9#e#1#2300#københavn"}},
            {new String[]{"Rued Langgaards Vej 9e 1-sal 2300 København S"}, new String[]{ "ruedlanggaardsvej#9#e#1#2300#københavn s"}},
            
            {new String[]{}, new String[]{}}, // placeholder for new tests
        });
    }
    
    private final String[] actual;
    private final String[] expected;

    public AddressMatcherTest(String[] actual, String[] expected) {
        this.actual = actual;
        this.expected = expected;
    }

    @Test
    public void testAll() {
        for(int i = 0; i < actual.length ;i++){
            AddressMatcher am = new AddressMatcher(actual[i]);
            actual[i] = am.getOutput();
            /*
            try {
                System.out.println(p.cleanString(p.parseStreetAddress(actual[i])));
                try {
                } catch (InvalidInputException ex) {
                    Logger.getLogger(AddressMatcherTest.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (InvalidInputException ex) {
                Logger.getLogger(AddressMatcherTest.class.getName()).log(Level.SEVERE, null, ex);
            }
            */
        }
        assertArrayEquals(expected, actual);
    }


   
    /**
     * Test of main method, of class AddressMatcher.
     * @throws java.lang.Exception
     */
    /*
    @Test
    public void testMain() throws Exception {
        AddressMatcher am;
        Addressparser ap = new Addressparser();
        try {
            am = new AddressMatcher();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AddressMatcher.class.getName()).log(Level.SEVERE, null, ex);
        }
        // wait for the user to input text, handle it and wait for the next.
        for (String s : input) {
            
            System.out.print("Input address: " + s);
            System.out.println("");

            String s2 = AddressMatcher.cleanString(s);
            s = s.toLowerCase();
            try {
                String streetName = AddressMatcher.checkAddressExist(s2);
                // Remove the way the user has typed the address...
                if (streetName != null) {
                    for (int i = 0; i < streetName.length(); i++) {
                        s = s.replaceFirst(streetName.substring(i, i + 1), "");
                    }
                }
                // ... and replace it with our version + a ","
                String s3 = streetName + ", " + s;
                System.out.println(ap.parseSingleAdress(s3));
                System.out.println("");

            } catch (InvalidInputException ex) {
                // if no other street names was found it will print the cause.
                ex.printPossibleStreetName();
                System.out.println("");
            }
        }
    }*/
}
