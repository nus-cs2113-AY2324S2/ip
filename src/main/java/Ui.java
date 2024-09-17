import java.util.Scanner;

/**
 * Deals with actions related to the command-line interface and terminal outputs for this project.
 */
public class Ui {
    protected static boolean stillGoing = true;
    private Parser parser;
    private final String LOGO = "╱╭╮╱╱╱╱╭╮╱╭╮\n" +
            "╭╯╰╮╱╱╱┃┃╱┃┃\n" +
            "╰╮╭╋┳━━┫┃╭┫┃╭━━┳━━╮\n" +
            "╱┃┃┣┫╭━┫╰╯┫┃┃┃━┫━━┫\n" +
            "╱┃╰┫┃╰━┫╭╮┫╰┫┃━╋━━┃\n" +
            "╱╰━┻┻━━┻╯╰┻━┻━━┻━━╯\n";

    public Ui(Storage storage) {
        parser = new Parser(this, storage);
    }

    /**
     * Displays tickles logo and prompts the user for input.
     */
    public void displayOpening() {
        System.out.println("Hello from\n" + LOGO);
        System.out.println(" What can I do for you?");
    }

    /**
     * Prompts user for input, and handles the special input cases "bye", "unmark"/"mark", "list".
     */
    public void promptUser() {
        while (stillGoing) {
            Scanner in = new Scanner(System.in);
            String prompt = in.nextLine();
            parser.parse(prompt);
        }
    }

    public void terminate() {
        stillGoing = false;
    }

    /**
     * Abstracts out the printing with line breaks.
     */
    public static void printThis(String str) {
        printLine();
        System.out.println(str);
        printLine();
    }

    public static void printLine() {
        System.out.println("_____________________________________________________________");
    }

}

