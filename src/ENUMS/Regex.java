/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ENUMS;

/**
 *
 * @author Alex
 */
public enum Regex {
    
    STREETNAME("[\\sA-Øa-ø'-]{1,}"),
    STREETNUMBER("[1-9]\\d{0,2}"),
    STREETLETTER("([a-ø]|[A-Ø])?( |\\s)?"),
    FLOOR("\\d{1,2}[. -]*(sal| {2}|\\.)"),   
    ZIPCODE("\\b[0-9]{4}\\b"),
    CITY("\\D*");
    
 
    Regex(String regex)
    {
        this.regex = regex;
    } 
    
    private final String regex;
    
    public String getRegex(){
        return regex;
    }

}
