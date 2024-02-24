import org.w3c.dom.Text;
import quill.exception.EmptyDateException;
import quill.exception.QuillException;
import quill.task.Deadline;
import quill.task.Event;
import quill.task.Task;
import quill.task.Todo;
import quill.ui.TextUi;

import java.util.ArrayList;
import java.util.Scanner;
public class Quill {
    private static TextUi ui;

    public static void printDeleteTask(String line, ArrayList<Task> tasks) {
        try {
            int taskNumber = Integer.parseInt(line) - 1;
            TextUi.showDeleteTask(tasks.get(taskNumber));
            tasks.remove(taskNumber);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Hey, wake up! That task? Non-existent. Try something real.");
        } catch (NumberFormatException e) {
            System.out.println("Listen up! Numbers only, got it? Don't bother with anything else");
        }
    }

    public static void performMarkOrUnmark(String line, ArrayList<Task> tasks, boolean isDone) {
        try {
            int taskNumber = Integer.parseInt(line) - 1;
            if (isDone) {
                tasks.get(taskNumber).markAsDone();
                TextUi.showDivider();
                System.out.println("Nice! I've marked this task as done:");
            } else {
                tasks.get(taskNumber).markAsNotDone();
                TextUi.showDivider();
                System.out.println("OK, I've marked this task as not done yet:");
            }
            System.out.println(tasks.get(taskNumber).toString());
            TextUi.showDivider();
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            System.out.println("Hey, wake up! That task? Non-existent. Try something real.");
        } catch (NumberFormatException e) {
            System.out.println("Listen up! Numbers only, got it? Don't bother with anything else");
        }
    }


    public static void main(String[] args) {
        TextUi.showWelcomeMessage();
        String line;
        ArrayList<Task> tasks;
        tasks = Save.readFile();
        ui = new TextUi();
        line = ui.getUserCommand();

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
                TextUi.showGoodbyeMessage();
                return;
            case "list":
                if (Task.isEmpty()) {
                    TextUi.showDivider();
                    System.out.println("Zero tasks. Add something already.");
                } else {
                    TextUi.showDivider();
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < Task.getTotalTasks(); i++) {
                        System.out.println(i + 1 + "." + tasks.get(i).toString());
                    }
                }
                TextUi.showDivider();
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
                    TextUi.showAddTask(tasks.get(taskNumber));
                }
                break;
            case "deadline":
                try {
                    tasks.add(new Deadline(line));
                    TextUi.showAddTask(tasks.get(taskNumber));
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
                    TextUi.showAddTask(tasks.get(taskNumber));
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
            case "delete":
                printDeleteTask(line, tasks);
                break;
            default:
                System.out.println("Enough with the gibberish. Stick to the commands I understand:");
                System.out.println("bye, list, todo, deadline, event, mark, unmark, delete. Got it? Next!");
                break;
            }
            line = ui.getUserCommand();
        }
    }
}
