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
        String[] expressions = new String[5];
        //debug
        System.out.println(parseMe);
        
        expressions[0] = "[\\sA-Øa-ø'-]{1,}";
        expressions[1] = "[1-9]\\d{0,2}[\\-\\s]?[a-z]?";
        expressions[2] = "(\\d{2}|\\d)[. -]*(sal| {2}|\\.)";
        expressions[3] = "\\b[0-9]{4}\\b";
        expressions[4] = "\\D*";

        for (int i = 0; i < expressions.length; i++) {

            Pattern pattern = Pattern.compile(expressions[i],Pattern.MULTILINE);

            parseMe = parseMe.replace(" i ", " , ");
            
            Matcher matcher = pattern.matcher(parseMe);

            
            while (matcher.find()) {
               
                String matchedString = parseMe.substring(matcher.start(), matcher.end());

                result[i] = matchedString.trim().replace(",", "");
                
                parseMe = parseMe.replace(matchedString, "");
                break;
            }

        }
        
        return result;
    }
}
