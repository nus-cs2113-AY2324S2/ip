package utils;

import classes.Deadline;
import classes.Event;
import classes.Task;
import classes.Todo;
import exceptions.EmptyTaskException;
import exceptions.TaskIndexOutOfBoundsException;

public class TaskManager {

    public static int add(String input, Task[] tasks, int taskCount) {
        String[] inputs;
        System.out.println(constants.BREAKLINE);
        inputs = input.split(" ", 2);
        if (inputs[0].equalsIgnoreCase("todo")) {
            tasks[taskCount] = new Todo(inputs[1]);
        } else if (inputs[0].equalsIgnoreCase("deadline")) {
            inputs = inputs[1].split("/", 2);
            if (inputs.length != 2)
            {
                System.out.println("Incorrect format of time specification.");
                System.out.println(constants.BREAKLINE);
                return taskCount;
            }
            tasks[taskCount] = new Deadline(inputs[0] + "(" + inputs[1] + ")");
        } else {
            inputs = inputs[1].split("/", 3);
            if (inputs.length != 3)
            {
                System.out.println("Incorrect format of time specification.");
                System.out.println(constants.BREAKLINE);
                return taskCount;
            }
            tasks[taskCount] = new Event(inputs[0] + "(" + inputs[1] + inputs[2] + ")");
        }
        taskCount++;
        System.out.println("Got it. I've added this task:");
        tasks[taskCount - 1].printTask();
        if (taskCount == 1) {
            System.out.println("Now you got " + taskCount + " task in the list.");
        } else {
            System.out.println("Now you got " + taskCount + " tasks in the list.");
        }
        System.out.println(constants.BREAKLINE);
        return taskCount;
    }

    public static void list(int taskCount, Task[] tasks) {
        System.out.println(constants.BREAKLINE);
        for (int j = 0; j < taskCount; j++) {
            System.out.print(j + 1 + ". ");
            tasks[j].printTask();
        }
        System.out.println(constants.BREAKLINE);
    }

    public static void mark(String[] inputs, int taskCount, Task[] tasks) throws TaskIndexOutOfBoundsException {
        int taskNumber;
        taskNumber = Integer.parseInt(inputs[1]);
        System.out.println(constants.BREAKLINE);
        if (taskNumber > 0 && taskNumber <= taskCount) {
            System.out.println("Nice! I've marked this task as done: ");
            tasks[taskNumber - 1].markAsDone();
            tasks[taskNumber - 1].printTask();
        }
        else {
            throw new TaskIndexOutOfBoundsException(taskCount);
        }
        System.out.println(constants.BREAKLINE);
    }

    public static void unmark(String[] inputs, int taskCount, Task[] tasks) throws TaskIndexOutOfBoundsException {
        int taskNumber;
        taskNumber = Integer.parseInt(inputs[1]);
        System.out.println(constants.BREAKLINE);
        if (taskNumber > 0 && taskNumber <= taskCount) {

            System.out.println("Nice! I've marked this task as undone: ");
            tasks[taskNumber - 1].markAsUndone();
            tasks[taskNumber - 1].printTask();
        }
        else {
            throw new TaskIndexOutOfBoundsException(taskCount);
        }
        System.out.println(constants.BREAKLINE);
    }

    public static void bye() {
        System.out.println(constants.BREAKLINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(constants.BREAKLINE);
    }

}
