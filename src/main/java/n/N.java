package n;

import n.exceptions.EmptyTaskDescriptionException;
import n.exceptions.NoTaskTypeException;
import n.task.Deadline;
import n.task.ToDo;
import n.task.Type;
import n.task.Task;
import n.task.Event;

import java.io.FileNotFoundException;
import java.nio.file.LinkOption;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

import java.io.IOException;
import java.nio.file.Files;
import java.io.File;
import java.nio.file.Path;

public class N {
    public static final int MAX_LIST_ITEMS = 100;
    public static final String NO_TASK_TYPE_ERROR = "oHno, you did not specify your task type :O";
    public static final String NO_TASK_INDEX_ERROR = "Task number not provided, please try again ...";
    public static final String INVALID_TASK_INDEX_ERROR = "invalid task!";
    public static final String INPUT_INSTRUCTION = "    Please input in this format:\n";
    public static final String TODO_INPUT_FORMAT = "    todo [task]";
    public static final String EVENT_INPUT_FORMAT = "    event [task] /from [start time] /to [end time]";
    public static final String DEADLINE_INPUT_FORMAT = "    deadline [task] /by [deadline]";
    public static final String TASK_INDEX_OUT_OF_BOUNDS_ERROR = "Task not found :p";
    public static final String SAVE_AS_FILE_ERROR = "Ohno, error occurred when saving file :/";
    public static final String FILENAME = "n.txt";
    public static final String LOGO = " ____   ___\n"
            + "|    \\ |   |\n"
            + "|     \\|   |\n"
            + "|          |\n"
            + "|   |\\     |\n"
            + "|___| \\____| .chatbot :)";

    private static ArrayList<Task> taskList = new ArrayList<Task>();
   // static Task[] taskList = new Task[MAX_LIST_ITEMS];
    public static void printLine() {
        System.out.println("    ____________________________________________________________\n");
    }

    public static void printMessage(String message) {
        printLine();
        System.out.println("    " +message);
        printLine();
    }

    public static void printTaskList() {
        if (taskList.isEmpty()) {
            printMessage("no tasks added, wake up pleasee!");
        } else {
            printLine();
            System.out.println("    Here are the tasks in your list:");
            for (Task task : taskList) {
                System.out.println("    " + task);
            }
            printLine();
        }
    }

