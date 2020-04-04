import java.io.IOException;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;
import java.util.Scanner;

/**
 * 
 * @author Mehak Taluja
 *
 */
public class MainMenu {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		Spider spider = new Spider();
		int input = 0;
		String query = "";

		// Displaying Main Menu
		do {
			System.out.println(
					"***********  SEARCH ENGINE MENU  *************" + "\nPress 1. To Download/Update Databases"
							+ "\nPress 2. To Search by Keyword" + "\nPress 3. Search Image by name\nPress 4. Exit");
			try {
				// Getting input
				input = scanner.nextInt();
			} catch (Exception e) {
				scanner.next();
				input = 0;
			}
			switch (input) {
			// Downloading/Updating Database
			case 1:
				System.out.println("Downloading/Updating databases...");

				try {
					long start = System.currentTimeMillis();
					spider.start();
					long elapsed = System.currentTimeMillis() - start;
					System.out.println("Download Successful!");
					System.out.println("Downloading time: " + TimeUnit.MILLISECONDS.toSeconds(elapsed) + " seconds\n");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			// Searching by keyword
			case 2:
				// Checking if database is null
				int count = 0;
				for (int i = 0; i < Spider.urlNames.length; i++) {
					count = SearchQuery.getFilesCount(Spider.urlNames[i]);
				}
				if (count == 0) {
					System.out.println(
							"\nNote: Please download the database for once, to execute search on the database.\n");
					break;
				}

				// Searching Keywords
				else {
					scanner = new Scanner(System.in);
					System.out.println("Enter string to be searched : ");
					// Getting input
					try {
						query = scanner.nextLine();
					} catch (Exception e) {
						scanner.next();
						query = "";
					}

					// Converting input to lower case
					query = query.toLowerCase();

					// Noting initial time
					long start = System.currentTimeMillis();
					// Searching Query in database
					List<Entry<String, Float>> listOfEntries = new SearchQuery().searchQuery(query);
					long elapsed = System.currentTimeMillis() - start;

					if (listOfEntries != null) {
						System.out.println(
								"\n******** Url's containing the above mentioned strings are as follows: ********\n");
						// Traversing and showing the results
						for (int i = 0; i < listOfEntries.size(); i++) {
							System.out.println(listOfEntries.get(i).getKey());
						}
					}

					// Displaying Average time
					System.out.println("\n***********  Time taken to execute searching: " + elapsed
							+ " milliseconds  ***********\n");

					break;
				}
				// Searching Images by keyword
			case 3:
				// Checking if the database is null
				count = 0;
				for (int i = 0; i < Spider.urlNames.length; i++) {
					count = SearchQuery.getFilesCount(Spider.urlNames[i]);
				}
				if (count == 0) {
					System.out.println(
							"\nNote: Please download the database for once, to execute search on the database.\n");
					break;
				}
				// Searching images
				else {
					// Getting input
					scanner = new Scanner(System.in);
					System.out.println("Enter image name to be searched : ");
					// Getting input
					try {
						query = scanner.nextLine();
					} catch (Exception e) {
						scanner.next();
						query = "";
					}

					// Converting input to lower case
					query = query.toLowerCase();

					// Noting initial time
					long start = System.currentTimeMillis();
					// Searching image url in database
					new SearchImage().searchImage(query);
					long elapsed = System.currentTimeMillis() - start;

					// Displaying Average time
					System.out.println("\n***********  Time taken to execute searching: " + elapsed
							+ " milliseconds  ***********\n");
				}
				break;
			// Exiting the code
			case 4:
				System.out.println("Thank you for using our Search Engine!");
				break;
			// Default case
			default:
				System.out.println("Invalid input!");
				break;
			}
			// Not exiting the loop until 4 input
		} while (input != 4);

		scanner.close();
	}

}
