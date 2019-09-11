import java.util.Scanner;

public class User {
	
	public static String insereInput() {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		return scanner.next();
	}
	
}
