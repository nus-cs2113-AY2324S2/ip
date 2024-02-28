package Tony.utility;

import Tony.task.Task;

import java.util.ArrayList;

public class Ui {
    private final ArrayList<Task> tasks;

    public Ui(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void printWelcomeMessage() {
        String chatBot = "Hello! I'm TONY"
                + System.lineSeparator()
                + "What can I do for you?";
        System.out.println(chatBot);
    }

    public void printByeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void printTaskList() {
        System.out.println("\tHere are the tasks in your list:");
        for(Task t : tasks) {
            int numbering = tasks.indexOf(t) + 1;
            System.out.println("\t" + numbering + ". "
                    + t);
        }
    }

    public void printAddOrDeleteTask(String command, int index) {
        String deleteOrAdd = (command.equals("delete") ? "removed" : "added");
        int taskSize = (command.equals("delete") ? tasks.size() - 1  : tasks.size());
        System.out.println("\t Got it. I've " + deleteOrAdd
                + " this task:"
                + System.lineSeparator()
                + "\t\t " + tasks.get(index)
                + System.lineSeparator()
                + "\t Now you have " + taskSize + " tasks in the list.");
    }
}
