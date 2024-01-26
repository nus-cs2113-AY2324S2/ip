import java.util.Scanner;

public class Faiz {
    public static final String HorLine = "\t_________________________________________\n";
    static Task[] tasks = new Task[100];

    public static void printHi() {
        System.out.println(HorLine + "\t Hello! I'm Faiz!\n\t What can I do for you?\n" + HorLine);
    }

    public static void printBye() {
        System.out.println(HorLine + "\t Bye. Hope to see you again soon!\n" + HorLine);
    }

    public static void echo(String line) {
        System.out.println(HorLine + "\t added: " + line + System.lineSeparator() + HorLine);
    }

    public static void listTasks(Task[] tasks, int ind) {
        System.out.print(Faiz.HorLine + "\t Here are the tasks in your list:\n");
        for (int i = 0; i < ind; i++) {
            System.out.println("\t " + (i + 1) + "." + tasks[i].toString());
        }
        System.out.println(Faiz.HorLine);
    }

    public static void markAsDone(int index) {
        tasks[index].markAsDone();
        System.out.print(Faiz.HorLine + "\t Nice! I've marked this task as done:\n\t " + tasks[index].toString() + System.lineSeparator() + HorLine);
    }

    public static void markAsUndone(int index) {
        tasks[index].markAsUndone();
        System.out.print(Faiz.HorLine + "\t OK, I've marked this task as not done yet:\n\t " + tasks[index].toString() + System.lineSeparator() + HorLine);
    }

    public static void main(String[] args) {
        String line;
        int ind = 0;
        Scanner in = new Scanner(System.in);
        printHi();
        while (true) {
            line = in.nextLine();
            if (line.equals("bye")) {
                break;
            } else if (line.equals("list")) {
                listTasks(tasks, ind);
            } else if (line.contains("mark")) {
                String[] words = line.split(" ");
                if (words[0].equals("mark")) {
                    markAsDone(Integer.parseInt(words[1]) - 1);
                } else if (words[0].equals("unmark")) {
                    markAsUndone(Integer.parseInt(words[1]) - 1);
                }
            } else {
                echo(line);
                Task t = new Task(line);
                tasks[ind] = t;
                ind++;
            }
        }
        printBye();
    }
}
