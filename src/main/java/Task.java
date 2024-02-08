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

    public void setDescription(String newDescription) {
        this.description = newDescription;
    }

    public static void markTask(int index, String isMarked, Task[] tasks) {
        try {
            PrintText.print(tasks[index].getStatusIcon());
        } catch(ArrayIndexOutOfBoundsException e) {
            PrintText.printWithLinebreak("Index out of range");
            return;
        }

        if (isMarked.equals("mark")) {
            tasks[index].isDone = true;
            String statusMark = "[" + tasks[index].getStatusIcon() + "] ";
            PrintText.printWithLinebreak("Nice! I've marked this task as done:\n" +
                    statusMark + tasks[index].description);
        } else if (isMarked.equals("unmark")) {
            tasks[index].isDone = false;
            String statusMark = "[" + tasks[index].getStatusIcon() + "] ";
            PrintText.printWithLinebreak("OK, I've marked this task as not done yet:\n" +
                    statusMark + tasks[index].description);
        } else {
            PrintText.printWithLinebreak("unknown instruction");
        }
    }
}
