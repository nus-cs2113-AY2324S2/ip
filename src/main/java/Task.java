import java.util.Arrays;
import java.util.Scanner;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone? "X" : " ");
    }

    public static void markTask(int index, String isMarked, Task[] tasks) {
        if (index >= 0 && index < tasks.length) {
            if (isMarked.equals("mark")) {
                tasks[index].isDone = true;
                String statusMark = "[" + tasks[index].getStatusIcon() + "] ";
                PrintText.printWithHorizon("Nice! I've marked this task as done:\n" +
                        statusMark + tasks[index].description);
            } else if (isMarked.equals("unmark")) {
                tasks[index].isDone = false;
                String statusMark = "[" + tasks[index].getStatusIcon() + "] ";
                PrintText.printWithHorizon("OK, I've marked this task as not done yet:\n" +
                        statusMark + tasks[index].description);
            } else {
                PrintText.printWithHorizon("unknown instruction");
            }
        }
        else if (index >= tasks.length) {
            PrintText.printWithHorizon("Index out of range");
        }
    }
    public static void printTask(Task[] tasks) {
        int index = 1;
        PrintText.print("Here are the tasks in your list:");
        for (Task task : tasks) {
            String indexPrinted = index + ".";
            String statusMark = "[" + task.getStatusIcon() + "] ";
            PrintText.print(indexPrinted + statusMark + task.description);
            index++;
        }
    }

    public static boolean hasIndex(String[] words) {
        try {
            Integer.parseInt(words[1]);
            return true;
        } catch(Exception a) {
            return false;
        }
    }

    public static void addTask(Task[] tasks) {
        int index = 0;
        while (true) {
            Scanner userInput = new Scanner(System.in);
            String text = userInput.nextLine();
            String[] words = text.split(" ");
            Task task = new Task(text);
            if (words[0].equals("bye")) {
                break;
            }
            else if (words[0].equals("list")) {
                PrintText.print(PrintText.HORIZON);
                printTask(Arrays.copyOf(tasks, index));
                PrintText.print(PrintText.HORIZON + "\n");
            }
            else if (words[0].equals("mark") || words[0].equals("unmark")) {
                int taskIndex = hasIndex(words)?
                        Integer.parseInt(words[1]) - 1 : -1;
                String isMarked = words[0].equals("mark")? "mark" : "unmark";
                markTask(taskIndex, isMarked, Arrays.copyOf(tasks, index));
            }
            else {
                tasks[index] = task;
                index++;
                PrintText.printWithHorizon("added: " + text);
            }
        }
    }
}
