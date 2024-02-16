import kvothe.exception.WrongArgumentsException;
import kvothe.task.Deadline;
import kvothe.task.Event;
import kvothe.task.Task;
import kvothe.task.Todo;

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


    private static void add(String line) throws WrongArgumentsException {

        String lineWords[] = line.split(" ");
        String command = lineWords[0];
        Task newTask = null;
        String args[];

        switch (command) {
        case "todo":
            args = Task.parseLine(line);
            newTask = new Todo(args[0]);
            break;
        case "deadline":
            args = Task.parseLine(line, Deadline.args);
            newTask = new Deadline(args[0], args[1]);
            break;
        case "event":
            args = Task.parseLine(line, Event.args);
            newTask = new Event(args[0], args[1], args[2]);
            break;
        default:
            echo("Sorry. I do not support that method.");
            break;
        }

        tasks[tasksIndex] = newTask;
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
        if (args.length != 2 ) {
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

            try{
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
                case "todo":
                case "deadline":
                case "event":
                    add(line);
                    echo("added: " + line + "\n\t\tnow you have " + tasksIndex + " tasks in the list");
                    break;
                default:
                    throw new WrongArgumentsException("Sorry. I do not support the method." + command);
                }
            }catch (WrongArgumentsException e) {
                echo(e.toString());
            }

            line = in.nextLine();
        }

    }


}