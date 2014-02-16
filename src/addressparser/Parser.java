/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package addressparser;

import ENUMS.Regex;
import ExceptionPackage.InvalidInputException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Parser {

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
}
