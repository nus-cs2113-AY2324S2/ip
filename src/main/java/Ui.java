import java.util.Scanner;

public class Ui {
    public static final String LINE_DIVIDER = "------------------------------------------";
    public static final String OUTPUT_INDENTATION = "    ";
    private Scanner input = new Scanner(System.in);

    public void printStartingMessage() {
        System.out.println(LINE_DIVIDER);
        System.out.println("Hello! I'm Chandler. Your personal sarcastic task manager.");
        System.out.println("So, what can you do for me? :)");
        System.out.println(LINE_DIVIDER);
    }

    public void printEndingMessage() {
        System.out.println(LINE_DIVIDER);
        System.out.println("Bye. Meeting you was okay..");
        System.out.println(LINE_DIVIDER);
    }

    public String inputPrompt() {
        return input.nextLine();
    }
    public void closeScanner() {
        input.close();
    }
}

