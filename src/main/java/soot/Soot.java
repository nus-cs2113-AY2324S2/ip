package soot;

import soot.parser.Parser;
import soot.task.TaskList;
import soot.ui.UserUi;
import soot.storage.Storage;

import java.util.Scanner;

public class Soot {
    private final Storage storage;
    private final TaskList tasks;
    private static Scanner scanner;
    public static boolean hasEnd;

    /**
     * Constructor for chatbot.
     * Boolean variable hasEnd is initialised to false as no user input has been scanned.
     */
    public Soot() {
        scanner = new Scanner(System.in);
        this.tasks = new TaskList();
        this.storage = new Storage(tasks);
        hasEnd = false;
    }

    /**
     * Run chatbot to read user input and parse the command.
     * Chatbot stops running when hasEnd is true, indicating user has input bye.
     */
    public static void run() {
        while (!hasEnd) {
            String line = scanner.nextLine();
            UserUi.displayDividerLine();
            Parser.parseCommand(line);
        }
    }

    public static void main(String[] args) {
        UserUi.showUserGreeting();
        Soot chatbot = new Soot();
        chatbot.run();
    }
}
