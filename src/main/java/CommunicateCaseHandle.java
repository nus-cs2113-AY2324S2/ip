import java.util.Scanner;

public class CommunicateCaseHandle {
    protected ExceptionsHandle e;

    public CommunicateCaseHandle() {
        e = new ExceptionsHandle();
    }

    public void dividingLine() {
        System.out.println("\t__________________________________________________");
    }

    public void listHandle(int index, Task[] list) {
        dividingLine();
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < index; i++) {
            System.out.println("\t" + (i + 1) + "." + list[i].getIdentity() + list[i].getStatusIcon() + " " + list[i].getContent());
        }
        dividingLine();
    }

    public void taskHandle(String line, Scanner in, Task[] list, int index) {
        int spaceIndex = line.indexOf(" ");
        if (line.toLowerCase().startsWith("todo")) {
            todoHandle(line.substring(spaceIndex + 1), list, index);
        } else if (line.toLowerCase().startsWith("deadline")) {
            deadlineHandle(line.substring(spaceIndex + 1), list, index);
        } else if (line.toLowerCase().startsWith("event")) {
            eventHandle(line.substring(spaceIndex + 1), list, index);
        } else {
            dividingLine();
            System.out.println("\tPlease start with 'todo', 'deadline' or 'event'!");
            dividingLine();
            line = in.nextLine().trim();
            taskHandle(line, in, list, index);
        }
    }

    public void newTaskAddedMessage(Task t, int index) {
        dividingLine();
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t\t" + t.getIdentity() + t.getStatusIcon() + " " + t.getContent());
        System.out.println("\tNow you have " + index + " tasks in the list.");
        dividingLine();
    }

    public void todoHandle(String line, Task[] list, int index) {
        Task t = new ToDo(line);
        list[index] = t;
        newTaskAddedMessage(t, index + 1);
    }

    public void deadlineHandle(String line, Task[] list, int index) {
        int byIndex = line.indexOf("/by");
        String content = line.substring(0, byIndex);
        String by = line.substring(byIndex + 3).trim();
        Task t = new Deadline(content, by);
        list[index] = t;
        newTaskAddedMessage(t, index + 1);
    }

    public void eventHandle(String line, Task[] list, int index) {
        int fromIndex = line.indexOf("/from");
        String content = line.substring(0, fromIndex);
        int toIndex = line.indexOf("/to");
        String from = line.substring(fromIndex + 5, toIndex).trim();
        String to = line.substring(toIndex + 3).trim();
        Task t = new Event(content, from, to);
        list[index] = t;
        newTaskAddedMessage(t, index + 1);
    }

    public void markHandle(String line, Scanner in, int index, Task[] list) {
        int spaceIndex = line.indexOf(" ");
        if (spaceIndex == -1) {
            while (spaceIndex == -1) {
                dividingLine();
                System.out.println("\tPlease add a spacing between 'mark' and 'number'");
                dividingLine();
                line = in.nextLine().trim();
                spaceIndex = line.indexOf(" ");
            }
        }
        String secondPart = line.substring(spaceIndex + 1);
        if (e.checkIfStringIsInteger(secondPart) == e.getStringIsNotInteger()) {
            while (e.checkIfStringIsInteger(secondPart) == e.getStringIsNotInteger()) {
                dividingLine();
                System.out.println("\tPlease type 'mark' + 'NUMBER'!");
                dividingLine();
                line = in.nextLine().trim();
                spaceIndex = line.indexOf(" ");
                secondPart = line.substring(spaceIndex + 1);
            }
        }
        int number = Integer.parseInt(line.substring(spaceIndex + 1));
        if (number > index) {
            dividingLine();
            System.out.println("\tOops, you do not have this task");
            dividingLine();
        } else {
            dividingLine();
            System.out.println("\tNice! I've marked this task as done:");
            list[number - 1].changeStatus(true);
            System.out.println("\t\t" + list[number - 1].getIdentity() + list[number - 1].getStatusIcon() + " " + list[number - 1].getContent());
            dividingLine();
        }
    }

    public void unmarkHandle(String line, Scanner in, int index, Task[] list) {
        int spaceIndex = line.indexOf(" ");
        if (spaceIndex == -1) {
            while (spaceIndex == -1) {
                dividingLine();
                System.out.println("\tPlease add a spacing between 'unmark' and 'number'");
                dividingLine();
                line = in.nextLine().trim();
                spaceIndex = line.indexOf(" ");
            }
        }
        String secondPart = line.substring(spaceIndex + 1);
        if (e.checkIfStringIsInteger(secondPart) == e.getStringIsNotInteger()) {
            while (e.checkIfStringIsInteger(secondPart) == e.getStringIsNotInteger()) {
                dividingLine();
                System.out.println("\tPlease type 'unmark' + 'NUMBER'!");
                dividingLine();
                line = in.nextLine().trim();
                spaceIndex = line.indexOf(" ");
                secondPart = line.substring(spaceIndex + 1);
            }
        }
        int number = Integer.parseInt(secondPart);
        if (number > index) {
            dividingLine();
            System.out.println("\tOops, you do not have this task");
            dividingLine();
        } else {
            dividingLine();
            System.out.println("\tOK, I've marked this task as not done yet:");
            list[number - 1].changeStatus(false);
            System.out.println("\t\t" + list[number - 1].getIdentity() + list[number - 1].getStatusIcon() + " " + list[number - 1].getContent());
            dividingLine();
        }
    }
}
