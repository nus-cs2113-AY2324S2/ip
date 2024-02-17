package interactions;
import java.util.Scanner;
import interactions.Task;
import interactions.Chatbot;

public class TaskList {
    protected Task[] list;
    protected int currSize;
    protected int TOTAL_SIZE = 100;
    public TaskList() {
        currSize = 0;
        list = new Task[TOTAL_SIZE];
    }
    public void mark(String line, boolean isMark) {
        if (isMark) {
            System.out.println("Nice! I've marked this task as done:");
        } else {
            System.out.println("OK, I've marked this task as not done yet:");
        }
        System.out.print("      ");
        int index = Integer.parseInt(line.substring(isMark ? 5 : 7));
        Task markedTask = list[index - 1];
        markedTask.setMarked(isMark);
        markedTask.print();
    }
    public void printList() {
        System.out.println("Here's the list below");
        if (currSize > 0) {
            for (int i = 0; i < currSize; i++) {
                System.out.print("      ");
                Task task = list[i];
                System.out.print(i + 1 + ".");
                task.print();
            }
        } else {
            System.out.println("There's nothing in this list.");
        }
    }
    public void addNewTask(String line) {
        Task newTask = new Task(line);
        list[currSize++] = newTask;
        System.out.println("added: " + line.trim());
    }
    // to be used in main code
    public static void main(String[] args) {
        Chatbot bot = new Chatbot();
        bot.greet();
        TaskList list = new TaskList();
        String line;
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.print("YOU: ");
            line = in.nextLine();
            System.out.print(bot.name + ": ");
            if (line.equals("bye")) {
                break;
            }
            if (line.equals("list")) {
                list.printList();
            }
            else if (line.startsWith("mark")){
                list.mark(line, true);
            }
            else if (line.startsWith("unmark")) {
                list.mark(line, false);
            } else {
                list.addNewTask(line);
            }
        }
        bot.exit();
    }
}
