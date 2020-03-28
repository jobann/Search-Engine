import java.io.IOException;

public class Spider {

	 String[] urls = new String[] { "https://www.javatpoint.com/java-tutorial" };
	 String[] urlNames = new String[] { "JavaTPoint" };

	void start() throws IOException {
		Crawler crawler = new Crawler(urls[0], urlNames[0]);
		crawler.startCrawling();
	}


}
