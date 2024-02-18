package interactions;
import java.util.Scanner;
import interactions.Task;
import interactions.Chatbot;

public class TaskList {
    protected Task[] list;
    protected int currSize;
    protected int TOTAL_SIZE = 100;
    protected static final String INDENT = "      ";
    public TaskList() {
        currSize = 0;
        list = new Task[TOTAL_SIZE];
    }
    public void mark(String line, boolean isMark) {
        int index = Integer.parseInt(line.substring(isMark ? 5 : 7));
        Task markedTask = list[index - 1];
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
    public void printList() {
        System.out.println("Here are the tasks in your list:");
        if (currSize > 0) {
            for (int i = 0; i < currSize; i++) {
                System.out.print("      ");
                Task task = list[i];
                System.out.print(i + 1 + ".");
                task.print();
            }
        } else {
            System.out.println(INDENT + "There's nothing in this list.");
        }
    }
}
