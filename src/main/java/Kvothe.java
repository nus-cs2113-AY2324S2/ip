import kvothe.exception.WrongArgumentsException;
import kvothe.task.Deadline;
import kvothe.task.Event;
import kvothe.task.Task;
import kvothe.task.Todo;

import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.ArrayList;

public class Kvothe {

    private static final int MAXTASKS = 100;
    private static ArrayList<Task> tasks = new ArrayList<Task>(MAXTASKS);

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

        tasks.add(newTask);
        echo("added: " + line + "\n\t\tnow you have " + tasks.size() + " tasks in the list");
    }

    private static void delete(int index) {
        echo("Noted. I've removed this task:\n\t\t" + tasks.get(index - 1));
        tasks.remove(index - 1);
        echo("Now you have " + tasks.size() + " tasks in the list.");
    }

    private static void list() {
        for (int i = 0; i < tasks.size(); i++) {
            echo(i + 1 + ". " + tasks.get(i), i == 0, i == tasks.size() - 1);
        }
    }

    private static void done(int index) {
        tasks.get(index - 1).markAsDone();
        echo("Nice! I've marked this task as done:\n\t\t" + tasks.get(index - 1));
    }

    private static void mark(int index){
        tasks.get(index - 1).setIsDone(true);
        echo("Nice! I've marked this task as done:\n\t\t" + tasks.get(index - 1));
    }

    private static void unmark(int index){
        tasks.get(index - 1).setIsDone(false);
        echo("OK, I've marked this task as not done yet:\n\t\t" + tasks.get(index - 1));
    }

    private static int getTaskNumber(String[] args) throws WrongArgumentsException{

        int index = 0;
        if (args.length != 2 ) {
          throw new WrongArgumentsException("Sorry. Input a task number.");
        }

        try{
            // First argument is the command
            index = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            throw new WrongArgumentsException("Sorry. The task number must be a number.");
        }

        if(index > tasks.size()){
            throw new WrongArgumentsException("Sorry. There are only " + tasks.size() + " tasks in the list");
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
                    mark(taskNumber);
                    break;
                case "unmark":
                    taskNumber = getTaskNumber(lineWords);
                    unmark(taskNumber);
                    break;
                case "todo":
                case "deadline":
                case "event":
                    add(line);
                    break;
                case "delete":
                    taskNumber = getTaskNumber(lineWords);
                    delete(taskNumber);
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