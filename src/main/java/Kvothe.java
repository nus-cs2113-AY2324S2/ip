import java.util.Scanner;

public class Kvothe {

    private static final int MAXTASKS = 100;
    private static Task[] tasks = new Task[MAXTASKS];
    private static int tasksIndex = 0;

    public static void main(String[] args) {
        String greeting = "Hello! I'm Kvothe.\n"
                + "\t\tWhat can I do for you?\n";

        String bye = "Bye. Hope to see you again soon!";

        echo(greeting);

        interact();

        echo(bye);
    }


    private static void echo(String line, boolean isTop, boolean isBottom) {
        if (isTop) {
            System.out.println("\t\t____________________________________________________________");
        }

        System.out.println("\t\t" + line);

        if (isBottom) {
            System.out.println("\t\t____________________________________________________________");
        }

    }

    private static void echo(String line) {
        System.out.println("\t\t____________________________________________________________");
        System.out.println("\t\t" + line);
        System.out.println("\t\t____________________________________________________________");

    }

    private static void add(String line) {
        tasks[tasksIndex] = new Task(line);
        tasksIndex++;
    }

    private static void list() {
        for (int i = 0; i < tasksIndex; i++) {
            echo(i + 1 + ". " + tasks[i], i == 0, i == tasksIndex - 1);
        }
    }

    private static void done(int index) {
        tasks[index - 1].markAsDone();
        echo("Nice! I've marked this task as done:\n\t\t" + tasks[index - 1]);
    }

    private static void mark(int index){
        tasks[index - 1].setIsDone(true);
        echo("Nice! I've marked this task as done:\n\t\t" + tasks[index - 1]);
    }

    private static void unmark(int index){
        tasks[index - 1].setIsDone(false);
        echo("OK, I've marked this task as not done yet:\n\t\t" + tasks[index - 1]);
    }

    private static int getTaskNumber(String[] args){

        int index = 0;
        if (args.length < 2 || args.length > 2) {
           echo("Sorry. Please specify one task number.");
           return -1;
        }

        try{
            // First argument is the command
            index = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            echo("Sorry. Please specify the task number as an integer.");
            return -1;
        }

        if(index > tasksIndex){
            echo("Sorry. There are just " + tasksIndex + " tasks in the list.");
            return -1;
        }

        return index;

    }

    public static void interact() {
        String stopWord = "bye";
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();

        while (!line.equals(stopWord)) {
            String lineWords[] = line.split(" ");
            String command = lineWords[0];

            int taskNumber;

            switch (command) {
                case "list":
                    list();
                    break;
                case "mark":
                    taskNumber = getTaskNumber(lineWords);
                    if(taskNumber != -1){
                        mark(taskNumber);
                    }
                    break;
                case "unmark":
                    taskNumber = getTaskNumber(lineWords);
                    if(taskNumber != -1){
                        unmark(taskNumber);
                    }
                    break;
                default:
                    add(line);
                    echo("added: " + line);
                    break;
            }
            line = in.nextLine();
        }

    }


}