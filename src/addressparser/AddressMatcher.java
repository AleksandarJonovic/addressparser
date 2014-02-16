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

    private static ArrayList<String> rawAddressList = new ArrayList<>();
    private static ArrayList<String> cleanAddressList = new ArrayList<>();

    public AddressMatcher() throws FileNotFoundException {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("road_names.txt"), "LATIN1"));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                rawAddressList.add(line);
                cleanAddressList.add(cleanString(line));
                line = br.readLine();
            }
            br.close();
            Collections.sort(rawAddressList);
            Collections.sort(cleanAddressList);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void matchStreet(String[] address) {
        long startTime = System.currentTimeMillis();
        String input = cleanString(address[0]);
        if (cleanAddressList.contains(input)) {
            System.out.println("Matched address " + input);
            System.out.println("It took " + ((System.currentTimeMillis() - startTime) / 1000.0) + " seconds");
        } else {
            System.out.println("No match for address " + input);
            System.out.println("It took " + ((System.currentTimeMillis() - startTime) / 1000.0) + " seconds");
        }

    }

    /**
     * 
     * @param input a string which has to be gone through to find an address.
     * @return
     * @throws InvalidInputException 
     */
    private static String checkAddressExist(String input) throws InvalidInputException {
        int indexLow = 0;
        int indexHigh = cleanAddressList.size();
        for (int i = 0; i < input.length(); i++) {
            boolean firstAppereance = true;
            boolean lastAppereance = true;
            for (int j = indexLow; j < indexHigh; j++) {
                String s3 = cleanAddressList.get(j);
                if (s3.length() < i + 1) {
                    continue;
                }
                String s1 = s3.substring(0, i + 1);
                String s2 = input.substring(0, i + 1);
                //System.out.println(s1);
                //System.out.println(s2);
                if (s1.equals(s2) && firstAppereance == true) {
                    firstAppereance = false;
                    indexLow = j;
                }
                int counter = 0;
                do {
                    int charValue = s2.charAt(i);
                    String next = String.valueOf((char) (charValue + 1));
                    s2 = input.substring(0, i) + next;
                    counter++;
                } while (!s1.equals(s2) && counter < 75 && lastAppereance == true);
                if (s1.equals(s2) && lastAppereance == true) {
                    lastAppereance = false;
                    indexHigh = j;
                }
            }
            firstAppereance = true;
            lastAppereance = true;

            if (indexHigh - indexLow <= 5) {
                InvalidInputException ex = new InvalidInputException();
                for (int k = indexHigh; k >= indexLow; k--) {
                    if (input.length() >= cleanAddressList.get(k).length()) {
                        if (cleanAddressList.get(k).equals(input.substring(0, cleanAddressList.get(k).length()))) {
                            System.out.println("Best match: " + cleanAddressList.get(k));
                            return cleanAddressList.get(k);
                        }
                    }
                    ex.addPossibleStreetName(cleanAddressList.get(k));
                }
                throw ex;
            }
        }
        throw new InvalidInputException("No street matches");
    }

    /**
     * 
     * @param input some String which needs to be cleaned
     * @return 
     */
    private static String cleanString(String input) {
        String cleanString;

        String dirtyString = input;

        //Special Characters Enum used to extract what to cleanse.
        for (SpecialChars s : SpecialChars.values()) {
            dirtyString = dirtyString.replaceAll(s.getChar(), "");
        }

        dirtyString = dirtyString.toLowerCase();
        cleanString = dirtyString;

        return cleanString;
    }

    /**
     * The active main method of the program. Waits for the user to input some
     * text and prints the address information in the correct format.
     * If something went wrong, the exception information will be printed instead.
     *
     * @param args
     * @throws InvalidInputException
     */
    public static void main(String[] args) throws InvalidInputException {
        AddressMatcher am;
        //Parser p = new Parser(); // Atm no need to have a direct connection.
        Addressparser ap = new Addressparser();
        String[] input;
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

            String s2 = cleanString(s);
            s = s.toLowerCase();
            try {
                String streetName = checkAddressExist(s2);
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
