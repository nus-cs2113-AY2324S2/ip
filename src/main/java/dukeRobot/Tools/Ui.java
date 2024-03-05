package dukeRobot.Tools;

import dukeRobot.Tasks.Task;

import java.util.Scanner;

public class Ui {
    private static String line;
    private static String logo;
    private static String name;


    public Ui (){
        line = "____________________________________________________________\n";
        logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        name = "Duck";

    }
    public void showWelcome() {
        System.out.println("Hello from\n" + logo);
        System.out.println(line + "Hello! I'm " + name
                + "\n" + "What can I do for you?\n" + "\n"
                + line);
    }

    public void showIOException() {
        System.out.println("Got an IOException error.");
    }

    public void showTasks(TaskList tasks) {
        for (int i = 0; i < Task.numOfTask; i++) {
            System.out.println((i+1) + "." + tasks.get(i));
        }
    }

    public void showRemoveMessage(TaskList tasks, int index) {
        System.out.println(line + "Noted. I've removed this task:\n" + tasks.get(index-1) +"\n"
            + "Now you have " + Task.numOfTask + " tasks in the list.\n" + line);
    }

    public void emptyByDeadlineError() {
        System.out.println("OOPS!!! The by of a deadline cannot be empty.");
        System.out.println(line);
    }

    public void emptyDescriptionByDeadlineError() {
        System.out.println("OOPS!!! The description and by of a deadline cannot be empty.");
        System.out.println(line);
    }

    public void emptyDescriptionError() {
        System.out.println("OOPS!!! The description cannot be empty.");
        System.out.println(line);
    }

    public void notUnderstandError() {
        System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
        System.out.println(line);
    }
    public void showAddedMessage(TaskList tasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks.get(Task.numOfTask - 1));
        System.out.println("Now you have " + Task.numOfTask + " tasks in the list.");
    }

    public void showByeMessage() {
        System.out.println(line + "Bye. Hope to see you again soon!\n"
                + "\n" + line);
    }
    public void indexOutOfBoundError() {
        System.out.println("the input index is out of bound!\n");
    }
    public void inputMismatchError() {
        System.out.println("the input type is incorrect\n");
    }
    public void showLoadingError() {
        System.out.println("there is a problem with loading the file.\n");
    }
    public void showLine() {
        System.out.println(line);
    }

    public String readInput() {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        userInput = userInput.toLowerCase();
        return userInput;
    }

}
