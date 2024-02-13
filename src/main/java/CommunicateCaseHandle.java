import java.util.Scanner;

public class CommunicateCaseHandle {
    public static final int correctTaskCreation = 66319;
    protected ExceptionsHandle exceptions;
    protected Formatting format;
    protected UserInputErrorHandle userInputError;

    public CommunicateCaseHandle() {
        exceptions = new ExceptionsHandle();
        format = new Formatting();
        userInputError = new UserInputErrorHandle();
    }

    public void listHandle(int index, Task[] list) {
        format.dividingLine();
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < index; i++) {
            System.out.println("\t" + (i + 1) + "." + list[i].getIdentity() + list[i].getStatusIcon() + " " + list[i]);
        }
        format.dividingLine();
    }

    public int taskHandle(String line, Scanner in, Task[] list, int index) {
        int spaceIndex = line.indexOf(" ");
        if (line.toLowerCase().startsWith("todo")) {
            return todoHandle(line.substring(spaceIndex + 1), list, index);
        } else if (line.toLowerCase().startsWith("deadline")) {
            return deadlineHandle(line.substring(spaceIndex + 1), list, index);
        } else if (line.toLowerCase().startsWith("event")) {
            return eventHandle(line.substring(spaceIndex + 1), list, index);
        } else {
            userInputError.undefinedTaskError();
            return -1;
        }
    }

    public void newTaskAddedMessage(Task t, int index) {
        format.dividingLine();
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t\t" + t.getIdentity() + t.getStatusIcon() + " " + t);
        System.out.println("\tNow you have " + index + " tasks in the list.");
        format.dividingLine();
    }

    public int todoHandle(String line, Task[] list, int index) {
        if (line.toLowerCase().startsWith("todo")) {
            userInputError.noTaskContentError("todo");
            return -1;
        }
        Task t = new ToDo(line.trim());
        list[index] = t;
        newTaskAddedMessage(t, index + 1);
        return correctTaskCreation;
    }

    public int deadlineHandle(String line, Task[] list, int index) {
        if (line.toLowerCase().startsWith("deadline")) {
            userInputError.noTaskContentError("deadline");
            return -1;
        }
        int byIndex = line.indexOf("/by");
        String content = line.substring(0, byIndex);
        String by = line.substring(byIndex + 3).trim();
        Task t = new Deadline(content.trim(), by.trim());
        list[index] = t;
        newTaskAddedMessage(t, index + 1);
        return correctTaskCreation;
    }

    public int eventHandle(String line, Task[] list, int index) {
        if (line.toLowerCase().startsWith("event")) {
            userInputError.noTaskContentError("event");
            return -1;
        }
        int fromIndex = line.indexOf("/from");
        String content = line.substring(0, fromIndex);
        int toIndex = line.indexOf("/to");
        String from = line.substring(fromIndex + 5, toIndex).trim();
        String to = line.substring(toIndex + 3).trim();
        Task t = new Event(content.trim(), from.trim(), to.trim());
        list[index] = t;
        newTaskAddedMessage(t, index + 1);
        return correctTaskCreation;
    }

    public void markHandle(String line, Scanner in, int index, Task[] list) {
        int spaceIndex = line.indexOf(" ");
        if (spaceIndex == -1) {
            format.dividingLine();
            System.out.println("\tPlease add a spacing between 'mark' and 'number'");
            format.dividingLine();
            return;
        }
        String secondPart = line.substring(spaceIndex + 1);
        if (exceptions.checkIfStringIsInteger(secondPart) == exceptions.getStringIsNotInteger()) {
            format.dividingLine();
            System.out.println("\tPlease type 'mark' + 'NUMBER'!");
            format.dividingLine();
            return;
        }
        int number = Integer.parseInt(line.substring(spaceIndex + 1));
        if (number > index) {
            format.dividingLine();
            System.out.println("\tOops, you do not have this task");
            format.dividingLine();
        } else {
            format.dividingLine();
            System.out.println("\tNice! I've marked this task as done:");
            list[number - 1].changeStatus(true);
            System.out.println("\t\t" + list[number - 1].getIdentity() + list[number - 1].getStatusIcon() + " " + list[number - 1]);
            format.dividingLine();
        }
    }

    public void unmarkHandle(String line, Scanner in, int index, Task[] list) {
        int spaceIndex = line.indexOf(" ");
        if (spaceIndex == -1) {
            format.dividingLine();
            System.out.println("\tPlease add a spacing between 'unmark' and 'number'");
            format.dividingLine();
            return;
        }
        String secondPart = line.substring(spaceIndex + 1);
        if (exceptions.checkIfStringIsInteger(secondPart) == exceptions.getStringIsNotInteger()) {
            format.dividingLine();
            System.out.println("\tPlease type 'unmark' + 'NUMBER'!");
            format.dividingLine();
            return;
        }
        int number = Integer.parseInt(secondPart);
        if (number > index) {
            format.dividingLine();
            System.out.println("\tOops, you do not have this task");
            format.dividingLine();
        } else {
            format.dividingLine();
            System.out.println("\tOK, I've marked this task as not done yet:");
            list[number - 1].changeStatus(false);
            System.out.println("\t\t" + list[number - 1].getIdentity() + list[number - 1].getStatusIcon() + " " + list[number - 1]);
            format.dividingLine();
        }
    }
}
