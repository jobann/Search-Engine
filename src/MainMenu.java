import java.util.Scanner;

public class MainMenu {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		int input = 0;

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
				break;
			case 2:
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
