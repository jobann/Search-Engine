import java.io.IOException;

/**
 * 
 * @author Lovejot Singh
 *
 */
public class Spider {

	static String[] urls = new String[] { "https://www.javatpoint.com/java-tutorial", "https://www.w3schools.com" };
	static String[] urlNames = new String[] { "JavaTPoint", "w3schools" };

	// Initializing Crawler with different Urls
	void start() throws IOException {
		for (int i = 0; i < urls.length; i++) {
			Crawler crawler = new Crawler(urls[i], urlNames[i]);
			crawler.startCrawling();
		}
	}

}
