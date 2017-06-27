import java.util.Scanner;

public class HelloDave{
	private static Scanner sc = new Scanner(System.in);
 
	public static final void main(String[] args) {
		System.out.println("Howdy!");
		System.out.println("What is your name?");
		String yourName = sc.nextLine();
		System.out.println("Hello, " + yourName );		
	}
}