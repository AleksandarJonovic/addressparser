/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package addressparser;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Parser {

    public String[] parseThis(String parseMe) {

        String[] result = new String[6];
        String[] expressions = new String[6];

        expressions[0] = "";
        expressions[1] = "";
        expressions[2] = "";
        expressions[3] = "";
        expressions[4] = "\b[0-9][0-9]{3}\b";
        expressions[5] = "";

        for (int i = 0; i < expressions.length; i++) {

            Pattern pattern = Pattern.compile(expressions[i]);

            Matcher matcher = pattern.matcher(parseMe);

            if (matcher.matches()) {
                String matchedString = parseMe.substring(matcher.start(), matcher.end());

                result[i] = matchedString;
            }

        }
        return result;
    }
}
