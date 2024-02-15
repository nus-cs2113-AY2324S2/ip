import n.exceptions.EmptyTaskDescriptionException;
import n.exceptions.NoTaskTypeException;
import n.task.*;

import java.util.Scanner;
public class N {
    static Task[] taskList = new Task[100];
    static int taskCount = 0;

    public static void printLine() {
        System.out.println("    ____________________________________________________________ \n");
    }

    public static void printMessage(String message) {
        printLine();
        System.out.println("    " +message);
        printLine();
    }

    public static void printTaskList() {
        if (taskCount == 0) {
            printMessage("no tasks added, wake up pleasee!");
        } else {
            printLine();
            System.out.println("Here are the tasks in your list:");
            for(int i = 0; i < taskCount; i++) {
                System.out.println(taskList[i]);
            }
            printLine();
        }
    }

    public static void changeTaskStatus(int taskIndex, boolean newStatus) {
        String outputMessage;
        //check to ensure that task to be marked/unmarked exists in the list
        if (taskIndex < taskCount) {
            //when no change in status is required
            if (taskList[taskIndex].isDone() == newStatus) {
                //generate output message based on current task status
                outputMessage = (newStatus) ?
                        //output message if task has already been marked done
                        "Task " +taskList[taskIndex].getIndex()+ " is already completed!" :
                        //output message if task has not been marked done
                        "Task " +taskList[taskIndex].getIndex()+ " is already unmarked, complete other tasks!";
            } else { //handle a change in task status
                taskList[taskIndex].setDone(newStatus);
                //generate output message based on new task status
                outputMessage = (newStatus) ?
                        //output message when task is marked done
                        "Task " +(taskIndex + 1)+ " marked done, yay! :)" :
                        //output message when task is unmarked
                        "Okay, task " +(taskIndex + 1)+ " unmarked, let's complete it soon ~.o.~";
            }
        } else {
            //output message when task index given is invalid
            outputMessage = "Task not found :p";
        }
        printMessage(outputMessage);
    }

    public static Type filterTask(String taskDescription) throws NoTaskTypeException {
        Type taskType;
        switch (taskDescription.split(" ")[0]) {
            case "event":
                taskType = Type.Event;
                break;
            case "deadline":
                taskType = Type.Deadline;
                break;
            case "todo":
                taskType = Type.ToDo;
                break;
            default:
                throw new NoTaskTypeException();
        };
        return taskType;
    }

    public static void addToTaskList(Type taskType, String message)
            throws EmptyTaskDescriptionException {
        String taskDescription = "";
        switch(taskType) {
            case Event:
                try {
                    taskDescription = message.substring(5);
                    taskList[taskCount] = new Event(taskDescription, taskCount);
                } catch (StringIndexOutOfBoundsException e) {
                    printMessage("Wrong format for event task! \n" +
                            "    Please input in this format: \n" +
                            "    event [task] /from [start time] /to [end time]");
                    return;
                }
                break;
            case Deadline:
                try {
                    taskDescription = message.substring(8);
                    taskList[taskCount] = new Deadline(taskDescription, taskCount);
                } catch (StringIndexOutOfBoundsException e) {
                    printMessage("Wrong format for deadline task! \n" +
                            "    Please input in this format: \n" +
                            "    deadline [task] /by [deadline]");
                    return;
                } catch (EmptyTaskDescriptionException e) {
                    printMessage("uhOh! The task or deadline cannot be empty for a deadline :o\n" +
                            "    Please input in this format:\n" +
                            "    deadline [task] /by [deadline]");
                    return;
                }
                break;
            case ToDo:
                try {
                    taskDescription = message.substring(4);
                    taskList[taskCount] = new ToDo(taskDescription, taskCount);
                } catch (EmptyTaskDescriptionException e) {
                    printMessage("uhOh! The task must be specified for a todo :o\n" +
                            "    Please input in this format:\n" +
                            "    todo [task]");
                    return;
                }
                break;
        }
        taskCount ++;
        if (taskCount <= 1) {
            printMessage("Got it, " +taskType+ " task has been added:\n" + "    "
                    +taskList[taskCount - 1].toString()+
                    "\n    Now you have 1 task in the list");
        } else {
            printMessage("Got it, " +taskType+ " task has been added:\n" + "    "
                    +taskList[taskCount - 1].toString()+
                    "\n    Now you have "+taskCount+" tasks in the list");
        }
    }

    public static void addTask(String message) {
        try {
            Type taskType = filterTask(message);
            addToTaskList(taskType, message);
        } catch (NoTaskTypeException e) {
            printMessage("oHno, you did not specify your task type :O");
            return;
        } catch (EmptyTaskDescriptionException e) {
            return;
        }

    }

    public static void unmarkTask(String message) {
        try {
            int indexToUnmark = Integer.parseInt(message.split(" ")[1]);
            changeTaskStatus(indexToUnmark - 1, false);
        } catch (NumberFormatException e) {
            printMessage("invalid task!");
        } catch (IndexOutOfBoundsException e) {
            printMessage("Task number not provided, please try again ...");
        }
    }

    public static void markTask(String message) {
        try {
            int indexToMark = Integer.parseInt(message.split(" ")[1]);
            changeTaskStatus(indexToMark - 1, true);
        } catch (NumberFormatException e) {
            printMessage("invalid task!");
        } catch (IndexOutOfBoundsException e) {
            printMessage("Task number not provided, please try again ...");
        }
    }

    public static void handleMessages(Scanner in) {
        String message = in.nextLine();

        if (message.equalsIgnoreCase("bye")) {
            //print bye message
            printMessage("Bye. Hope to see you again soon!");
        } else if (message.equalsIgnoreCase("list")) {
            printTaskList();
            handleMessages(in);
        } else if (message.trim().startsWith("unmark")) {
            unmarkTask(message);
            handleMessages(in);
        } else if (message.trim().startsWith("mark")) {
            markTask(message);
            handleMessages(in);
        } else {
            addTask(message);
            handleMessages(in);
        }
    }

    public static void main(String[] args) {
        /*
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
         */

        String logo = " ____   ___\n"
                + "|    \\ |   |\n"
                + "|     \\|   |\n"
                + "|          |\n"
                + "|   |\\     |\n"
                + "|___| \\____| .chatbot :)\n";

        System.out.println("Hello from\n" + logo);

        printMessage("Hello! I'm N :) \n" + "    What can I do for you? \n");
        Scanner in = new Scanner(System.in);
        handleMessages(in);
    }
}
