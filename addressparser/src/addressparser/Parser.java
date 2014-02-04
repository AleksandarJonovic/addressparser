/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package addressparser;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Parser {

    String[] result = new String[6];
    String[] expressions = new String[6];

    public String[] parseThis(String parseMe)
    {

        //debug
        //System.out.println(parseMe);

        expressions[0] = "[\\sA-Øa-ø'-]{1,}";
        expressions[1] = "[1-9]\\d{0,2}";
        expressions[2] = "([a-z]|[A-Z])?( |\\s)?";
        expressions[3] = "\\d{1,2}[. -]*(sal| {2}|\\.)";
        expressions[4] = "\\b[0-9]{4}\\b";
        expressions[5] = "\\D*";

        //System.out.println("Printing the parseMe string");
        //System.out.println("- - - -");
        for (int i = 0; i < expressions.length; i++)
        {
            Pattern pattern = Pattern.compile(expressions[i], Pattern.MULTILINE);

            parseMe = parseMe.replace(" i ", " , ");

            Matcher matcher = pattern.matcher(parseMe);

            while (matcher.find())
            {

                String matchedString = parseMe.substring(matcher.start(), matcher.end());
                matchedString = matchedString.replace(",", "").trim();
                if (!matchedString.equals(""))
                    result[i] = matchedString;
                else
                    result[i] = null;

                parseMe = parseMe.replaceFirst(matchedString, "").trim();
                //System.out.println(parseMe);
                break;
            }

        }
        //System.out.println("....");

        return result;
    }
}
