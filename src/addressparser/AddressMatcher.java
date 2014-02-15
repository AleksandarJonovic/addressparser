package addressparser;

import ENUMS.SpecialChars;
import ExceptionPackage.InvalidInputException;

import java.io.*;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Christian on 12/02/14.
 */
public class AddressMatcher {
    
    private static ArrayList<String> rawAddressList = new ArrayList<>();
    private static ArrayList<String> cleanAddressList = new ArrayList<>();
    
    public AddressMatcher() throws FileNotFoundException {
      try {
      BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("road_names.txt"), "LATIN1"));
      StringBuilder sb = new StringBuilder();
      String line = br.readLine();
       
      while (line != null) {
        rawAddressList.add(line);
        cleanAddressList.add(cleanString(line));
        line = br.readLine();
      }
      br.close();
      
    } catch (Exception e) {
      e.printStackTrace();
    }
//      Collections.sort(cleanAddressList, Collator.getInstance());
//      Collections.sort(rawAddressList, Collator.getInstance());
    }
    
  public static void matchStreetOld(String[] address) {
      long startTime = System.currentTimeMillis(); 
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
    System.out.println("It took " + ((System.currentTimeMillis() - startTime) / 1000.0) + " seconds");
  }
  
  public static void matchStreet(String[] address) {
    long startTime = System.currentTimeMillis();  
    String input = cleanString(address[0]);
    int streetIndex = -1; 
    String rawAdress = null;
      if (cleanAddressList.contains(input)) {
          streetIndex = cleanAddressList.indexOf(input);
          System.out.println("Matched street " + rawAddressList.get(streetIndex));
          System.out.println("It took " + ((System.currentTimeMillis() - startTime) / 1000.0) + " seconds");
      } else {
          System.out.println("No match for street " + input);
          System.out.println("It took " + ((System.currentTimeMillis() - startTime) / 1000.0) + " seconds");
      }

  }
  
  private static String cleanString(String input){
      String cleanString;
      
      String dirtyString = input;
      
      //Special Characters Enum used to extract what to cleanse.
      for(SpecialChars s : SpecialChars.values())
      {
          dirtyString = dirtyString.replaceAll(s.getChar(),"");
      }

      dirtyString = dirtyString.toLowerCase();
      cleanString = dirtyString;
      
      return cleanString; 
  }

  public static void main(String[] args) throws InvalidInputException {
        AddressMatcher am;
        Parser p = new Parser();
        String[] input;
        Scanner scanIn = new Scanner(System.in);
  
        try {
            am = new AddressMatcher();            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AddressMatcher.class.getName()).log(Level.SEVERE, null, ex);
        }
      
      while (true) {
          System.out.print("Enter address: ");
          input = p.parseThis(scanIn.nextLine());
          matchStreet(input);
          System.out.println("");   
      }
  }
}
