package interactions;
import java.lang.reflect.Array;
import java.util.Scanner;
import interactions.Task;
import interactions.Chatbot;
import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> list;
    //protected int currSize;
    //protected int TOTAL_SIZE = 100;
    protected static final String INDENT = "      ";
    protected Task lastActionTask = null; // allows for 'undo' functionality

    public Task getLastActionTask() {
        return lastActionTask;
    }
    public void setLastActionTask(Task lastActionTask) {
        this.lastActionTask = lastActionTask;
    }

    public void countTasks() {
        int currSize = list.size();
        System.out.println(INDENT + "Now you have " + currSize + " task" + (currSize > 1 ? "s " : " ") + "in the list");
    }
    public TaskList() {
        //currSize = 0;
        list = new ArrayList<>();
    }
    public void mark(int index, boolean isMark) {
        Task markedTask = list.get(index - 1);
        if (markedTask.isMarked() == isMark) {
            System.out.println("This task is already set as " + (isMark ? "marked." : "unmarked."));
            return;
        }
        if (isMark) {
            System.out.println("Nice! I've marked this task as done:");
        } else {
            System.out.println("OK, I've marked this task as not done yet:");
        }
        System.out.print(INDENT);
        markedTask.setMarked(isMark);
        markedTask.print();
    }
    public void deleteTask(int index) {
        //int index = Integer.parseInt((line.substring(6)));
        System.out.println("Noted. I've removed this task:");
        System.out.print(INDENT);
        lastActionTask = list.get(index - 1);
        lastActionTask.print();
        list.remove(index - 1);
        countTasks();
    }
    public void printList() {
        System.out.println("Here are the tasks in your list:");
        if (!list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                System.out.print("      ");
                Task task = list.get(i);
                System.out.print(i + 1 + ".");
                task.print();
            }
        } else {
            System.out.println(INDENT + "There's nothing in this list.");
        }
    }
}
