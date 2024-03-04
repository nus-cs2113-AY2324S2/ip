import java.util.Scanner;
import java.util.ArrayList;

public class Ui {
    private static final Scanner scanner = new Scanner(System.in);
    public static final String LINE = "----------------------------------------------------------------";
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
    public static String readCommand() {
        return scanner.nextLine().trim();
    }

    public static void closeScanner() {
        scanner.close();
    }

    public static void taskRemainderDisplay(ArrayList<Task> tasksList) {
        System.out.println(tasksList.size() + " tasks linger, shadows yet unvanquished. How will you face them?");
        System.out.println(Ui.LINE);
    }

    public static void taskDeleteMessage(int taskNumber, ArrayList<Task> tasksList) {
        String displayString = "Task erased. Its existence now a whisper in the winds of fate.\n" +
                "What's your next decree?";
        System.out.println(Ui.LINE);
        System.out.println(displayString);
        System.out.printf("         %s%n",  tasksList.get(taskNumber).toString());
    }
    public static void displayTasks(int count, ArrayList<Task> tasksList) {
        System.out.println(Ui.LINE);
        System.out.println("    Your list of Tasks");
        for (int i = 0; i < count; i++) {
            System.out.printf("     %d. %s%n", i + 1, tasksList.get(i).toString());
        }
        System.out.println(Ui.LINE);
    }

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
        System.out.println(LINE);
        System.out.println("Kratos commends you for your presence. Prepare for battle.\n" +
                "Enter your commands with purpose.");
        System.out.println(LINE);
    }

    // Method to say goodbye
    public static void end() {
        System.out.println(LINE);
        System.out.println("            Until the next battle, mortal.\n" +
                "May your tasks be conquered with the ferocity of a god.");
        System.out.println(LINE);
    }

    private static void taskAddMessage(String x, String x1) {
        System.out.println(Ui.LINE);
        System.out.println(x +
                x1);
        System.out.println(Ui.LINE);
    }

    public static void displayMessageSelector(String type){
        String x;
        String x1;
        if (type.equalsIgnoreCase("deadline")){
            x = "Deadline acknowledged. Time ticks away, mortal.\n";
            x1 =  "What next? Embrace purpose or succumb to chaos?";
        } else if (type.equalsIgnoreCase("todo")) {
            x = "Task noted. A duty without a deadline? Dangerous.\n";
            x1 =  "What now? Forge ahead or risk oblivion?";
        } else {
            x = "Event recorded. Destiny's hourglass turns.\n";
            x1 =  "What now? Seize control or be swept by its sands?";
        }
        taskAddMessage(x, x1);
    }
}
