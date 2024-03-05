package kurobot;

import java.util.ArrayList;

public class Ui {

    private final String LOGO =
            " ___   ___    ___    ___ \n"
                    + "|   |/   /   |  |   |  | \n"
                    + "|       /    |  |   |  | \n"
                    + "|   |\\   \\   |_ |___| _| \n"
                    + "|___| \\___\\    |_____|   \n";


    private final int LINE_LEN = 60;
    private final String LINE =  "-".repeat(LINE_LEN);

    public void showWelcomeMessage() {
        System.out.println(LINE);
        System.out.println("Hello! I'm KuroBot\n" + "What can I do for you?");
        System.out.println(LOGO);
        System.out.println(LINE);
    }

    public void showGoodByeMessage() {
        System.out.println(LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
        System.out.println(LOGO);
    }

    public void showInvalidCommand() {
        System.out.println(LINE);
        System.out.println("Whoops! Please enter a valid command~");
        System.out.println(LINE);
    }

    public void printTasks(ArrayList<Task> tasks) {
        System.out.println(LINE);
        System.out.println("Here are the tasks in your list:");
        for (Task task : tasks){
            System.out.println(tasks.indexOf(task)+1 + "." + task.printTask());
        }
        System.out.println(LINE);
    }

    public void showNoTaskGiven() {
        System.out.println(LINE);
        System.out.println("Hmmm.. what is it about?");
        System.out.println(LINE);
    }

    public void showNoTimingGiven() {
        System.out.println(LINE);
        System.out.println("uhoh! don't forget the time!");
        System.out.println(LINE);
    }

    public void showNoSuchTask() {
        System.out.println(LINE);
        System.out.println("there's no such task though...");
        System.out.println(LINE);
    }

    public void showNoIndexGiven() {
        System.out.println(LINE);
        System.out.println("mhmm.. which task? >.<");
        System.out.println(LINE);
    }

    public void printGivenTask(Task task, int taskNum, boolean isAdd) {
        System.out.println(LINE);
        String text = isAdd ? "Got it. I've added this task:" : "Noted. I've removed this task:";
        System.out.println(text);
        System.out.println(task.printTask());
        System.out.println("Now you have " + taskNum + " tasks in the list.");
        System.out.println(LINE);
    }

    public void printFoundTasks() {
        System.out.println(LINE);
        System.out.println("Here are the matching tasks in your list:");
    }

    public void showLine() {
        System.out.println(LINE);
    }
}
