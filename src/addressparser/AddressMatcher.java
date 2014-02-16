package addressparser;

import ENUMS.SpecialChars;
import ExceptionPackage.InvalidInputException;

import java.io.*;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Christian on 12/02/14.
 */
public class AddressMatcher {

    protected static ArrayList<String> rawAddressList = new ArrayList<>();
    protected static ArrayList<String> cleanAddressList = new ArrayList<>();

    /**
     * The constructor of the AddressMatcher. It loads in the road_names.txt
     * file, and creates two arrays, a cleaned one and a raw one. The clean one
     * is used for comparing with user-input the other one is used for output.
     * Here we also sort the two collections.
     *
     * @throws FileNotFoundException throws it if there went something wrong
     * with reading the streetnames file.
     */
    public AddressMatcher() throws FileNotFoundException {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("road_names.txt"), "LATIN1"));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                rawAddressList.add(line);
                cleanAddressList.add(Parser.cleanString(line));
                line = br.readLine();
            }
            br.close();
            Collections.sort(rawAddressList);
            Collections.sort(cleanAddressList);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * The active main method of the program. Waits for the user to input some
     * text and prints the address information in the correct format. If
     * something went wrong, the exception information will be printed instead.
     *
     * @param args
     * @throws InvalidInputException
     */
    public static void main(String[] args) throws InvalidInputException {
        AddressMatcher am;
        //Parser p = new Parser(); // Atm no need to have a direct connection.
        Addressparser ap = new Addressparser();

        Scanner scanIn = new Scanner(System.in, "ISO-8859-1");
        

        try {
            am = new AddressMatcher();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AddressMatcher.class.getName()).log(Level.SEVERE, null, ex);
        }

        // wait for the user to input text, handle it and wait for the next.
        while (true) {
            System.out.print("Enter address: ");
            String s = scanIn.nextLine();

            //input = p.parseThis(s); // not used atm
            //matchStreet(input); // not used atm
            System.out.println("");

            String s2 = Parser.cleanString(s);
            s = s.toLowerCase();
            try {
                String streetName = Parser.checkAddressExist(s2);
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
    }
}
