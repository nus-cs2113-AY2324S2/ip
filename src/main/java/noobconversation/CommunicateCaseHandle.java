package noobconversation;

import errorhandle.ExceptionsHandle;
import errorhandle.UserInputErrorOutputHandle;
import format.Formatting;
import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

import java.util.ArrayList;

public class CommunicateCaseHandle {


    protected ExceptionsHandle exceptions;
    protected Formatting format;
    protected UserInputErrorOutputHandle userInputError;

    public CommunicateCaseHandle() {
        exceptions = new ExceptionsHandle();
        format = new Formatting();
        userInputError = new UserInputErrorOutputHandle();
    }

    public void listHandle(ArrayList<Task> list) {
        format.dividingLine();
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println("\t" + (i + 1) + "." + list.get(i).getIdentity() + list.get(i).getStatusIcon() + " " + list.get(i));
        }
        format.dividingLine();
    }

    public void taskHandle(String line, ArrayList<Task> list) {
        int spaceIndex = line.indexOf(" ");
        if (line.toLowerCase().startsWith("todo")) {
            todoHandle(line.substring(spaceIndex + 1), list);
        } else if (line.toLowerCase().startsWith("deadline")) {
            deadlineHandle(line.substring(spaceIndex + 1), list);
        } else if (line.toLowerCase().startsWith("event")) {
            eventHandle(line.substring(spaceIndex + 1), list);
        } else {
            userInputError.undefinedTaskError();
        }
    }

    public void newTaskAddedMessage(Task t, ArrayList<Task> list) {
        format.dividingLine();
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t\t" + t.getIdentity() + t.getStatusIcon() + " " + t);
        System.out.println("\tNow you have " + list.size() + " tasks in the list.");
        format.dividingLine();
    }

    public void todoHandle(String line, ArrayList<Task> list) {
        if (line.toLowerCase().startsWith("todo")) {
            userInputError.noTaskContentError("todo");
        }
        Task t = new ToDo(line.trim());
        list.add(t);
        newTaskAddedMessage(t, list);
    }

    public void deadlineHandle(String line, ArrayList<Task> list) {
        if (line.toLowerCase().startsWith("deadline")) {
            userInputError.noTaskContentError("deadline");
        }
        int byIndex = line.indexOf("/by");
        String content = line.substring(0, byIndex);
        String by = line.substring(byIndex + 3).trim();
        Task t = new Deadline(content.trim(), by.trim());
        list.add(t);
        newTaskAddedMessage(t, list);
    }

    public void eventHandle(String line, ArrayList<Task> list) {
        if (line.toLowerCase().startsWith("event")) {
            userInputError.noTaskContentError("event");
        }
        int fromIndex = line.indexOf("/from");
        String content = line.substring(0, fromIndex);
        int toIndex = line.indexOf("/to");
        String from = line.substring(fromIndex + 5, toIndex).trim();
        String to = line.substring(toIndex + 3).trim();
        Task t = new Event(content.trim(), from.trim(), to.trim());
        list.add(t);
        newTaskAddedMessage(t, list);
    }

    public void totalHandle(String line, ArrayList<Task> list, String identity) {
        int spaceIndex = line.indexOf(" ");
        if (spaceIndex == -1) {
            userInputError.noSpacingError("'" + identity + "'");
            return;
        }
        String secondPart = line.substring(spaceIndex + 1);
        if (exceptions.checkIfStringIsInteger(secondPart) == exceptions.getStringIsNotInteger()) {
            userInputError.inputNotNumberError("'" + identity + "'");
            return;
        }
        int number = Integer.parseInt(secondPart);
        if (number > list.size()) {
            userInputError.requestTaskOutOfBound();
        } else {
            format.dividingLine();
            String output = "\t\t" + list.get(number - 1).getIdentity() +
                    list.get(number - 1).getStatusIcon() + " " + list.get(number - 1);
            switch (identity) {
            case "unmark":
                System.out.println("\tOK, I've marked this task as not done yet:");
                list.get(number - 1).changeStatus(false);
                break;
            case "mark":
                System.out.println("\tNice! I've marked this task as done:");
                list.get(number - 1).changeStatus(true);
                break;
            case "delete":
                System.out.println("\tNoted. I've removed this task:");
                list.remove(number - 1);
                break;
            }
            System.out.println(output);
            if (identity.equals("delete")) {
                System.out.println("\tNow you have " + list.size() + " tasks in the list.");
            }
            format.dividingLine();
        }
    }
}
