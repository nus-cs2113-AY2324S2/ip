import java.util.ArrayList;
import java.util.Scanner;

public class Task {
    //Attributes
    protected TaskType taskType;
    protected String name;
    protected boolean isDone;
    private static ArrayList<Task> tasks = new ArrayList<Task>();

    //Constructors
    Task (String name) {
        this.name = name;
        isDone = false;
        taskType = TaskType.TASK;
    }

    public TaskType getTaskType () {
        return taskType;
    }

    //Methods
    public String toString () {
        return (isDone ? "[X] " : "[ ] ") + name;
    }

    public static void listTasks () {
        System.out.println(UI.LINE_SEPARATOR);
        for (int i = 0; i < tasks.size(); i++) {
            System.out.print((i + 1) + ".");
            System.out.println(tasks.get(i));
        }
        System.out.println(UI.LINE_SEPARATOR);
    }

    public static void addTask (Task task) {
        System.out.println(UI.LINE_SEPARATOR);

        switch (task.getTaskType()) {
            case DEADLINE:
            case EVENT:
            case TODO:
                System.out.println("Got it. I've added this task:");
                tasks.add(task);
                break;
        }
        System.out.println(task);

        System.out.println(UI.LINE_SEPARATOR);
    }

    public static void delete(int index) {
        boolean isValidIndex = false;
        Task deletedTask = null;
        try {
            isValidIndex = true;
            deletedTask = tasks.get(index - 1);
            tasks.remove(index - 1);
        }   catch (IndexOutOfBoundsException ioobe) {
            isValidIndex = false;
            System.out.println(UI.LINE_SEPARATOR);
            System.out.println("Index out of bound!");
            System.out.println(UI.LINE_SEPARATOR);
        }   catch (NumberFormatException nfe) {
            isValidIndex = false;
            System.out.println(UI.LINE_SEPARATOR);
            System.out.println("Invalid index!");
            System.out.println(UI.LINE_SEPARATOR);
        }
        if (isValidIndex){
            System.out.println(UI.LINE_SEPARATOR);
            System.out.println("Deleted task " + deletedTask + ".");
            System.out.println("Now you have " + tasks.size() + " task(s) left.");
            System.out.println(UI.LINE_SEPARATOR);
        }
    }

    public static void mark (int index) {
        tasks.get(index - 1).isDone = true;
        System.out.println(UI.LINE_SEPARATOR);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks.get(index - 1));
        System.out.println(UI.LINE_SEPARATOR);
    }

    public static void unmark (int index) {
        tasks.get(index - 1).isDone = false;
        System.out.println(UI.LINE_SEPARATOR);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(tasks.get(index - 1));
        System.out.println(UI.LINE_SEPARATOR);
    }

    private static boolean isValidTask (String command) {
        return command.equals("todo") || command.equals("deadline") || command.equals("event");
    }

    private static boolean isValidCommand (String command) {
        return command.equals("list") || command.equals("mark") || command.equals("unmark") || command.equals("delete")
                || isValidTask(command);
    }

    public static void responseToCommand (String command) {
        String[] commandWords = command.split(" ");
        if (!isValidCommand(commandWords[0])) {
            System.out.println(UI.LINE_SEPARATOR);
            System.out.println("Command not recognized");
            System.out.println(UI.LINE_SEPARATOR);
            return;
        }
        if (commandWords[0].equals("list")) {
            Task.listTasks();
        } else if (commandWords[0].equals("mark")) {
            mark(Integer.parseInt(commandWords[1]));
        } else if (commandWords[0].equals("unmark")) {
            unmark(Integer.parseInt(commandWords[1]));
        } else if (commandWords[0].equals("delete")){
            delete(Integer.parseInt(commandWords[1]));
        } else {
            if (Parser.isValidTaskCommand(command, commandWords)) {
                Task newTask;
                newTask = Parser.parseCommand(command);
                addTask(newTask);
            }
        }
    }
}
