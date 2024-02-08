import java.util.Scanner;

public class Kratos {
    public final static int MAX_TASKS = 100;
    static Task[] tasksList = new Task[MAX_TASKS];

    public static int count = 0;

    // Method to greet the user
    public static void greet() {
        String logo = "──────────────────────────────\n" +
                "───────────────────────▓▓▓▓───\n" +
                "──────────────────────▓▓▓▓▓▓──\n" +
                "─────────────────────▓▓▓▓▓▓▓▓─\n" +
                "────────────────────▓▓▓▓▓▓▓▓──\n" +
                "───────────────────▓▓▓▓▓▓▓▓───\n" +
                "──────────────────▓▓▓▓▓▓▓▓────\n" +
                "─────────────────▓▓▓▓▓▓▓▓─────\n" +
                "─────────█──────▓▓▓▓▓▓▓▓──────\n" +
                "────────██─────▓▓▓▓▓▓▓▓───────\n" +
                "───────██──────▓▓▓▓▓▓▓▓▓──────\n" +
                "─▀██▄─██───────▓▓▓▓▓▓▓▓██──▄█─\n" +
                "─█████████▄─▄───▓▄▓▄████████──\n" +
                "──█▀▀▀███████────███████▀▀▀██─\n" +
                "─███▄▄██▄███▀─────███▄██▄▄███─\n" +
                "─█─██████▀█────────▓████████▀─\n" +
                "────██▀───▀─█────█──▓▓▓▓▓▓▓▓──\n" +
                "────▄█▀──────█──█────▓▓▓▓▓▓▓▓─\n" +
                "───▀█────────█──█─────▓▓▓▓▓▓▓─\n" +
                "───▄█▀────▄▄█▀──▀█▄▄───▓▓▓▓▓▓─\n" +
                "───█▄────█──█────█──█──▓▓▓▓▓▓─\n" +
                "───█─────▀──────────▀──▓▓▓▓▓▓─\n" +
                "────▀─────▀█──────█▀────▓▓▓───\n" +
                "────────────▀▄▄▄▄▀──────▓▓────\n" +
                "────────────────────────▓─────\n" +
                "────────▄█████████████▄───────\n" +
                "───────██▀▀▀▀▀▀▀▀▀▀▀▀▀██──────\n" +
                "───────▀───────────────▀──────\n" +
                "───────────█████████──────────\n" +
                "────────────███████───────────\n" +
                "──────────────███─────────────\n" +
                "──────██▄█─▄─█████─▄─█▄██─────\n" +
                "───────█████████████████──────\n" +
                "────────███████████████───────\n" +
                "─────────█████████████────────\n" +
                "──────────███████████─────────\n" +
                "──────────▀─███████─▀─────────\n" +
                "────────────▀─███─▀───────────\n" +
                "───────────────▀──────────────\n";


        System.out.println(logo);
        System.out.println("------------------------------------------------------------");
        System.out.println("Kratos commends you for your presence. Prepare for battle. \n" +
                        "Enter your commands with purpose.");
        System.out.println("------------------------------------------------------------");
    }

    // Method to say goodbye
    public static void end() {
        System.out.println("------------------------------------------------------------");
        System.out.println("            Until the next battle, mortal. \n" +
                "May your tasks be conquered with the ferocity of a god.");
        System.out.println("------------------------------------------------------------");
    }

    // Method to display marking of tasks
    public static void displayMarking(int taskNumber, String mark) {
        String displayString;
        if (mark.equals("mark")) {
            displayString = "    Task vanquished. Another notch on the blade of progress.\n" +
                    " What next, mortal?";
            tasksList[taskNumber].markTask();
        } else {
            displayString = "    Task restored from the depths of completion.\n" +
                    " A twist of fate, mortal. What now? \n" +
                    "Reclaim victory or face the abyss once more.";
            tasksList[taskNumber].unmarkTask();
        }
        
        System.out.println("------------------------------------------------------------");
        System.out.println(displayString);
        System.out.printf("         %s%n",  tasksList[taskNumber].toString());
        System.out.println("------------------------------------------------------------");
    }

    // Method to display tasks
    public static void displayTasks(int count) {
        System.out.println("----------------------------------------------------------------");
        System.out.println("    Your list of Tasks");
        for (int i = 0; i < count; i++) {
            System.out.printf("     %d. %s%n", i + 1, tasksList[i].toString());
        }
        System.out.println("----------------------------------------------------------------");
    }

    // Main method
    public static void main(String[] args) {
        greet();
        String userInput;
        Scanner in = new Scanner(System.in);
        while (true) {
            userInput = in.nextLine();
            if (userInput.equals("bye")) {
                break;
            } else if (userInput.equals("list")) {
                displayTasks(count);
            } else if (userInput.startsWith("mark") || userInput.startsWith("unmark")) {
                int taskNumber = Integer.parseInt(userInput.split(" ")[1]);
                displayMarking(taskNumber - 1, userInput.split(" ")[0]);
            } else {
                if (userInput.startsWith("deadline")) {
                    addDeadline(userInput);
                }
                else if (userInput.startsWith("todo")) {
                    addTodo(userInput);
                }
                if (userInput.startsWith("event")) {
                    addEvent(userInput);
                }
            }
        }
        end();
    }

    private static void addEvent(String userInput) {
        // Split the string by "/by" to separate the deadline and date
        String[] eventParts = userInput.split("/from");

        // Extract the deadline, action, and item
        String eventAndName = eventParts[0].trim();  // "event project meeting"
        String date = eventParts[1].trim();  // "Mon 2pm /to 4pm"

        // Split the deadline into action and item
        String[] event = eventAndName.split("\\s+", 2);
        String action = event[1]; // project Meeting

        String[] timeline = date.split("/to");

        tasksList[count] = new Event(action, timeline[0].trim(), timeline[1].trim());
        count++;
        System.out.println("----------------------------------------------------------------");
        System.out.println("Event recorded. Destiny's hourglass turns. \n" +
                "What now? Seize control or be swept by its sands?");
        System.out.println("----------------------------------------------------------------");
    }

    private static void addTodo(String userInput) {
        // Split line into action and type
        String[] actionAndType = userInput.split("\\s+", 2);
        String action = actionAndType[1];
        tasksList[count] = new Todo(action);
        count++;
        System.out.println("----------------------------------------------------------------");
        System.out.println("Task noted. A duty without a deadline? Dangerous. \n" +
                "   What now? Forge ahead or risk oblivion?");
        System.out.println("----------------------------------------------------------------");
    }

    private static void addDeadline(String userInput) {
        // Split the string by "/by" to separate the deadline and date
        String[] deadlineParts = userInput.split("/by");

        // Extract the deadline, action, and item
        String deadline = deadlineParts[0].trim();  // "deadline return book"
        String date = deadlineParts[1].trim();  // "Sunday"

        // Split the deadline into action and item
        String[] actionAndItem = deadline.split("\\s+", 2);
        String action = actionAndItem[0];
        String item = actionAndItem[1];
        tasksList[count] = new Deadline(item, date);
        count++;
        System.out.println("----------------------------------------------------------------");
        System.out.println("Deadline acknowledged. Time ticks away, mortal. \n" +
                "   What next? Embrace purpose or succumb to chaos?" );
        System.out.println("----------------------------------------------------------------");
    }
}
