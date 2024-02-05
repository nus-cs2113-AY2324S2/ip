import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class RecrBad {
    /**
     * Prints "*" as line-separator
     */
    private static void printLine() {
        int CHARS_IN_LINE = 40;
        for (int i = 0; i < CHARS_IN_LINE; i++) {
            System.out.print("*");
        }
        System.out.println();
    }

    /**
     * Prints introductory message
     */
    private static void sayHi() {
        String logo = " ____   ___    ___   ____    ___   \n"
                + "| __/  / _ \\  | _ \\  | __|  / _ \\   \n"
                + "| |    ||_||  | / /  | |_   | | |      \n"
                + "| |_   | _ |  | _/   |_  /  | | |      \n"
                + "|___\\  // \\\\  |_|    /__/ . \\___/  \n";

        System.out.println("Hello! I'm \n" + logo);
        printLine();
        System.out.println("What can I do for you?");
    }

    /**
     * Prints ending message
     */
    private static void sayBye() {
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

        System.out.println("Adios My Friend. Sleep early, study smarter \n" + quotes[randNum]);
        printLine();
    }

    /**
     * Adds new Task to Tasks array
     *
     * @param tasks existing Tasks array
     * @param line string input from user
     * @return moreTasks array with added Task
     */
    private static Task[] addToList(Task[] tasks, String line) {
        // copies and returns longer String[tasks.length+1]
        Task[] moreTasks = Arrays.copyOf(tasks, tasks.length + 1);
        moreTasks[tasks.length] = new Task(line); //append at last elem

        printLine();
        System.out.println("You added: " + line);
        return moreTasks;
    }

    /**
     * Prints each Task in Task array
     *
     * @param tasks Existing Task array
     */
    private static void displayList(Task[] tasks) {
        int count = 1;
        for (Task line : tasks) {
            System.out.println(count + ".[" + line.getStatus() + "] " + line.description);
            count += 1;
        }
    }

    /**
     * Prints a specific task in Tasks array
     *
     * @param tasks existing Task array
     * @param index index of specific task
     */
    private static void displayListItem(Task[] tasks, int index) {
        System.out.println("[" + tasks[index].getStatus() + "] " + tasks[index].description);
    }

    /**
     * Marks Task as done or not done
     *
     * @param tasks existing Tasks array
     * @param line  String input from user
     * @param isMark type of operation: mark or unmark
     */
    private static void markOperation(Task[] tasks, String line, boolean isMark) {
        String[] req = line.split(" ");
        if (req.length > 1) {
            int taskNum = Integer.parseInt(req[1]);
            if (tasks.length > taskNum - 1) {
                // mark #taskNum as done
                if (isMark) {
                    tasks[taskNum - 1].markAsDone();
                    System.out.println("Okie dokie, marked task below:");
                } else {
                    tasks[taskNum - 1].markAsNotDone();
                    System.out.println("Okie dokie, unmarked task below:");
                }
                displayListItem(tasks, taskNum - 1);
            } else {
                System.out.println("No such taskNum");
            }
        } else {
            System.out.println("invalid mark/ unmark operation");
        }
    }

    /**
     * Asks user for input to addToList or displayList or markOperation
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        sayHi();
        Task[] tasks = new Task[]{};

        while (true) {
            printLine();
            String line = in.nextLine(); // reads input

            if (line.equalsIgnoreCase("BYE")) {
                sayBye(); // Ctrl B to see def, shift F10 to run, Ctrl Alt L reformat
                break;
            }
            if (line.equalsIgnoreCase("LIST")) {
                displayList(tasks);
                continue;
            }
            if (line.toUpperCase().contains("MARK")) { // both unmark & mark contains 'mark'
                boolean isMark = !line.toUpperCase().contains("UNMARK");
                markOperation(tasks, line, isMark);
                continue;
            }

            tasks = addToList(tasks, line);
        }
    }
}
