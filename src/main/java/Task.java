import java.util.Scanner;

public class Task{

    private static final String LINE_SEPARATOR = "____________________________________________________________";
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public String toString() {
        String done = "[ ] ";
        if (isDone) {
            done = "[X] ";
        }
        return done + description;
    }

    public static void markTask(Task[] tasks, String userInput, int index){
        String[] split = userInput.split(" ");
        int number = Integer.parseInt(split[1]);
        if (number <= index) {
            tasks[number - 1].setDone(true);
            System.out.println(LINE_SEPARATOR);
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("[X] " + tasks[number-1].getDescription());
            System.out.println(LINE_SEPARATOR + "\n");
        } else {
            System.out.println("Task does not exist yet!\n" + LINE_SEPARATOR);
        }
    }

    public static void unmarkTask(Task[] tasks, String userInput, int index){
        String[] split = userInput.split(" ");
        int number = Integer.parseInt(split[1]);
        if (number <= index) {
            tasks[number - 1].setDone(false);
            System.out.println(LINE_SEPARATOR);
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("[ ] " + tasks[number-1].getDescription());
            System.out.println(LINE_SEPARATOR + "\n");
        } else {
            System.out.println("Task does not exist yet!\n" + LINE_SEPARATOR);
        }
    }

    public static void listTasks(Task[] tasks, int index){
        System.out.println(LINE_SEPARATOR);
        for (int i = 0; i < index; i++) {
            System.out.print(i+1 + ".");
            System.out.println(tasks[i]);
        }
    }
}