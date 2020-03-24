import java.io.IOException;

public class Spider {

	static String[] urls = new String[] { "https://www.javatpoint.com/java-tutorial" };
	static String[] urlNames = new String[] { "JavaTPoint" };

	void start() throws IOException {
		Crawler crawler = new Crawler(urls[0], urlNames[0]);
		crawler.startCrawling();
	}


}
