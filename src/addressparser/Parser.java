/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package addressparser;

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
        String[] expressions = new String[6]; // the array with the regex
        /**
         * Here we add the regEx to the array expressions. Each expression has a
         * corrosponding index with that of the wanted index of the result in
         * the result array
         */
        expressions[0] = Regex.STREETNAME.getRegex();
        expressions[1] = Regex.STREETNUMBER.getRegex();
        expressions[2] = Regex.STREETLETTER.getRegex();
        expressions[3] = Regex.FLOOR.getRegex();
        expressions[4] = Regex.ZIPCODE.getRegex();
        expressions[5] = Regex.CITY.getRegex();

        // go through the expressions
        for (int i = 0; i < expressions.length; i++)    /* 1 */

        {
            // create a pattern with the expression.
            Pattern pattern = Pattern.compile(expressions[i], Pattern.MULTILINE);

            // replace " i " with a " , " for the regEx to divide the different blocks.
            parseMe = parseMe.replace(" i ", " , ");

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
                parseMe = parseMe.replaceFirst(matchedString, "").trim();
                // break out and go on to the next regEx
                break;
            }

        }
        return result;
    }
}
