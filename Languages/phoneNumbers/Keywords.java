package phoneNumbers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Keywords
{
    public static void main( String args[] ){

      // String to be scanned to find the pattern.
      String line = "micro, microarray, micro  arrays, microarrays, micro-chips, array, arrayss";
      String pattern = "(micro)?( |-){0,2}(array|chip)(s){0,2}";
      //  String line = " this is a web link http://www.io.com/folder#ref with a folder";
      //String pattern = "([\\w-]+://?|www[.])[^\\s()<>]+";

      // Create a Pattern object
      Pattern r = Pattern.compile(pattern);

      // Now create matcher object.
      Matcher m = r.matcher(line);
      while (m.find( )) {
          System.out.println("Found value: " + m.group(0) + " at " + m.start(0) );
      } 
   }
}