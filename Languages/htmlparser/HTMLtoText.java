package htmlparser;

import java.io.*;
import javax.swing.text.html.*;
import javax.swing.text.html.parser.*;
import java.net.*;

public class HTMLtoText extends HTMLEditorKit.ParserCallback {
 StringBuffer s;

 public HTMLtoText() {}

 public void parse(Reader in) throws IOException {
   s = new StringBuffer();
   ParserDelegator delegator = new ParserDelegator();
   // the third parameter is TRUE to ignore charset directive
   delegator.parse(in, this, Boolean.TRUE);
 }

 public void handleText(char[] text, int pos) {
   s.append(text);
 }

 public String getText() {
   return s.toString();
 }

 public static void main (String[] args) {
   try {
     // the HTML to convert
	//URL  url = new URL("http://www.windsorstar.com/story.html?id=10392258");
	//URL  url = new URL("http://blogs.windsorstar.com/entertainment/agw-wins-three-major-awards");
	URL  url = new URL("http://blogs.windsorstar.com/news/woman-to-be-charged-with-child-abandonment-after-infants-found-in-apartment-stairwell");
	   
    URLConnection conn = url.openConnection();
    BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
    String inputLine;
    String finalContents = "";
    while ((inputLine = reader.readLine()) != null) {
     finalContents += "\n" + inputLine.replace("<br", "\n<br");
    }
    BufferedWriter writer = new BufferedWriter(new FileWriter("testHtml.html"));
    writer.write(finalContents);
    writer.close();
    System.out.println(finalContents);

     FileReader in = new FileReader("testHtml.html");
     HTMLtoText parser = new HTMLtoText();
     parser.parse(in);
     in.close();
     String textHTML = parser.getText();
     System.out.println(textHTML);
     
     // Write the text to a file  
     BufferedWriter writerTxt = new BufferedWriter(new FileWriter("testHtml.txt"));
     writerTxt.write(textHTML);
     writerTxt.close();

   }
   catch (Exception e) {
     e.printStackTrace();
   }
 }
}