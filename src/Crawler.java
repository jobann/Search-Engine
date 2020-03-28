import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Crawler {

	String url, urlName;

	public Crawler(String url, String urlName) {
		this.url = url;
		this.urlName = urlName;
	}

	void startCrawling() throws IOException {
		crawl(this.url);
	}

	private void crawl(String url) throws IOException {
		Document doc = Jsoup.parse(getHtml(url));

		Elements elements = doc.select("a");

		int count = 0;
		for (Element element : elements) {
			String href = element.attr("href");
			href = fixUrl(href, url);
			writeToFiles(href, ++count);
		}
	}

	// Getting Html text from URL
	private String getHtml(String urls) throws IOException {
		URL url = new URL(urls);
		URLConnection connection = url.openConnection();
		connection.setRequestProperty("User-Agent", "BBot/1.0");
		connection.setRequestProperty("Accept-Charset", "UTF-8");
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String inputLine, finalContents = "";

		// getting content line by line from Buffer Reader
		while ((inputLine = reader.readLine()) != null) {
			finalContents += "\n" + inputLine.replace("<br>", "\n<br>");
		}

		return finalContents;

	}

	// fixing link
	private String fixUrl(String href, String url) {

		try {
			URL link = new URL(url);
			if (href.startsWith("./")) {
				href = href.substring(2, href.length());
				href = link.getProtocol() + "://" + link.getAuthority() + trimPath(link.getPath()) + href;
			} else if (href.startsWith("#")) {
				href = url + href;
			} else if (href.startsWith("javascript:")) {
				href = null;
			} else if (href.startsWith("../") || (!href.startsWith("http://") && !href.startsWith("https://"))) {
				href = link.getProtocol() + "://" + link.getAuthority() + trimPath(link.getPath()) + href;
			}
			return href;

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return null;
		}

	}

	// Triming path from Url
	private String trimPath(String path) {
		int pos = path.lastIndexOf("/");
		return pos <= -1 ? path : path.substring(0, pos + 1);
	}

	// writing to files
	private void writeToFiles(String link, int count) throws IOException {

		try {
			System.out.println(link);
			Document doc = Jsoup.connect(link).get();

			String text = doc.text();
			File file1 = new File("WebPages/" + "text/" + urlName + "" + count + ".txt");
			file1.createNewFile();
			PrintWriter writer = new PrintWriter(file1);
			writer.println(link);
			writer.println(text);
			writer.close();


		} catch (Exception e) {
			// e.printStackTrace();
		}
	}
}