    public static void changeTaskStatus(int taskIndex, boolean newStatus) {
        String outputMessage;
        //check to ensure that task to be marked/unmarked exists in the list
        if (taskIndex < taskList.size()) {
            //when no change in status is required
            if (taskList.get(taskIndex).isDone() == newStatus) {
                //generate output message based on current task status
                outputMessage = (newStatus) ?
                        //output message if task has already been marked done
                        "Task " +taskList.get(taskIndex).getIndex()+ " is already completed!" :
                        //output message if task has not been marked done
                        "Task " +taskList.get(taskIndex).getIndex()+ " is already unmarked, complete other tasks!";
            } else { //handle a change in task status
                taskList.get(taskIndex).setDone(newStatus);
                //generate output message based on new task status
                outputMessage = (newStatus) ?
                        //output message when task is marked done
                        "Task " +(taskIndex + 1)+ " marked done, yay! :)" :
                        //output message when task is unmarked
                        "Okay, task " +(taskIndex + 1)+ " unmarked, let's complete it soon ~.o.~";
            }
        } else {
            outputMessage = TASK_INDEX_OUT_OF_BOUNDS_ERROR;
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
                    taskList.add(new Event(taskDescription, taskList.size()));
                } catch (StringIndexOutOfBoundsException e) {
                    printMessage("Wrong format for event task! \n" +
                            INPUT_INSTRUCTION +
                            EVENT_INPUT_FORMAT);
                    return;
                }
                break;
            case Deadline:
                try {
                    taskDescription = message.substring(8);
                    taskList.add(new Deadline(taskDescription, taskList.size()));
                } catch (StringIndexOutOfBoundsException e) {
                    printMessage("Wrong format for deadline task! \n" +
                            INPUT_INSTRUCTION +
                            DEADLINE_INPUT_FORMAT);
                    return;
                } catch (EmptyTaskDescriptionException e) {
                    printMessage("uhOh! The task or deadline cannot be empty for a deadline :o\n" +
                            INPUT_INSTRUCTION +
                            DEADLINE_INPUT_FORMAT);
                    return;
                }
                break;
            case ToDo:
                try {
                    taskDescription = message.substring(4);
                    taskList.add(new ToDo(taskDescription, taskList.size()));
                } catch (EmptyTaskDescriptionException e) {
                    printMessage("uhOh! The task must be specified for a todo :o\n" +
                            INPUT_INSTRUCTION +
                            TODO_INPUT_FORMAT);
                    return;
                }
                break;
        }
        if (taskList.size() <= 1) {
            printMessage("Got it, " +taskType+ " task has been added:\n" + "    "
                    +taskList.get(taskList.size() - 1).toString()+
                    "\n    Now you have 1 task in the list");
        } else {
            printMessage("Got it, " +taskType+ " task has been added:\n" + "    "
                    +taskList.get(taskList.size() - 1).toString()+
                    "\n    Now you have "+taskList.size()+" tasks in the list");
        }
    }

    public static void addTask(String message) {
        try {
            Type taskType = filterTask(message);
            addToTaskList(taskType, message);
        } catch (NoTaskTypeException e) {
            printMessage(NO_TASK_TYPE_ERROR);
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
            printMessage(INVALID_TASK_INDEX_ERROR);
        } catch (IndexOutOfBoundsException e) {
            printMessage(NO_TASK_INDEX_ERROR);
        }
    }

    public static void markTask(String message) {
        try {
            int indexToMark = Integer.parseInt(message.split(" ")[1]);
            changeTaskStatus(indexToMark - 1, true);
        } catch (NumberFormatException e) {
            printMessage(INVALID_TASK_INDEX_ERROR);
        } catch (IndexOutOfBoundsException e) {
            printMessage(NO_TASK_INDEX_ERROR);
        }
    }

    public static void deleteTask(String message) {
        try {
            int indexToDelete = Integer.parseInt(message.split(" ")[1]) - 1;

            if (indexToDelete < taskList.size()) {
                printMessage("Noted, I have removed the following task:\n" +
                        "    " +taskList.get(indexToDelete).toString()+ "\n" +
                        "    Number of Tasks Remaining: " +taskList.size());
                taskList.remove(indexToDelete);
            } else {
                printMessage(TASK_INDEX_OUT_OF_BOUNDS_ERROR);
            }

        } catch (NumberFormatException e) {
            printMessage(INVALID_TASK_INDEX_ERROR);
        } catch (IndexOutOfBoundsException e) {
            printMessage(NO_TASK_INDEX_ERROR);
        }

    }
    public static void saveTaskList() {
        Path filePath = Path.of("./src/Data/n.txt");
        try {
            Files.deleteIfExists(filePath);
            Files.createFile(filePath);
            Files.write(filePath, ("Task List:\n").getBytes(), StandardOpenOption.APPEND);
            for (Task task : taskList) {
                // Write each task into the text file
                Files.write(filePath, (task.toString()+"\n").getBytes(), StandardOpenOption.APPEND);
            }
            Files.write(filePath, ("Number of Tasks: " +taskList.size()).getBytes(), StandardOpenOption.APPEND);
            printMessage("Your Task List has been saved, find it at "+filePath);
        } catch (IOException e) {
            System.out.println(SAVE_AS_FILE_ERROR);
            e.printStackTrace();
        }
    }
    public static void getToDoTask(String taskDescription) throws EmptyTaskDescriptionException {
        String task = taskDescription.substring(11);
        taskList.add(new ToDo(task,taskList.size()));
        //mark task if task has been done
        if (taskDescription.charAt(8) == 'X') {
            taskList.get(taskList.size() - 1).setDone(true);
        }
    }
    public static void getEventTask(String taskDescription) {
        int fromIndex = taskDescription.indexOf("(from:");
        int toIndex = taskDescription.indexOf("to:");
        String task = taskDescription.substring(11, fromIndex).trim();
        String fromTime = taskDescription.substring(fromIndex + 6, toIndex);
        String toTime = taskDescription.substring(toIndex + 3, taskDescription.length() - 1);
        taskList.add(new Event(task + " /from " + fromTime + " /to " +toTime, taskList.size()));
        //mark task if task has been done
        if (taskDescription.charAt(8) == 'X') {
            taskList.get(taskList.size() - 1).setDone(true);
        }
    }
    public static void getDeadlineTask(String taskDescription) throws EmptyTaskDescriptionException {
        int deadlineIndex = taskDescription.indexOf("(by");
        String task = taskDescription.substring(11, deadlineIndex);
        String deadline = taskDescription.substring(deadlineIndex + 4, taskDescription.length() - 1).trim();
        taskList.add(new Deadline(task + " /by " + deadline, taskList.size()));
        if (taskDescription.charAt(8) == 'X') {
            taskList.get(taskList.size() - 1).setDone(true);
        }
    }
    public static void loadTaskList() throws FileNotFoundException, EmptyTaskDescriptionException {
        if (Files.exists(Path.of("./src/Data/n.txt"), LinkOption.NOFOLLOW_LINKS)) {
            File initialTaskList = new File("./src/Data/n.txt");
            Scanner s = new Scanner(initialTaskList);
            while (s.hasNextLine()){
                String taskDescription = s.nextLine();
                if (taskDescription.equals("Task List:") || taskDescription.startsWith("Number of Tasks")) {
                    continue;
                }
                char taskTypeSymbol = taskDescription.charAt(4);
                Type taskType = Type.ToDo;
                // switch to determine task type for each task in the list
                switch (taskTypeSymbol) {
                    case ('T'):
                        taskType = Type.ToDo;
                        break;
                    case ('D'):
                        taskType = Type.Deadline;
                        break;
                    case('E'):
                        taskType = Type.Event;
                        break;
                }
                // switch to create task based to task type
                switch (taskType) {
                    case ToDo:
                        getToDoTask(taskDescription.substring(11));
                        break;
                    case Event:
                        getEventTask(taskDescription.substring(11));
                        break;
                    case Deadline:
                        getDeadlineTask(taskDescription.substring(11));
                        break;
                }
            }
            s.close();
        }
    }

    public static void handleMessages(Scanner in) {
        String message = in.nextLine();

        if (message.startsWith("bye")) {
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
        } else if (message.trim().startsWith("delete")) {
            deleteTask(message);
            handleMessages(in);
        } else if (message.trim().equalsIgnoreCase("save")) {
            saveTaskList();
            handleMessages(in);
        } else {
            addTask(message);
            handleMessages(in);
        }
    }

    public static void main(String[] args) throws FileNotFoundException, EmptyTaskDescriptionException {
        System.out.println("Hello from\n" + LOGO);
        printMessage("Hello! I'm N :) \n" + "    What can I do for you?");
        loadTaskList();
        Scanner in = new Scanner(System.in);
        handleMessages(in);
    }
}
