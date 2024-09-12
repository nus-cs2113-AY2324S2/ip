import java.util.Scanner;
import java.util.ArrayList;

public class Ui {
    private static final Scanner scanner = new Scanner(System.in);
    public static final String LINE = "----------------------------------------------------------------";
    public static final String LIST = "full";
    public static final String FIND = "find";
    static String deadlineMissing = "Your intentions for a 'deadline' task are as unclear as the shifting sands of time.\n" +
            "Specify a task after 'deadline' or face the consequences.";
    static String todoMissing = "Your intentions for a 'todo' task are as vague as the mists of Hades.\n" +
            "Specify a task after 'todo' or face the consequences.";
    static String eventMissing = "Your intentions for an 'event' are as elusive as a fleeting dream.\n" +
            "Specify a task after 'event' or face the consequences.";
    static String taskMissing = "Your intentions are as vague as the mists of Hades.\n" +
            "Specify a valid command or face the consequences.";
    static String folderNotFound = "A new file's birth marred by chaos. Restore order.\n";
    static String fileNotFound = "Darkness shrouds the file. Forging its presence......";
    public static String fileReadingError = "Tasks trapped in the void. Break the chains of ignorance.\n";
    static String markTask = "Task vanquished. Another notch on the blade of progress.\n" +
            "What next, mortal?";
    static String markedTask = "Your task bears the mark of completion.\n" +
            "Attempting to mark it again is futile.";
    static String unmarkTask = "Task restored from the depths of completion.\n" +
            "A twist of fate, mortal. What now?\n" +
            "Reclaim victory or face the abyss once more.";
    static String unmarkedTask = "Foolish mortal.\n" +
            "Once a task is freed, it shall not be bound again.";

    
    static String fullListOfTasks = "    Your list of Tasks";
    static String matchedTasks = "Behold, the deeds that align with your purpose.";
    /**
     * Prompt the user to enter a command and return the input.
     *
     * @return The user's input command.
     */
    public static String readCommand() {
        return scanner.nextLine().trim();
    }

    /**
     * Close the scanner object to release system resources.
     */
    public static void closeScanner() {
        scanner.close();
    }

    /**
     * Display the remaining tasks in the list.
     *
     * @param tasksList The list of tasks.
     */
    public static void taskRemainderDisplay(ArrayList<Task> tasksList) {
        System.out.println(tasksList.size() + " tasks linger, shadows yet unvanquished. How will you face them?");
        System.out.println(Ui.LINE);
    }

    /**
     * Display a message when a task is deleted.
     *
     * @param taskNumber The index of the task being deleted.
     * @param tasksList  The list of tasks.
     */
    public static void taskDeleteMessage(int taskNumber, ArrayList<Task> tasksList) {
        String displayString = "Task erased. Its existence now a whisper in the winds of fate.\n" +
                "What's your next decree?";
        System.out.println(Ui.LINE);
        System.out.println(displayString);
        System.out.printf("         %s%n",  tasksList.get(taskNumber).toString());
    }

    /**
     * Display the tasks in the list based on the purpose.
     *
     * @param tasksList The list of tasks.
     * @param purpose   Specifies which method the function is called from.
     */
    public static void displayTasks(String purpose, ArrayList<Task> tasksList) {
        System.out.println(Ui.LINE);
        String display = purpose.equalsIgnoreCase("find") ? matchedTasks : fullListOfTasks;
        System.out.println(display);

        for (Task task : tasksList) {
            int index = Kratos.tasksList.indexOf(task) + 1; // Get the actual index of the task in the main tasksList
            System.out.printf("     %d. %s%n", index, task.toString());
        }

        System.out.println(Ui.LINE);
    }


