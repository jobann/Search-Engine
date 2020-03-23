package phoneNumbers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyPhoneNumber
{
    public static void main( String args[] ){

      // String to be scanned to find the pattern.
      String line = "My work phone number is 519-253-3000. My cel phone number is +1-226-347-1134."
      		+ " You can also find my number as follows: (519) 253-3000";
      //String pattern = "(\\d){3}-(\\d){3}-(\\d){4}";
      String pattern = "(\\()?(\\d){3}(\\))?[- ](\\d){3}-(\\d){4}";

      // Create a Pattern object
      Pattern r = Pattern.compile(pattern);

      // Now create matcher object.
      Matcher m = r.matcher(line);
      while (m.find( )) {
          System.out.println("Found value: " + m.group(0) + " at " + m.start(0));
      } 
   }
}