import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		String RESET = "\u001B[0m";
		String RED = "\u001B[31m";
		String GREEN = "\u001B[32m";
		String YELLOW = "\u001B[33m";
		System.out.println("Greetings! To access the system, please choose your role.");
		System.out.println("If you are an Admin, type Admin.");
		System.out.println("If you are a Teacher, type Teacher.");
		System.out.print("Enter your role: ");

		Scanner scanner = new Scanner(System.in);

		while (true) {
			String role = scanner.nextLine();
			if ("Admin".equalsIgnoreCase(role)) {
				System.out.print("\033[H\033[2J");
				System.out.flush();
				System.out.println(YELLOW + "Welcome Admin!" + RESET);
				break;
			} else if ("Teacher".equalsIgnoreCase(role)) {
				System.out.print("\033[H\033[2J");
				System.out.flush();
				System.out.println(GREEN + "Welcome Teacher!" + RESET);
				break;
			}
			else {
				System.out.print("\033[H\033[2J");
				System.out.flush();
				System.out.println(RED + "Invalid Role, Please Try Again." + RESET);
				System.out.print("Enter your role: ");
			}
		}
	}
}