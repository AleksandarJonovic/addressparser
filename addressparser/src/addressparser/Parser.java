/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package addressparser;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Parser {

    String[] result = new String[6];
    String[] expressions = new String[5];

    public String[] parseThis(String parseMe)
    {

        //debug
        System.out.println(parseMe);

        expressions[0] = "[\\sA-Øa-ø'-]{1,}";
        expressions[1] = "[1-9]\\d{0,2}[\\-\\s]?[a-z]?";
        expressions[2] = "(\\d{2}|\\d)[. -]*(sal| {2}|\\.)";
        expressions[3] = "\\b[0-9]{4}\\b";
        expressions[4] = "\\D*";

        for (int i = 0; i < expressions.length; i++)
        {

            Pattern pattern = Pattern.compile(expressions[i], Pattern.MULTILINE);

            parseMe = parseMe.replace(" i ", " , ");

            Matcher matcher = pattern.matcher(parseMe);

            while (matcher.find())
            {

                String matchedString = parseMe.substring(matcher.start(), matcher.end());

                result[i] = matchedString.trim().replace(",", "");

                parseMe = parseMe.replace(matchedString, "");
                if (i == 1) // road
                {
                    splitRoad(matchedString);
                }
                break;
            }

        }

        return result;
    }

    /**
     * mega grim kode, men den skiller vejnavn og nummer fra hinanden. + den
     * placerer vejnummer-bogstavet til sidst i resultsettet.
     *
     * @param roadNameNumber
     */
    private void splitRoad(String roadNameNumber)
    {
        Pattern pattern = Pattern.compile("\\d{1,}", Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(roadNameNumber);
        while (matcher.find())
        {
            String matchedString = roadNameNumber.substring(matcher.start(), matcher.end());

            result[1] = matchedString.trim();
        }
        pattern = Pattern.compile("\\D", Pattern.MULTILINE);
        matcher = pattern.matcher(roadNameNumber);
        while (matcher.find())
        {
            String matchedString = roadNameNumber.substring(matcher.start(), matcher.end());

            result[5] = matchedString.trim();
        }
    }
}
