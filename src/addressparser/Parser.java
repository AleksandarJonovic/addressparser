/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package addressparser;

import ENUMS.Regex;
import ENUMS.SpecialChars;
import ExceptionPackage.InvalidInputException;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Parser {


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
    public String checkAddressExist(String input) throws InvalidInputException {

        try {
            AddressMatcher am = new AddressMatcher();

        int indexLow = 0;
        int indexHigh = am.cleanAddressList.size();
        for (int i = 0; i < input.length(); i++) {
            boolean firstAppereance = true;
            boolean lastAppereance = true;
            for (int j = indexLow; j < indexHigh; j++) {
                String s3 = am.cleanAddressList.get(j);
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
            if (indexHigh - indexLow <= 5) {
                InvalidInputException ex = new InvalidInputException();
                for (int k = indexHigh; k >= indexLow; k--) {
                    if (input.length() >= am.cleanAddressList.get(k).length()) {
                        if (am.cleanAddressList.get(k).equals(input.substring(0, am.cleanAddressList.get(k).length()))) {
                            System.out.println("Best street name match: " + am.cleanAddressList.get(k));
                            return am.cleanAddressList.get(k);
                        }
                    }
                    ex.addPossibleStreetName(am.cleanAddressList.get(k));
                }
                throw ex;
            }
        }
        throw new InvalidInputException("No street matches");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     *
     * @param input some String which needs to be cleaned
     * @return
     */
    public String cleanString(String input) {
        String cleanString;
        String dirtyString = input;
        for (SpecialChars s : SpecialChars.values()) {
            dirtyString = dirtyString.replaceAll(s.getChar(), "");
        }
        dirtyString = dirtyString.toLowerCase();
        cleanString = dirtyString;
        return cleanString;
    }

    /**
     * Takes a string and splits it up into the different blocks that an address
     * is made of and then return the array with the blocks. If the parsed
     * address does not contain some information, null will take the
     * corrosponding spot in the array.
     *
     * @param parseMe the address to parse. This must be a single address.
     * @return a String array with all the blocks an address is made of.
     * @throws ExceptionPackage.InvalidInputException
     */
        public String[] parseThis(String parseMe) throws InvalidInputException
    {
        // checks on the parseMe string
        if (parseMe.contains("?"))
        {
            throw new InvalidInputException("Address contains an ?");
        } else if (parseMe.contains("!"))
        {
            throw new InvalidInputException("Address contains an !");
        } else if (parseMe.length() < 3)
        {
            throw new InvalidInputException("The input is too short");
        }else if (parseMe.length() > 100)
        {
            throw new InvalidInputException("The input is too long");
        }

        String[] result = new String[6]; // the array to return

        // go through the expressions
        for (int i = 0; i < Regex.values().length; i++)    /* 1 */

        {
            // create a pattern with the expression.
            Pattern pattern = Pattern.compile(Regex.values()[i].getRegex(), Pattern.MULTILINE);

            // replace " i " with a " , " for the regEx to divide the different blocks.
            parseMe = parseMe.replace(" i ", " , ").trim();

            Matcher matcher = pattern.matcher(parseMe);

            // find matches if any in the parseMe String
            while (matcher.find())                      /* 2 */

            {
                // create a new string with the first matched find.
                String matchedString = parseMe.substring(matcher.start(), matcher.end());
                // remove nonwanted chars
                matchedString = matchedString.replace(",", "").trim();
                if (!matchedString.equals(""))          /* 3 */
                {
                    // add the string to the resultset on the corrosponding spot.
                    result[i] = matchedString.replace(".", "").trim();
                } else
                {
                    // ... unless it was an empty string
                    result[i] = null;
                }

                /**
                 * remove the first appearence of the found string so that the
                 * regEx will not find it again.
                 */
                parseMe = parseMe.trim();
                parseMe += " ";
                while (parseMe.substring(0,1).equals(","))
                    parseMe = parseMe.replaceFirst(",", "");
                parseMe = parseMe.replaceFirst(matchedString, "").trim();
                // break out and go on to the next regEx
                break;
            }

        }
        return result;
    }
        public String parseStreetAddress(String s){
                    s = cleanString(s);
            try {
                Addressparser ap = new Addressparser();
                String streetName = checkAddressExist(s);
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
                
                return s;
            } catch (InvalidInputException ex) {
                // if no other street names was found it will print the cause.
                ex.printPossibleStreetName();
                System.out.println("");
            }
            return s;
        }

}
