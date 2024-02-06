import java.util.Arrays;
import java.util.Scanner;

public class Task {
    protected String description;
    protected boolean isDone;
    protected char type;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.type = ' ';
    }
    public Task(String description, char type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    public String getStatusIcon() {
        return (isDone? "X" : " ");
    }

    public char getTypeIcon() {
        return type;
    }

    public void setDescription(String newOne) {
        this.description = newOne;
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
        else {
            PrintText.printWithHorizon("Index out of range");
        }
    }
}
