import java.util.Scanner;

public class Faiz {
    public static final String HorLine = "\t_________________________________________\n";

    public static void printHi() {
        System.out.println(HorLine + "\t Hello! I'm Faiz!\n\t What can I do for you?\n" + HorLine);
    }

    public static void printBye() {
        System.out.println(HorLine + "\t Bye. Hope to see you again soon!\n" + HorLine);
    }

    public static void echo(String line) {
        System.out.println(HorLine + "\t added: " + line + System.lineSeparator() + HorLine);
    }

    private static void listTasks(String[] tasks, int ind) {
        System.out.print(HorLine);
        for (int i = 0; i < ind; i++) {
            System.out.println("\t " + (i + 1) + ". " + tasks[i]);
        }
        System.out.println(HorLine);
    }

    public static void main(String[] args) {
        String line;
        String[] tasks = new String[100];
        int ind = 0;
        Scanner in = new Scanner(System.in);
        printHi();
        while (true) {
            line = in.nextLine();
            if (line.equals("bye")) {
                break;
            } else if (line.equals("list")) {
                listTasks(tasks, ind);
            } else {
                echo(line);
                tasks[ind] = line;
                ind++;
            }
        }
        printBye();
    }
}
