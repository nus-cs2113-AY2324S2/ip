package utils;

import classes.Deadline;
import classes.Event;
import classes.Task;
import classes.Todo;
import exceptions.TaskIndexOutOfBoundsException;

import java.util.ArrayList;

public class TaskManager {

    public static int add(String input, ArrayList<Task> tasks, int taskCount) {
        String[] inputs;
        System.out.println(constants.BREAKLINE);
        inputs = input.split(" ", 2);
        if (inputs[0].equalsIgnoreCase("todo")) {
            tasks.add(taskCount, new Todo(inputs[1]));
        } else if (inputs[0].equalsIgnoreCase("deadline")) {
            inputs = inputs[1].split("/", 2);
            if (inputs.length != 2)
            {
                System.out.println("Incorrect format of time specification.");
                System.out.println(constants.BREAKLINE);
                return taskCount;
            }
            tasks.add(taskCount, new Deadline(inputs[0] + "(" + inputs[1] + ")"));
        } else {
            inputs = inputs[1].split("/", 3);
            if (inputs.length != 3)
            {
                System.out.println("Incorrect format of time specification.");
                System.out.println(constants.BREAKLINE);
                return taskCount;
            }
            tasks.add(taskCount, new Event(inputs[0] + "(" + inputs[1] + inputs[2] + ")"));
        }
        taskCount++;
        System.out.println("Got it. I've added this task:");
        tasks.get(taskCount - 1).printTask();
        if (taskCount == 1) {
            System.out.println("Now you got " + taskCount + " task in the list.");
        } else {
            System.out.println("Now you got " + taskCount + " tasks in the list.");
        }
        System.out.println(constants.BREAKLINE);
        return taskCount;
    }

    public static void list(int taskCount, ArrayList<Task> tasks) {
        System.out.println(constants.BREAKLINE);
        if (taskCount == 0) {
            System.out.println("You have no tasks in the list.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int j = 0; j < taskCount; j++) {
                System.out.print(j + 1 + ". ");
                tasks.get(j).printTask();
            }
        }
        System.out.println(constants.BREAKLINE);
    }

    public static void mark(String[] inputs, int taskCount, ArrayList<Task> tasks) throws TaskIndexOutOfBoundsException {
        int taskNumber;
        taskNumber = Integer.parseInt(inputs[1]);
        System.out.println(constants.BREAKLINE);
        if (taskNumber > 0 && taskNumber <= taskCount) {
            System.out.println("Nice! I've marked this task as done: ");
            tasks.get(taskNumber - 1).markAsDone();
            tasks.get(taskNumber - 1).printTask();
        }
        else {
            throw new TaskIndexOutOfBoundsException(taskCount);
        }
        System.out.println(constants.BREAKLINE);
    }

    public static void unmark(String[] inputs, int taskCount, ArrayList<Task> tasks) throws TaskIndexOutOfBoundsException {
        int taskNumber;
        taskNumber = Integer.parseInt(inputs[1]);
        System.out.println(constants.BREAKLINE);
        if (taskNumber > 0 && taskNumber <= taskCount) {

            System.out.println("Nice! I've marked this task as undone: ");
            tasks.get(taskNumber - 1).markAsUndone();
            tasks.get(taskNumber - 1).printTask();
        }
        else {
            throw new TaskIndexOutOfBoundsException(taskCount);
        }
        System.out.println(constants.BREAKLINE);
    }

    public static int delete(String[] inputs, int taskCount, ArrayList<Task> tasks) throws TaskIndexOutOfBoundsException {
        int taskNumber;
        taskNumber = Integer.parseInt(inputs[1]);
        System.out.println(constants.BREAKLINE);
        if (taskNumber > 0 && taskNumber <= taskCount) {
            System.out.println("Noted. I've removed this task: ");
            tasks.get(taskNumber - 1).printTask();
            tasks.remove(taskNumber - 1);
            taskCount--;
            if (taskCount == 1) {
                System.out.println("Now you got " + taskCount + " task in the list.");
            } else {
                System.out.println("Now you got " + taskCount + " tasks in the list.");
            }
        }
        else {
            throw new TaskIndexOutOfBoundsException(taskCount);
        }
        System.out.println(constants.BREAKLINE);
        return taskCount;
    }

    public static void bye() {
        System.out.println(constants.BREAKLINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(constants.BREAKLINE);
    }

}
