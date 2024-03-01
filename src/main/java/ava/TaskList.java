package ava;

import ava.task.Deadline;
import ava.task.Event;
import ava.task.Task;
import ava.task.ToDo;

import java.util.ArrayList;

public class TaskList extends ArrayList<Task> {
    public TaskList() {
        super();
    }

    public void listTask() {
        Ui.printLine();
        System.out.println("Here are the tasks in your list:");
        int noOfTask = 0;
        while (noOfTask < this.size()) {
            System.out.println((noOfTask + 1) + "." + this.get(noOfTask));
            noOfTask += 1;
        }
        Ui.printLine();
    }

    public void addTask(String task, String type) throws EmptyTaskNameException {
        task = task.replace(type, "");

        if (task.isEmpty()) {
            throw new EmptyTaskNameException();
        }

        String[] taskAndDate = task.split("/");
        taskAndDate[0] = taskAndDate[0].trim();

        switch (type) {
        case "todo":
            this.add(new ToDo(taskAndDate[0]));
            break;
        case "deadline":
            this.add(new Deadline(taskAndDate[0], taskAndDate[1]));
            break;
        case "event":
            this.add(new Event(taskAndDate[0], taskAndDate[1], taskAndDate[2]));
            break;
        }
    }

    public static int extractTaskNumber(String type, String command) {
        command = command.replace(type, "");
        return Integer.parseInt(command) - 1;
    }

    public void deleteTask(String command) {
        int taskNumberToBeDeleted;
        try {
            try {
                taskNumberToBeDeleted = extractTaskNumber("delete ", command);
                Task deletedTask = this.get(taskNumberToBeDeleted);
                Ui.printLine();
                System.out.println("Noted!!! I've removed this task:");
                System.out.println(deletedTask);
                this.remove(deletedTask);
                System.out.println("Now you have " + this.size() + " tasks in the list!!!");
                Ui.printLine();
            } catch (IndexOutOfBoundsException e) {
                Ui.printLine();
                System.out.println(" ⊙﹏⊙ Hey! You cannot delete a task that does not exist!");
                Ui.printLine();
            }
        } catch (NumberFormatException e) {
            Ui.printLine();
            System.out.println("Please tell me which one to delete? (＾＿－)");
            Ui.printLine();
        }
    }

    public void markTask(String command) {
        Ui.printLine();
        boolean isMark = true;
        int taskChanged;
        if (command.startsWith("unmark")) {
            isMark = false;
        }
        try {
            try {
                if (isMark) {
                    taskChanged = extractTaskNumber("mark ", command);
                    this.get(taskChanged).markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                } else {
                    taskChanged = extractTaskNumber("unmark ", command);
                    this.get(taskChanged).markAsNotDone();
                    System.out.println("OK, I've marked this task as not done yet:");
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println(" ⊙﹏⊙ Hey! You cannot mark a task that does not exist!");
                Ui.printLine();
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Please tell me which one to mark? (＾＿－)");
            Ui.printLine();
            return;
        }
        System.out.println(this.get(taskChanged));
        Ui.printLine();
    }
}
