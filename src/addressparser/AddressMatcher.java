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

    /**
     * Not used atm as of 16-12-2014
     *
     * @param address the parsed addresses
     */
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
     * Takes the input, and character by character broaden the streetnames in
     * the streetname file, to match the input. If there are 5 or less elements
     * that matches at any given point, then it tests if they are the wanted
     * element.
     *
     * @param input a string which has to be gone through to find an address.
     * @return
     * @throws InvalidInputException
     */
    public static String checkAddressExist(String input) throws InvalidInputException {
        int indexLow = 0;
        int indexHigh = cleanAddressList.size();
        // this loop goes widens the substring of the input so in the beginning
        // it is only the first substring.
        for (int i = 0; i < input.length(); i++) {
            boolean firstAppereance = true;
            boolean lastAppereance = true;

            //   Here we search through the streetnames file, if their substring
            //    matches the input substring of the same length then make a new
            //    lowest index and a new highest index. This way the lowest and
            //    highest index will at some point be close to eachother.
            for (int j = indexLow; j < indexHigh; j++) {
                String s3 = cleanAddressList.get(j);
                if (s3.length() < i + 1) {
                    continue;
                }
                String s1 = s3.substring(0, i + 1);
                String s2 = input.substring(0, i + 1);

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

            //  If the code was successful at finding 2 indexes close to
            //  eachother then we check through each of those results that are on
            //  those indexes. If any of the matches the substring of the input
            //  with that length then return it, otherwise throw an exception
            //  with those matches it did find
            if (indexHigh - indexLow <= 5) {
                InvalidInputException ex = new InvalidInputException();
                for (int k = indexHigh; k >= indexLow; k--) {
                    if (input.length() >= cleanAddressList.get(k).length()) {
                        if (cleanAddressList.get(k).equals(input.substring(0, cleanAddressList.get(k).length()))) {
                            System.out.println("Best street name match: " + cleanAddressList.get(k));
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
    public static String cleanString(String input) {
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
