import java.io.IOException;
import java.util.Scanner;

import textprocessing.In;

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
					spider.start();
					System.out.println("Download Successful!\n\n");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 2:
				System.out.println("Enter query : ");
				try {
					query=scanner.nextLine();
				} catch (Exception e) {
					scanner.next();
					query = "";
				}
				query= scanner.nextLine();
				Tokenizer token= new Tokenizer();
				
				for(int count=1;count<=212;count++) {
					System.out.println("in for loop");
					In file = new In("WebPages/" + "text/" + spider.urlNames[0] + "" + count + ".txt");
					token.tokenize(file, query);
					System.out.println(count);
				}
				
				
				System.out.println("Searching...");
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

}
