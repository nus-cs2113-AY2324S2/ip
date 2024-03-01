import java.util.Scanner;
public class Ui {
    private static final String LINE = "____________________________________________________________";

    private Scanner in = new Scanner(System.in);

    private final static String logo =
                    "     ░▒▓███████▓▒░ ░▒▓██████▓▒░░▒▓███████▓▒░▒▓███████▓▒░  \n" +
                    "     ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░ ░▒▓█▓▒░     \n" +
                    "     ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░ ░▒▓█▓▒░     \n" +
                    "     ░▒▓███████▓▒░░▒▓████████▓▒░▒▓███████▓▒░  ░▒▓█▓▒░     \n" +
                    "     ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░ ░▒▓█▓▒░     \n" +
                    "     ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░ ░▒▓█▓▒░     \n" +
                    "     ░▒▓███████▓▒░░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░ ░▒▓█▓▒░  ";

    public void greetUser() {
        System.out.println(logo + "\nHello! I'm Bartholomew, but you can call me Bart for short :)");
        System.out.println("What can I do for you?\nType 'help' for a list of available commands!\n" + LINE);
    }
    public void byeUser() {
        System.out.println(LINE + "\nBye. Hope to see you again soon!\n" + LINE);
    }

    public void printHelp() {
        System.out.println(LINE + "\n'list' lists all current tasks" +
                "\n'mark <#>' marks tasks with X" +
                "\n'unmark <#>' unmarks tasks by removing the X" +
                "\n'todo <task>' creates a to-do" +
                "\n'deadline <task> /by <time>' creates a task with deadline" +
                "\n'event <task> /from <time> /to <time>' creates a to-do" +
                "\n'bye' to quit\n" + LINE);
    }

    public void println(String s) {
        System.out.println(s);
    }
    public String getInput() {
        return in.nextLine().trim();
    }
}
