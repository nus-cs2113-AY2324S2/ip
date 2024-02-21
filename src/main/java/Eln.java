
import java.util.Scanner;


public class Eln {
    private static final String LINE = "____________________________________________________________";
    private static final String ErrorLINE = "************************************************************";
    private static Task[] tasksList = new Task[100]; // max number of tasks set to 100
    private static int numOfTasks = 0;

    private static void greeting() {
        System.out.println(LINE);
        System.out.println("Hello! I'm Eln");
        System.out.println("What can I do for you?");
        System.out.println(LINE);
    }

    private static void farewell() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    private static void showList() {
        System.out.println(LINE);
        if(numOfTasks == 0) {
            System.out.println("there are no pending tasks");
        }
        for(int i = 0; i < numOfTasks; i++) {
            System.out.println((i+1) + ". " + tasksList[i].toString());
        }
        System.out.println(LINE);
    }

    private static void InvalidInput() {
        System.out.println(ErrorLINE);
        System.out.println("That doesn't really make sense to me.");
        System.out.println("Blink 3 times quick and type /help if you need assistance");
        System.out.println(ErrorLINE);
    }

    private static void printHelpMessage() {
        System.out.println(LINE);
        System.out.println("Eln sees you require some help");
        System.out.println("Eln will try his best but don't expect too much");
        System.out.println();
        System.out.println("To create a todo, type");
        System.out.println("todo (task to do)");
        System.out.println();
        System.out.println("To create a deadline, type");
        System.out.println("deadline (task to be completed) /by (by when)");
        System.out.println();
        System.out.println("To create an event, type");
        System.out.println("event (the event) /from (start date/time) /to (end date/time)");
        System.out.println();
        System.out.println("Other things you can type include list for a list of tasks and others to be added");
        System.out.println();
        System.out.println("Eln hopes that this has been of help. If not there's nothing else Eln can do.");
        System.out.println(LINE);
    }

    private static void addTask(String taskToAdd) {

        Task newTask;

        try {
            if (taskToAdd.startsWith("todo")) {
                newTask = new Todo(taskToAdd);
            } else if (taskToAdd.startsWith("deadline")) {
                newTask = new Deadline(taskToAdd);
            } else if (taskToAdd.startsWith("event")) {
                newTask = new Event(taskToAdd);
            } else {
                InvalidInput();
                return;
            }

        } catch (InvalidTodoException | InvalidDeadlineException | InvalidEventException e) {
            System.out.println(ErrorLINE);
            e.printExceptionMessage();
            System.out.println(ErrorLINE);
            return;
        }

        tasksList[numOfTasks] = newTask;
        numOfTasks++;

        //addTaskResponse
        System.out.println(LINE);
        System.out.println("The following task has been added as task number " + numOfTasks);
        System.out.println(newTask);
        System.out.println(LINE);
    }

    private static void mark(String taskToMark) {
        int taskNumToMark = Integer.parseInt(taskToMark.substring("mark".length()).trim()) - 1;
        tasksList[taskNumToMark].markAsDone();

        //markAsDone response
        System.out.println(LINE);
        System.out.println("The following task was marked");
        System.out.println(tasksList[taskNumToMark].toString());
        System.out.println(LINE);
    }

    private static void unmark(String taskToUnmark) {
        int taskNumToUnmark = Integer.parseInt(taskToUnmark.substring("unmark".length()).trim()) - 1;
        tasksList[taskNumToUnmark].markAsUndone();

        //markAsDone response
        System.out.println(LINE);
        System.out.println("The following task was unmarked");
        System.out.println(tasksList[taskNumToUnmark].toString());
        System.out.println(LINE);
    }



    private static void taskManager() {
        Scanner in = new Scanner(System.in);
        String userInput = in.nextLine(); // read user input

        while (!userInput.equalsIgnoreCase("bye")){
            if (userInput.equalsIgnoreCase("list")) {
                showList();
            } else if (userInput.startsWith("mark")) {
                mark(userInput);
            } else if (userInput.startsWith("unmark")) {
                unmark(userInput);
            } else if (userInput.equalsIgnoreCase("/help")) {
                printHelpMessage();
            } else {
                addTask(userInput);
            }
            userInput = in.nextLine();
        }

    }
    public static void main(String[] args) {
        greeting();
        taskManager(); // taskManager ends when user inputs "bye"
        farewell();
    }
}

