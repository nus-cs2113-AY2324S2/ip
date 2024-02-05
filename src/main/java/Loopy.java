import java.util.Scanner;  // Import the Scanner class
import java.util.ArrayList;

public class Loopy {
    private static ArrayList<String> tasks = new ArrayList<>();

    public static void main(String[] args) {
        String logo = " __                \n"
                + "| |    ____ ____ ____ \n"
                + "| |   | |-| ||-| | _ \\\n"
                + "| |___| |_| ||_| | __/\n"
                + "|____/ \\__/ \\__/_| | \n";
        System.out.println("Hello! I'm Loopy!\n" + logo);
        System.out.println("What can I do for you?\n");

        Scanner taskScanner = new Scanner(System.in);  // Create a Scanner object


        //create taskList array

        while (true) {
            String task = taskScanner.nextLine();
            processTask(task);
            if (task.equals("bye")) {
                System.out.println("Bye! Hope to see you again.\n");
                System.out.println(".                       \n" +
                        "                                 --                      \n" +
                        "                                 =..                     \n" +
                        "                                .=.::                    \n" +
                        "                                :=:.                     \n" +
                        "                 .::.. ....::::::::..                    \n" +
                        "                .-+++-:::::::::::::::::..                \n" +
                        "                 :+=-:::::----------::::::....           \n" +
                        "                 .------------------------:=++-.         \n" +
                        "                .---------------------------=+=.         \n" +
                        "                :---------===-------=-------=:.          \n" +
                        "               .---------===================-            \n" +
                        "              .--------=#%#*+===============-            \n" +
                        "             .----------*%%+======++===----.           \n" +
                        "             :=------------------=+@@#=------.           \n" +
                        "             -======---==-=%@@@%*---==-------.           \n" +
                        "             -+===========*@@@%*##====-------.           \n" +
                        "             .+++++++++++++#%@@@#++==========.           \n" +
                        "              :+++++++*********+++=========.           \n" +
                        "               .=********##=-=*#**++++++++++:            \n" +
                        "                  -+#******++******++++++:             \n" +
                        "            .:-=-.:**###****************+.               \n" +
                        "          .=++***=++****#########+=--:..                 \n" +
                        "         .=+****===+++++*********+:                      \n" +
                        "         .++***===+*+++++++++++++++-.                    \n" +
                        "          =+++-===+*++=====----==+*+=.                   \n" +
                        "          .++---=+*+===-:::::::---++:                  \n" +
                        "           -----=+*++=::::::::::-:=*+++=.                \n" +
                        "            :-=-##*+=-::::::::::::-+++=.               \n" +
                        "            :%#*#+=--:::::::::::-.:=*%#+               \n" +
                        "             -++++++----:::::::::--.  .:-.");
                break;
            }
        }
    }

    private static void processTask(String task) {
        if (task.equals("list")) {
            displayTaskList();
        } /*else if (task.startsWith("mark ")) {
            markTaskAsDone(task);
        } else if (task.startsWith("unmark ")) {
            markTaskAsUndone(task);
        }*/ else {
            addTask(task);
        }
    }

    private static void addTask(String task) {
        if (!task.equals("bye")) {
            tasks.add(task);
            System.out.println("added: " + task);
        }
    }

    private static void displayTaskList() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

   /* private static void markTaskAsDone(String task) {
        int taskIndex = Integer.parseInt(task.substring(5)) - 1;
        if (taskIndex >= 0 && taskIndex < tasks.size()) {
            tasks.get(taskIndex).markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("  " + tasks.get(taskIndex).toString());
        } else {
            System.out.println("Invalid task index. Please provide a valid index.");
        }
    }

    private static void markTaskAsUndone(String task) {
        int taskIndex = Integer.parseInt(task.substring(7)) - 1;
        if (taskIndex >= 0 && taskIndex < tasks.size()) {
            tasks.get(taskIndex).markAsNotDone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("  " + tasks.get(taskIndex).toString());
        } else {
            System.out.println("Invalid task index. Please provide a valid index.");
        }
    }

    static class Task {
        private String description;
        private boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public void markAsDone() {
            this.isDone = true;
        }

        public void markAsNotDone() {
            this.isDone = false;
        }

        @Override
        public String toString() {
            return "[" + (isDone ? "X" : " ") + "] " + description;
        }
    }*/
}


