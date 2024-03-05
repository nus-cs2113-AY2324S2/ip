package daisy.ui;

import daisy.task.Task;

import java.util.ArrayList;

public class Ui {

    protected static String INTRO_PROMPT = "Good day! This is Daisy.\nAny task for today?";
    protected final static String EXIT_PROMPT = "Ending prompt received. Remember to keep to the deadlines!";
    protected final static String LINE_BREAK = "____________________________________";

    public Ui() {
    }

    public void setLineBreak() {
        System.out.println(LINE_BREAK);
    }

    public void sendIntro() {
        setLineBreak();
        System.out.println(INTRO_PROMPT);
        setLineBreak();
    }

    public void sendExit() {
        System.out.println(EXIT_PROMPT);
        setLineBreak();
    }

    public void listTasks(ArrayList<Task> tasks) {
        for (Task task : tasks) {
            System.out.println((tasks.indexOf(task) + 1) + "." + task);
        }
    }

    public void printTask(Task task) {
        System.out.println(task);
    }

    public void printMarked(Task task) {
        System.out.println("Congrats on completing the task!");
        printTask(task);
    }

    public void printUnmarked(Task task) {
        System.out.println("More time needed for the following task? Sure!");
        printTask(task);
    }

    public void printTodoMissingError() {
        System.out.println("Error! No event detected for todo. Try again!");
    }

    public void printDeadlineMissingError() {
        System.out.println("Error! No event detected for deadline. Try again!");
    }

    public void printDeadlineInputError() {
        System.out.println("Error! Deadline entry is not following format. Try again!");
    }

    public void printEventMissingError() {
        System.out.println("Error! No event detected for event. Try again!");
    }

    public void printEventInputError() {
        System.out.println("Error! Event entry is not following format. Try again!");
    }

    public void printInvalidCommandError() {
        System.out.println("Your input does not match any of my programs! Try again!");
    }

}
