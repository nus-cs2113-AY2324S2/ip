package ui;

import exceptions.DuplicateMarkException;
import exceptions.DuplicateUnmarkException;
import exceptions.EmptyTaskException;
import tasks.Task;
import parser.Parse;

import java.util.Scanner;

public class UI {

    public static final String NEW_LINE = "____________________________________________________________\n";

    public static void greetUser() {
        String chat_name = "Sigma";
        String output = NEW_LINE + "Hello! I'm " + chat_name + "\n"
                + "What can I do for you?\n" + NEW_LINE;
        System.out.println(output);
    }

    public static void sayGoodBye() {
        System.out.println(NEW_LINE + "Bye. Hope to see you again soon!\n" + NEW_LINE);
    }

    public static boolean getUserInput () {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        if (input.equals("bye")) {
            return true;
        } else {

            if (input.equals("list")) {
                Task.showTasks();
            } else if (input.startsWith("mark ")) {
                try {
                    Task.mark(Parse.getTaskNumber(input));
                } catch (IndexOutOfBoundsException | NullPointerException | NumberFormatException e) {
                    System.out.println("That task does not exist!");
                } catch (DuplicateMarkException e) {
                    System.out.println("Task is already marked!");
                } catch (Exception e) {
                    System.out.println("Error marking task :/");
                }
            } else if (input.startsWith("unmark ")) {
                try {
                    Task.unMark(Parse.getTaskNumber(input));
                } catch (IndexOutOfBoundsException | NullPointerException | NumberFormatException e) {
                    System.out.println("That task does not exist!");
                } catch (DuplicateUnmarkException e) {
                    System.out.println("Task is currently unmarked!");
                } catch (Exception e) {
                    System.out.println("Error marking task :/");
                }
            } else if (input.startsWith("todo ") || input.startsWith("deadline ") || input.startsWith("event ")) {
                try {
                    Task.handleTasks(input);
                } catch (EmptyTaskException e) {
                    System.out.println("Task cannot be empty!");
                }
            } else if (input.startsWith("remove ")) {
                try {
                    Task.deleteTask(Parse.getTaskNumber(input) - 1);
                } catch (Exception e) {
                    System.out.println("Error removing task");
                }
            } else {
                System.out.println(NEW_LINE + "Sorry, I don't recognise that input\n"
                        + "Hint: Use todo/event/deadline [task] to list tasks\n"
                        + "OR Use mark/unmark/remove [task number] to edit tasks\n"
                        + NEW_LINE);
            }
            return false;
        }
    }
}
