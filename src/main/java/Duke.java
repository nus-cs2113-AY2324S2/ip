import java.util.Scanner;

public class Duke {
    static String[] taskList = new String[100];

    // Method to greet the user
    public static void greet() {
        String logo = "  TTTTT  AAAAA  TTTTT  EEEEE\n" +
                "    T    A   A    T    E\n" +
                "    T    AAAAA    T    EEEE\n" +
                "    T    A   A    T    E\n" +
                "    T    A   A    T    EEEEE\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("------------------------------------------------------------");
        System.out.println("                Hello! I'm Tate!");
        System.out.println("                What do you need?\n");
        System.out.println("------------------------------------------------------------");
    }

    // Method to say goodbye
    public static void end() {
        System.out.println("------------------------------------------------------------");
        System.out.println("            Goodbye and Take care, later then!");
        System.out.println("------------------------------------------------------------");
    }

    // Method to display tasks
    public static void displayTask(int count) {
        System.out.println("----------------------------------------------------------------");
        for (int i = 0; i < count; i++) {
            System.out.println("     " + (i + 1) + ". " + taskList[i]);
        }
        System.out.println("----------------------------------------------------------------");
    }

    // Main method
    public static void main(String[] args) {
        greet();
        String line;
        Scanner in = new Scanner(System.in);
        int count = 0;
        while (true) {
            line = in.nextLine();
            if (line.equals("bye")) {
                break;
            } else if (line.equals("list")) {
                displayTask(count);
            } else {
                taskList[count] = line;
                count++;
                System.out.println("    ------------------------------------------------------------");
                System.out.println("    " + "added: " + line);
                System.out.println("----------------------------------------------------------------");
            }
        }
        end();
    }
}
