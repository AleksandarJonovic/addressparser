package addressparser;

import ExceptionPackage.InvalidInputException;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Christian on 12/02/14.
 */
public class AddressMatcher {
    
    private static final ArrayList<String> rawAddressList = new ArrayList<>();
    private static final ArrayList<String> cleanAddressList = new ArrayList<>();
    
    public AddressMatcher() throws FileNotFoundException {
      try {
      BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("road_names.txt"), "LATIN1"));
      StringBuilder sb = new StringBuilder();
      String line = br.readLine();
       
      int i = 0;
      while (line != null) {
        rawAddressList.add(line);
        cleanAddressList.add(cleanString(line));
        line = br.readLine();
      }
      br.close();

    } catch (Exception e) {
      e.printStackTrace();
    }
    }

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
        try {
            AddressMatcher am = new AddressMatcher();
//    Parser p = new Parser();
//    try {
//        System.out.println(AddressMatcher.cleanString("A. P. Møller"));
//      AddressMatcher.matchStreet(p.parseThis(AddressMatcher.cleanString("A. P. Møller")));
//        
//    } catch (InvalidInputException e) {
//      e.getMessage();
//    }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AddressMatcher.class.getName()).log(Level.SEVERE, null, ex);
        }
  }
}
