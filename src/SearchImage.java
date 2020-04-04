import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 
 * @author Aashka Prajapati
 *
 */
public class SearchImage {

	// Searching image url in the database
	/**
	 * @param query name of file to search
	 */
	void searchImage(String query) {
		// loop to check every url
		for (int i = 0; i < Spider.urls.length; i++) {
			String url = Spider.urls[i];

			// Pattern to get Source path of url
			String pattern = ".com/.*";
			// Create a Pattern object
			Pattern r = Pattern.compile(pattern);

			try {
				// getting html in text format
				Document doc = Jsoup.parse(getHtml(url));
				Elements elements = doc.select("img");

				for (Element element : elements) {
					String img = element.attr("data-src");
					img = fixUrl(img, url);
					// matcher object.
					Matcher m = r.matcher(img);
					if (m.find()) {
						String urlSource = img.substring(m.start());
						if (urlSource.contains(query)) {
							System.out.println(img);
						}
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	// Getting Html text from URL
	private static String getHtml(String urls) throws IOException {
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
	private static String fixUrl(String href, String url) {

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
	static String trimPath(String path) {
		int pos = path.lastIndexOf("/");
		return pos <= -1 ? path : path.substring(0, pos + 1);
	}

}