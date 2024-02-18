import quill.exception.EmptyDateException;
import quill.exception.QuillException;
import quill.task.Deadline;
import quill.task.Event;
import quill.task.Task;
import quill.task.Todo;

import java.util.ArrayList;
import java.util.Scanner;
public class Quill {
    public static final String horizontalLine = "____________________________________________________________";
    public static void printAddTask(Task tasks) {
        System.out.println(horizontalLine + "\nGot it. I've added this task:");
        System.out.println(tasks.toString());
        System.out.println("Now you have " + Task.getTotalTasks() + " tasks in the list.\n" + horizontalLine);
    }

    public static void performMarkOrUnmark(String line, ArrayList<Task> tasks, boolean isDone) {
        try {
            int taskNumber = Integer.parseInt(line) - 1;
            if (isDone) {
                tasks.get(taskNumber).markAsDone();
                System.out.println(horizontalLine + "\nNice! I've marked this task as done:");
            } else {
                tasks.get(taskNumber).markAsNotDone();
                System.out.println(horizontalLine + "\nOK, I've marked this task as not done yet:");
            }
            System.out.println(tasks.get(taskNumber).toString() + "\n" + horizontalLine);
        } catch (NullPointerException e) {
            System.out.println("Hey, wake up! That task? Non-existent. Try something real.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("1 to 100 only. Seriously, no zeros, no hundreds. Got it?");
        } catch (NumberFormatException e) {
            System.out.println("Listen up! Numbers only, got it? Don't bother with anything else");
        }
    }


    public static void main(String[] args) {
        String name = "Quill";
        String line;
        ArrayList<Task> tasks;
        tasks = Save.readFile();
        Scanner in = new Scanner(System.in);

        System.out.println(horizontalLine + "\nHello! I'm " + name + ".");
        System.out.println("What can i do for you?\n" + horizontalLine);

        line = in.nextLine();

        while(true) {
            String command;
            int taskNumber = Task.getTotalTasks();
            int index = line.indexOf(" ");
            if (index != -1) {
                command = line.substring(0, index);
                line = line.substring(index + 1);
            } else {
                command = line;
                line = "";
            }
            switch(command) {
            case "bye":
                Save.writeToFile(tasks);
                System.out.println(horizontalLine + "\nBye! Hope to see you again soon!\n" + horizontalLine);
                return;
            case "list":
                if (Task.isEmpty()) {
                    System.out.println(horizontalLine + "\nZero tasks. Add something already.");
                } else {
                    System.out.println(horizontalLine + "\nHere are the tasks in your list:");
                    for (int i = 0; i < Task.getTotalTasks(); i++) {
                        System.out.println(i + 1 + "." + tasks.get(i).toString());
                    }
                }
                System.out.println(horizontalLine);
                break;
            case "mark":
                performMarkOrUnmark(line, tasks, true);
                break;
            case "unmark":
                performMarkOrUnmark(line, tasks, false);
                break;
            case "todo":
                if (line.isEmpty()) {
                    System.out.println("No empty descriptions allowed for todos. Fill it in!");
                } else {
                    tasks.add(new Todo(line));
                    printAddTask(tasks.get(taskNumber));
                }
                break;
            case "deadline":
                try {
                    tasks.add(new Deadline(line));
                    printAddTask(tasks.get(taskNumber));
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("Seriously? You call that a deadline?");
                    System.out.println("It's 'deadline [task] /by [date]'. Get it right!");
                    Task.removeTask();
                    break;
                } catch (EmptyDateException e) {
                    break;
                } catch (QuillException e) {
                    System.out.println("No empty descriptions allowed for deadline. Fill it in!");
                    break;
                }
                break;
            case "event":
                try {
                    tasks.add(new Event(line));
                    printAddTask(tasks.get(taskNumber));
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("Seriously? You call that an event?");
                    System.out.println("It's 'event [task] /from [date] /to [date]'. Get it right!");
                    Task.removeTask();
                } catch (EmptyDateException e) {
                    break;
                } catch (QuillException e) {
                    System.out.println("No empty descriptions allowed for event. Fill it in!");
                }
                break;
            default:
                System.out.println("Enough with the gibberish. Stick to the commands I understand:");
                System.out.println("bye, list, todo, deadline, event, mark, unmark. Got it? Next!");
                break;
            }
            line = in.nextLine();
        }
    }
}
