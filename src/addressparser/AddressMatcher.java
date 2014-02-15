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
            Collections.sort(rawAddressList);
            Collections.sort(cleanAddressList);

        } catch (Exception e) {
            e.printStackTrace();
        }
//      Collections.sort(cleanAddressList, Collator.getInstance());
//      Collections.sort(rawAddressList, Collator.getInstance());
    }

    /*
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
  
     */

  
  public static void matchStreet(String[] address) {
    long startTime = System.currentTimeMillis();
    String rawInput = address[0];
    String input = cleanString(rawInput);
    int streetIndex = -1; 
      if (cleanAddressList.contains(input)) {
          streetIndex = cleanAddressList.indexOf(input);
          System.out.println("Matched street " + rawAddressList.get(streetIndex));
          System.out.println("It took " + ((System.currentTimeMillis() - startTime) / 1000.0) + " seconds");
      } else {
          System.out.println("No match for street " + rawInput);
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


    private static String checkAddressExist(String input) {
        //System.out.println(cleanAddressList.get(0));
        int indexLow = 0;
        int indexHigh = cleanAddressList.size();
        for (int i = 0; i < input.length(); i++) {
            boolean firstAppereance = true;
            boolean lastAppereance = true;
            for (int j = 0; j < cleanAddressList.size(); j++) {
                String s3 = cleanAddressList.get(j);
                if (s3.length() < i + 1) {
                    continue;
                }
                String s1 = s3.substring(0, i + 1);
                String s2 = input.substring(0, i + 1);
                //System.out.println(s1);
                //System.out.println(s2);
                if (s1.equals(s2) && firstAppereance == true) {
                    firstAppereance = false;
                    indexLow = j;
                }
                int counter = 0;
                do {
                    int charValue = s2.charAt(i);
                    String next = String.valueOf((char) (charValue + 1));
                    s2 = input.substring(0, i) + next;
                    counter++;
                } while (!s1.equals(s2) && counter < 75 && lastAppereance == true);
                if (s1.equals(s2) && lastAppereance == true) {
                    lastAppereance = false;
                    indexHigh = j;
                }
            }
            firstAppereance = true;
            lastAppereance = true;

            if (indexHigh - indexLow <= 5) {
                for (int k = indexHigh; k >= indexLow; k--) {
                    if (input.length() >= cleanAddressList.get(k).length()) {
                        if (cleanAddressList.get(k).equals(input.substring(0, cleanAddressList.get(k).length()))) {
                            System.out.println("Bedste match: " + cleanAddressList.get(k));
                            return cleanAddressList.get(k);
                        }
                    }
                    System.out.println("Muligt match: " + cleanAddressList.get(k));
                }
                System.out.println("fandt ikke præcist match men 5 eller mindre elementer passer");
                return null;
            }
        }
        System.out.println("Fandt ikke et match");
        return null;
    }

    public static void main(String[] args) throws InvalidInputException {
        AddressMatcher am;
        Parser p = new Parser();
        Addressparser ap = new Addressparser();
        String[] input;
        Scanner scanIn = new Scanner(System.in);

        try {
            am = new AddressMatcher();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AddressMatcher.class.getName()).log(Level.SEVERE, null, ex);
        }

        while (true) {
            System.out.print("Enter address: ");
            String s = scanIn.nextLine();

            input = p.parseThis(s);
            matchStreet(input);
            System.out.println("");

            s = cleanString(s);
            // anders metode
            String streetName = checkAddressExist(s);
            if (streetName != null) {
                for (int i = 0; i < streetName.length() ; i ++) {
                    s = s.replaceFirst(streetName.substring(i,i+1), "");
                }
            }
            System.out.println("test " + s);
        }
    }
    
    private String print()
    {
        return "";
    }
}
