/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package addressparser;

/**
 *
 * @author Alex
 */
public enum SpecialChars {
    
    SPACE(" "),
    DOT("\\."),
    COMMA(","),
    DASH("-"),
    UNDERSCORE("_"),
    APOSTROPHE("'"),
    APOSTROPHETWO("´"),
    DIGITS("\\d");

    
 
    SpecialChars(String chars)
    {
        this.chars = chars;
    } 
    
    private final String chars;
    
    public String getChar(){
        return chars;
    }

}
