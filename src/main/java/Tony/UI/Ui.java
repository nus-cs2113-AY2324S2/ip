package Tony.UI;

import Tony.task.Task;

import java.util.ArrayList;

public class Ui {
    public static ArrayList<Task> tasks = new ArrayList<>();
    public static final String LINE_BREAKER = "__________________________________________________"
            + System.lineSeparator();
    public Ui() {

    }
    public static void printWelcomeMessage() {
        String chatBot = "Hello! I'm TONY\n"
                + "What can I do for you?\n"
                + LINE_BREAKER;
        System.out.println(chatBot);
    }

    public static void printByeMessage() {
        System.out.println(
                LINE_BREAKER
                        + "Bye. Hope to see you again soon!"
                        + System.lineSeparator()
                        + LINE_BREAKER);
    }

    public static void printTaskList() {
        System.out.println(LINE_BREAKER);
        System.out.println("\tHere are the tasks in your list:");
        for(Task t : tasks) {
            int numbering = tasks.indexOf(t) + 1;
            System.out.println("\t" + numbering + "."
                    + t);
        }
        System.out.println(LINE_BREAKER);
    }

    public static void printAddOrDeleteTask(String command, int index) {
        String deleteOrAdd = (command.equals("delete") ? "removed" : "added");
        int taskSize = (command.equals("delete") ? tasks.size() - 1  : tasks.size());
        System.out.println(
                LINE_BREAKER
                        + "\t Got it. I've " + deleteOrAdd
                        + " this task:"
                        + System.lineSeparator()
                        + "\t\t " + tasks.get(index)
                        + System.lineSeparator()
                        + "\t Now you have " + taskSize + " tasks in the list."
                        + System.lineSeparator()
                        + LINE_BREAKER);
    }
}
