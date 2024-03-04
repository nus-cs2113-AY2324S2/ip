package Tony.utility;

import Tony.task.Task;

import java.util.ArrayList;

public class Ui {
    private final ArrayList<Task> tasks;

    /**
     * Represents a {@code Ui} object that prints texts on program.
     * @param tasks the current <code>tasks</code> list.
     */
    public Ui(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Prints the welcome message <code>chatBot</code>.
     */
    public void printWelcomeMessage() {
        String chatBot = "Hello! I'm TONY"
                + System.lineSeparator()
                + "What can I do for you?";
        System.out.println(chatBot);
    }

    /**
     * Prints the bye message <code>byeBot</code>.
     */
    public void printByeMessage() {
        String byeBot = "Bye. Hope to see you again soon!";
        System.out.println(byeBot);
    }

    /**
     * Prints the current <code>tasks</code> list when user type "list".
     */
    public void printTaskList() {
        System.out.println("\tHere are the tasks in your list:");
        for(Task t : tasks) {
            int numbering = tasks.indexOf(t) + 1;
            System.out.println("\t" + numbering + ". "
                    + t);
        }
    }

    /**
     * Prints message when user <code>add/delete</code> a task into/from <code>tasks</code> list.
     * @param command is the <code>delete</code> command entered by user.
     * @param index is used to retrieve task from the <code>tasks</code> list based on the given index.
     */
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

    /**
     * Finds the task that contains the keyword that user input
     * then list down all matching <code>tasks</code>.
     * @param findTask is the task description after the find keyword
     */
    public void findCommand(String[] findTask) {
        String keyword = findTask[1].trim();
        System.out.println("\tHere are the matching tasks in your list:");
        int numbering = 0;
        for (Task t : tasks) {
            if(t.description.contains(keyword)) {
                System.out.println("\t" + (numbering + 1) + ". " + t);
                numbering++;
            }
        }
    }
}
