package chris;

import java.util.Scanner;

public class UI {
    private final Scanner s = new Scanner(System.in);
    private final String line = "---------------------------------------";

    public UI() {}
    public void printWelcome() {
        System.out.println(line);
        System.out.println("Hello, I am Chris!");
        System.out.println("What can I do for you?");
        System.out.println(line);
    }

    public void printException(Exception e) {
        System.out.println(line);
        System.out.println(e.getMessage());
        System.out.println(line);
    }

    public void printMessage(String message) {
        System.out.println(line);
        System.out.println(message);
        System.out.println(line);
    }

    public String readInput() {
        return s.nextLine();
    }
}
