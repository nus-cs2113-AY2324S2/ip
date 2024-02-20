package noobconversation;

import errorhandle.ExceptionsHandler;
import errorhandle.UserInputErrorOutputHandler;
import format.Formatter;
import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

import java.io.IOException;
import java.util.ArrayList;

import memory.FileAccess;

import static constant.NormalConstant.CORRECT_TASK_CREATION;
import static constant.NormalConstant.WRONG_TASK_CREATION;


public class CommunicateCaseHandler {


    protected ExceptionsHandler exceptions;
    protected Formatter formatter;
    protected UserInputErrorOutputHandler userInputError;

    public CommunicateCaseHandler() {
        exceptions = new ExceptionsHandler();
        formatter = new Formatter();
        userInputError = new UserInputErrorOutputHandler();
    }

    public void printList(ArrayList<Task> list) {
        formatter.printDividingLine();
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println("\t" + (i + 1) + "." + list.get(i).getIdentity() + list.get(i).getStatusIcon() + " " + list.get(i));
        }
        formatter.printDividingLine();
    }

    public int handleTask(String line, ArrayList<Task> list) {
        int spaceIndex = line.indexOf(" ");
        if (line.toLowerCase().startsWith("todo")) {
            return handleTodo(line.substring(spaceIndex + 1), list);
        } else if (line.toLowerCase().startsWith("deadline")) {
            return handleDeadline(line.substring(spaceIndex + 1), list);
        } else if (line.toLowerCase().startsWith("event")) {
            return handleEvent(line.substring(spaceIndex + 1), list);
        } else {
            userInputError.printUndefinedTaskError();
            return WRONG_TASK_CREATION;
        }
    }

    public void printNewTaskAddedMessage(Task t, ArrayList<Task> list) {
        formatter.printDividingLine();
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t\t" + t.getIdentity() + t.getStatusIcon() + " " + t);
        System.out.println("\tNow you have " + list.size() + " tasks in the list.");
        formatter.printDividingLine();
    }

    public int handleTodo(String line, ArrayList<Task> list) {
        if (line.toLowerCase().startsWith("todo")) {
            userInputError.printNoTaskContentError("todo");
            return WRONG_TASK_CREATION;
        }
        Task t = new ToDo(line.trim());
        list.add(t);
        printNewTaskAddedMessage(t, list);
        return CORRECT_TASK_CREATION;
    }

    public int handleDeadline(String line, ArrayList<Task> list) {
        if (line.toLowerCase().startsWith("deadline")) {
            userInputError.printNoTaskContentError("deadline");
            return WRONG_TASK_CREATION;
        }
        int byIndex = line.indexOf("/by");
        String content = line.substring(0, byIndex);
        String by = line.substring(byIndex + 3).trim();
        Task t = new Deadline(content.trim(), by.trim());
        list.add(t);
        printNewTaskAddedMessage(t, list);
        return CORRECT_TASK_CREATION;
    }

    public int handleEvent(String line, ArrayList<Task> list) {
        if (line.toLowerCase().startsWith("event")) {
            userInputError.printNoTaskContentError("event");
            return WRONG_TASK_CREATION;
        }
        int fromIndex = line.indexOf("/from");
        String content = line.substring(0, fromIndex);
        int toIndex = line.indexOf("/to");
        String from = line.substring(fromIndex + 5, toIndex).trim();
        String to = line.substring(toIndex + 3).trim();
        Task t = new Event(content.trim(), from.trim(), to.trim());
        list.add(t);
        printNewTaskAddedMessage(t, list);
        return CORRECT_TASK_CREATION;
    }

    public void handleTotal(String line, ArrayList<Task> list, String identity) {
        FileAccess fileAccess = new FileAccess();
        int spaceIndex = line.indexOf(" ");
        if (spaceIndex == -1) {
            userInputError.printNoSpacingError("'" + identity + "'");
            return;
        }
        String secondPart = line.substring(spaceIndex + 1);
        if (exceptions.checkIfStringIsInteger(secondPart) == exceptions.getStringIsNotInteger()) {
            userInputError.printInputNotNumberError("'" + identity + "'");
            return;
        }
        int number = Integer.parseInt(secondPart);
        if (number > list.size()) {
            userInputError.printRequestTaskOutOfBoundError();
        } else {
            formatter.printDividingLine();
            String output;
            switch (identity) {
            case "unmark":
                System.out.println("\tOK, I've marked this task as not done yet:");
                list.get(number - 1).changeStatus(false);
                output = "\t\t" + list.get(number - 1).getIdentity() +
                        list.get(number - 1).getStatusIcon() + " " + list.get(number - 1);
                break;
            case "mark":
                System.out.println("\tNice! I've marked this task as done:");
                list.get(number - 1).changeStatus(true);
                output = "\t\t" + list.get(number - 1).getIdentity() +
                        list.get(number - 1).getStatusIcon() + " " + list.get(number - 1);
                break;
            case "delete":
                System.out.println("\tNoted. I've removed this task:");
                output = "\t\t" + list.get(number - 1).getIdentity() +
                        list.get(number - 1).getStatusIcon() + " " + list.get(number - 1);
                try {
                    fileAccess.deleteTask(list.get(number - 1));
                } catch (IOException e) {
                    System.out.println("Can not delete your Task!!!" + e.getMessage());
                }
                list.remove(number - 1);
                break;
            default:
                output = "Corrupted";
            }
            System.out.println(output);
            if (identity.equals("delete")) {
                System.out.println("\tNow you have " + list.size() + " tasks in the list.");
            }
            formatter.printDividingLine();
        }
    }
}
