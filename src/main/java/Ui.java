import java.util.Scanner;

public class Ui {
    protected static boolean stillGoing = true;
    private Parser parser;

    public Ui(Storage storage) {
        parser = new Parser(this, storage);
    }

    // Prompts user for input, and handles the special input cases "bye", "unmark"/"mark", "list".
    public void promptUser() {
        while (stillGoing) {
            Scanner in = new Scanner(System.in);
            String prompt = in.nextLine();
            parser.parse(prompt);
        }
    }

    public void showLoadingError() throws StorageException {
        throw new StorageException();
    }

    public void terminate() {
        stillGoing = false;
    }

    // Abstracts out the printing with line breaks.
    public static void printThis(String str) {
        printLine();
        System.out.println(str);
        printLine();
    }

    public static void printLine() {
        System.out.println("_____________________________________________________________");
    }

}

