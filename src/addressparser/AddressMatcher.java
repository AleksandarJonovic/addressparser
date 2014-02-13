package addressparser;

import ExceptionPackage.InvalidInputException;

import java.io.*;

/**
 * Created by Christian on 12/02/14.
 */
public class AddressMatcher {

  public static void matchStreet(String[] address) {
    String match = "";
    String input = address[0].toLowerCase();
    try {
      BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("road_names.txt"), "LATIN1"));
      StringBuilder sb = new StringBuilder();
      String line = br.readLine();

      while (line != null) {
        match = line;
        line = line.replaceAll(" ", "").toLowerCase();
        if (line.contains(input.replaceAll(" ", ""))) {
          sb.append(match);
          sb.append(System.lineSeparator());
        }
        line = br.readLine();
      }
      match = sb.toString();
      br.close();

    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println("Input address: " + input);
    System.out.println("Matched address(es): " + match);
  }
  
  private static String cleanString(String input){
      String cleanString;
      
      String dirtyString = input;
      
      dirtyString = dirtyString.replaceAll(" ", "");
      dirtyString = dirtyString.replaceAll("\\.", "");
      dirtyString = dirtyString.replaceAll(",", "");
      dirtyString = dirtyString.replaceAll("-", "");
      dirtyString = dirtyString.replaceAll("'", "");
      dirtyString = dirtyString.replaceAll("´", "");
      dirtyString = dirtyString.replaceAll("\\d", "");
      dirtyString = dirtyString.toLowerCase();

      
      cleanString = dirtyString;
      
      return cleanString; 
  }

  public static void main(String[] args) {
    Parser p = new Parser();
    try {
        System.out.println(AddressMatcher.cleanString("A. P. Møller"));
      AddressMatcher.matchStreet(p.parseThis(AddressMatcher.cleanString("A. P. Møller")));
        
    } catch (InvalidInputException e) {
      e.getMessage();
    }
  }
}
