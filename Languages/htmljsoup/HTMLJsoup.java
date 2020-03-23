package htmljsoup;


import java.io.*;

import org.jsoup.*;

public class HTMLJsoup {

	public static void main(String[] args) throws IOException {
		org.jsoup.nodes.Document doc = Jsoup.connect("http://luisrueda.cs.uwindsor.ca/researchint/transcriptomics").get();
		//org.jsoup.nodes.Document doc = Jsoup.connect("http://blogs.windsorstar.com/news/woman-to-be-charged-with-child-abandonment-after-infants-found-in-apartment-stairwell").get();
		String text = doc.text();
		System.out.println(text);
		PrintWriter out = new PrintWriter("jsoupText.txt");
		out.println(text);
		out.close();
		String html = doc.html();
		out = new PrintWriter("jsoupHTML.html");
		out.println(html);
		out.close();
    	String program = "C:/Program Files (x86)/Google/Chrome/Application/chrome.exe jsoupHTML.html";
    	Process p = Runtime.getRuntime().exec(program);
	}
}
