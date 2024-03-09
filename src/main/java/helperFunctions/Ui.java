package helperFunctions;

import java.util.Random;
import java.util.Scanner;

public class Ui {

    /**
     * Reads user input and process accordingly, infinitely till sayBye()
     *
     * @param tasks TaskList object of tasks
     * @param FILE_NAME to store tasks in
     */
    public static void readInput(TaskList tasks, String FILE_NAME) {
        Scanner in = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            printLine();
            String userInput = in.nextLine(); // reads input
            // process input
            try {
                boolean isReadMode = false;
                isRunning = Parser.processUserInput(tasks, userInput, FILE_NAME, isReadMode);
            } catch (InvalidParamsException e) {
                showLoadingError(e.getMessage()); // prints out error message
            }
        }
        sayBye();
    }

    /**
     * Prints errorMessage
     *
     * @param errorMessage if got error
     */
    public static void showLoadingError(String errorMessage) {
        System.out.println(errorMessage);
    }

    /**
     * Returns list of possible commands
     *
     * @return list of possible commands
     */
    public static String printCommandsList() {
        return "## Possible user commands: \n" +
                "1. todo <task>                            : Add todo task \n" +
                "2. deadline <task> /<deadline>            : Add deadline\n" +
                "3. event <task> /<startTime> /<endTime>   : Add event \n" +
                "4. list                                   : List all tasks\n" +
                "5. mark/unmark <taskIndex>                : Mark task <index> as done/ undone\n" +
                "6. find <keyword>                         : Finds keyword in list of tasks\n" +
                "7. bye                                    : Exit Program";
    }

    /**
     * Prints "*" as line-separator
     */
    public static void printLine() {
        final int CHARS_IN_LINE = 40; // an estimate
        for (int i = 0; i < CHARS_IN_LINE; i++) {
            System.out.print("*");
        }
        System.out.println();
    }

    /**
     * Prints introductory message
     */
    public static void sayHi() {
        String logo = " ____   ___    ___   ____    ___\n"
                + "| __/  / _ \\  | _ \\  | __|  / _ \\\n"
                + "| |    ||_||  | / /  | |_   | | |\n"
                + "| |_   | _ |  | _/   |_  /  | | |\n"
                + "|___\\  // \\\\  |_|    /__/ . \\___/\n";

        System.out.println("Hello! I'm\n" + logo);
        printLine();
        System.out.println("What can I do for you?");
    }

    /**
     * Prints ending message and a random quote
     */
    public static void sayBye() {
        // generating random quote
        Random rand = new Random(); // create instance of class Random
        // quotes inspired by https://wisdomquotes.com/short-quotes/, https://wisdomquotes.com/positive-quotes/
        String[] quotes = {
                "2230 | Time Block",
                "The best investment is in yourself",
                "Never regret anything that made you smile ~Mark Twain",
                "You are the main character in your life",
                "Life is like riding a bicycle. To keep your balance, you must keep moving ~Albert Einstein",
                "Stay hungry, Stay Foolish ~Steve Jobs",
                "You only live once, but if you do it right, once is enough ~Mae West",
                "Choose one thing and be the best in it",
                "Don't tell people your plans. Show them your results",
                "Good things happen to those who hustle ~Anais Nin",
                "If you want it, work for it",
                "If it matters to you, you'll find a way ~Charlie Gilkey",
                "If you're going through hell, keep going ~Winston Churchill",
                "Small steps motivate. Big steps overwhelm ~Maxime Lagace",
                "The difference btwn a good and bad day is your ATTITUDE! ~a magnet",
                "The less you respond to negative people, the more positive your life will become ~Paulo Coelho",
                "Always know your goals in life",
                "Often, the problem is not the lack of time but lack of direction"
        };
        int upperbound = quotes.length;
        int randNum = rand.nextInt(upperbound);

        System.out.println("Adios My Friend. Sleep early, study smarter\n" + quotes[randNum]);
        printLine();
    }
}