    /**
     * Method to greet the user with ASCII art
     */
    public static void greet() {
        String logo =   "\t\t\t──────────────────────────────\n" +
                        "\t\t\t───────────────────────▓▓▓▓───\n" +
                        "\t\t\t──────────────────────▓▓▓▓▓▓──\n" +
                        "\t\t\t─────────────────────▓▓▓▓▓▓▓▓─\n" +
                        "\t\t\t────────────────────▓▓▓▓▓▓▓▓──\n" +
                        "\t\t\t───────────────────▓▓▓▓▓▓▓▓───\n" +
                        "\t\t\t──────────────────▓▓▓▓▓▓▓▓────\n" +
                        "\t\t\t─────────────────▓▓▓▓▓▓▓▓─────\n" +
                        "\t\t\t─────────█──────▓▓▓▓▓▓▓▓──────\n" +
                        "\t\t\t────────██─────▓▓▓▓▓▓▓▓───────\n" +
                        "\t\t\t───────██──────▓▓▓▓▓▓▓▓▓──────\n" +
                        "\t\t\t─▀██▄─██───────▓▓▓▓▓▓▓▓██──▄█─\n" +
                        "\t\t\t─█████████▄─▄───▓▄▓▄████████──\n" +
                        "\t\t\t──█▀▀▀███████────███████▀▀▀██─\n" +
                        "\t\t\t─███▄▄██▄███▀─────███▄██▄▄███─\n" +
                        "\t\t\t─█─██████▀█────────▓████████▀─\n" +
                        "\t\t\t────██▀───▀─█────█──▓▓▓▓▓▓▓▓──\n" +
                        "\t\t\t────▄█▀──────█──█────▓▓▓▓▓▓▓▓─\n" +
                        "\t\t\t───▀█────────█──█─────▓▓▓▓▓▓▓─\n" +
                        "\t\t\t───▄█▀────▄▄█▀──▀█▄▄───▓▓▓▓▓▓─\n" +
                        "\t\t\t───█▄────█──█────█──█──▓▓▓▓▓▓─\n" +
                        "\t\t\t───█─────▀──────────▀──▓▓▓▓▓▓─\n" +
                        "\t\t\t────▀─────▀█──────█▀────▓▓▓───\n" +
                        "\t\t\t────────────▀▄▄▄▄▀──────▓▓────\n" +
                        "\t\t\t────────────────────────▓─────\n" +
                        "\t\t\t────────▄█████████████▄───────\n" +
                        "\t\t\t───────██▀▀▀▀▀▀▀▀▀▀▀▀▀██──────\n" +
                        "\t\t\t───────▀───────────────▀──────\n" +
                        "\t\t\t───────────█████████──────────\n" +
                        "\t\t\t────────────███████───────────\n" +
                        "\t\t\t──────────────███─────────────\n" +
                        "\t\t\t──────██▄█─▄─█████─▄─█▄██─────\n" +
                        "\t\t\t───────█████████████████──────\n" +
                        "\t\t\t────────███████████████───────\n" +
                        "\t\t\t─────────█████████████────────\n" +
                        "\t\t\t──────────███████████─────────\n" +
                        "\t\t\t──────────▀─███████─▀─────────\n" +
                        "\t\t\t────────────▀─███─▀───────────\n" +
                        "\t\t\t───────────────▀──────────────\n";
        System.out.println(logo);
        System.out.println(LINE);
        System.out.println("Kratos commends you for your presence. Prepare for battle.\n" +
                "Enter your commands with purpose.");
        System.out.println(LINE);
    }

    /**
     * Method to say goodbye message.
     */
    public static void end() {
        System.out.println(LINE);
        System.out.println("            Until the next battle, mortal.\n" +
                "May your tasks be conquered with the ferocity of a god.");
        System.out.println(LINE);
    }

    /**
     * Helper method to display messages for adding tasks.
     *
     * @param sentence1  The first part of the message.
     * @param sentence2 The second part of the message.
     */
    private static void taskAddMessage(String sentence1, String sentence2) {
        System.out.println(Ui.LINE);
        System.out.println(sentence1 +
                sentence2);
        int index = Kratos.tasksList.size()-1; // Get the actual index of the task in the main tasksList
        System.out.printf("     %d. %s%n", index+1, Kratos.tasksList.get(index).toString());
        System.out.println(Ui.LINE);
    }

    /**
     * Display a message selector based on the type of task.
     *
     * @param type The type of task (deadline, todo, or event).
     */
    public static void displayMessageSelector(String type){
        String firstSentence;
        String secondSentence;
        if (type.equalsIgnoreCase("deadline")){
            firstSentence = "Deadline acknowledged. Time ticks away, mortal.\n";
            secondSentence =  "What next? Embrace purpose or succumb to chaos?";
        } else if (type.equalsIgnoreCase("todo")) {
            firstSentence = "Task noted. A duty without a deadline? Dangerous.\n";
            secondSentence =  "What now? Forge ahead or risk oblivion?";
        } else {
            firstSentence = "Event recorded. Destiny's hourglass turns.\n";
            secondSentence =  "What now? Seize control or be swept by its sands?";
        }
        taskAddMessage(firstSentence, secondSentence);
    }

    /**
     * Display marking information for a task.
     *
     * @param taskNumber    The index of the task.
     * @param tasksList     The list of tasks.
     * @param displayString The message to display.
     */
    public static void displayMarking(int taskNumber, ArrayList<Task> tasksList, String displayString) {
        System.out.println(LINE);
        System.out.println(displayString);
        System.out.printf("         %s%n",  tasksList.get(taskNumber).toString());
        System.out.println(LINE);
    }
    public static void displayCommands() {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║           Available Commands           ║");
        System.out.println("╠════════════════════════════════════════╣");
        System.out.println("║ 1. list            - Display all tasks ║");
        System.out.println("║ 2. mark <num>      - Mark task as done ║");
        System.out.println("║ 3. unmark <num>    - Unmark task       ║");
        System.out.println("║ 4. deadline <desc> - Add a deadline    ║");
        System.out.println("║ 5. todo <desc>     - Add a todo        ║");
        System.out.println("║ 6. event <desc>    - Add an event      ║");
        System.out.println("║ 7. delete <num>    - Delete a task     ║");
        System.out.println("║ 8. find <keyword>  - Find tasks        ║");
        System.out.println("║ 9. bye             - Exit program      ║");
        System.out.println("╚════════════════════════════════════════╝");
    }

    public static void noTaskFound() {
        System.out.println(LINE);
        System.out.println("None dare to stand before your path,\n" +
                "for you have already vanquished them all.");
        System.out.println(LINE);
    }
}
