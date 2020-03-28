import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;
import java.util.Scanner;
import java.util.TreeMap;

public class MainMenu {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		Spider spider = new Spider();
		int input = 0;
		String query = "";

		do {
			System.out.println("-----------------Main Menu-----------------" + "\n1. Download Databases"
					+ "\n2. Search by Keyword" + "\n3. Exit");
			try {
				input = scanner.nextInt();
			} catch (Exception e) {
				scanner.next();
				input = 0;
			}
			switch (input) {
			case 1:
				System.out.println("Downloading databases...");

				try {
					long start = System.currentTimeMillis();
					spider.start();
					long elapsed = System.currentTimeMillis() - start;
					System.out.println("Download Successful!\n\n");
					System.out.println("CPU time: " + TimeUnit.MILLISECONDS.toSeconds(elapsed) + " seconds");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 2:
				System.out.println("Enter query : ");

				try {

					query = scanner.nextLine();

				} catch (Exception e) {

					scanner.next();

					query = "";

				}

				query = scanner.nextLine();
				query = query.toLowerCase();

				Tokenizer tokenizer = new Tokenizer();
				TreeMap<Integer, String> resultMap = new TreeMap<Integer, String>(Collections.reverseOrder());

				for (int i = 1; i <= getFilesCount("JavaTPoint"); i++) {

					try {
						File file = new File("WebPages/" + "text/" + spider.urlNames[0] + "" + i + ".txt");
						BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
						String url = bufferedReader.readLine();

						Integer count = tokenizer.tokenize(bufferedReader).get(query);
						if (count != null) {
							resultMap.put(count, url);
						}

						bufferedReader.close();
					} catch (IOException e) {
						System.err.println("Message: " + e.getMessage());
					}
				}

				// Traversing Map and showing results
				Iterator<Entry<Integer, String>> iterator = resultMap.entrySet().iterator();
				while (iterator.hasNext()) {
					System.out.println(((Map.Entry<Integer, String>) iterator.next()).getValue());
				}

				break;
			case 3:
				System.out.println("Bye!");
				break;
			default:
				System.out.println("Invalid input!");
				break;
			}

		} while (input != 3);

		scanner.close();
	}

	// get number of files in directory
	static int getFilesCount(String fileName) {
		int count = 0;

		String[] files = new File("WebPages/text/").list();

		for (String file : files) {
			if (file.toLowerCase().contains(fileName.toLowerCase())) {
				count++;
			}
		}

		return count;
	}

}
