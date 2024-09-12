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
        help();
        System.out.println(line + "Hello! I'm " + name
                + "\n" + "What can I do for you?\n" + "\n"
                + line);
    }

    public void showIOException() {
        System.out.println("Got an IOException error.");
    }

    public void showTasks(TaskList tasks) {
        if (Task.numOfTask == 0) {
            System.out.println("Currently the task list is empty:) Add more tasks!\n");
        }
        for (int i = 0; i < Task.numOfTask; i++) {
            System.out.println((i+1) + "." + tasks.get(i));
        }
    }

    public void showRemoveMessage(TaskList tasks, int index) {
        System.out.println(line + "Noted. I've removed this task:\n" + tasks.get(index-1) +"\n"
            + "Now you have " + (Task.numOfTask-1) + " tasks in the list.\n" + line);
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

    public void showClearFileError() {
        System.out.println("Fail to clear the file.\n");
    }

    public void showWriteFileError() {
        System.out.println("Fail to write file.\n");
    }
    public void indexOutOfBoundError() {
        System.out.println("the input index is out of bound!\n");
    }
    public void inputMismatchError() {
        System.out.println("the input command is incorrect, please try again:)\n");
    }
    public void showLoadingError() {
        System.out.println("there is a problem with loading the file.\n");
    }
    public void showLine() {
        System.out.println(line);
    }
    public void showFind(TaskList tasks) {
        showLine();
        System.out.println("Here are the matching tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i+1) + "." + tasks.get(i));
        }
        showLine();
    }

    public String readInput() {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        userInput = userInput.toLowerCase();
        return userInput;
    }

    public void help() {
        System.out.println("Duke is a task management program, you can try following commands to play with it:\n" +
                "list: list all the tasks in the task list. Example: list\n" +
                "mark [index]: mark the task with corresponding index (from 1 to N). Example: mark 1\n" +
                "unmark [index]: unmark the task with corresponding index (from 1 to N). Example: unmark 1\n" +
                "deadline [task description] /by [due date]: add an deadline task. Example: deadline return book /by Sunday\n" +
                "todo [task description]: add an todo task. Example: todo brush teeth\n" +
                "event [task description] /from [start time] /to [end time]: add an event task. Example: event project meeting /from Mon 2pm /to 4pm\n" +
                "delete [index]: delete the task with corresponding index (from 1 to N). Example: delete 1\n" +
                "bye: exit the program. Example: bye\n" +
                "help: show this help message:) Example: help\n");
    }

}
