import java.util.Scanner;
public class JigaChat {
    Task[] taskList = new Task[100];
    int taskCounter = 0;

    public void printCommandList() {
        printTodoCommand();
        printDeadlineCommand();
        printEventCommand();
        printListCommand();
        printMarkCommand();
        printUnmarkCommand();
    }

    private static void printUnmarkCommand() {
        System.out.println("unmark [task index]                         Marks a task in your list as not done");
    }

    private static void printMarkCommand() {
        System.out.println("mark [task index]                           Marks a task in your list as done");
    }

    private static void printListCommand() {
        System.out.println("list                                        Displays a list of all tasks and their statuses as well as types");
    }

    private static void printEventCommand() {
        System.out.println("event [description] /from [from] /to [to]   Adds a new event to your list");
    }

    private static void printDeadlineCommand() {
        System.out.println("deadline [description] /by [by]             Adds a new deadline to your list");
    }

    private static void printTodoCommand() {
        System.out.println("todo [description]                          Adds a new todo to your list");
    }

    public void readCommand() {
        String input;
        String[] commands = new String[2];
        Scanner in = new Scanner(System.in);
        input = in.nextLine();
        commands = input.split(" ", 2);

        switch(commands[0]) {
        case "help":
            printCommandList();
            return;
        case "bye":
            System.out.println("Bye. Hope to see you again soon!");
            System.exit(0);
        case "list":
            printList();
            return;
        case "mark":
            try {
                taskList[Integer.parseInt(commands[1]) - 1].markAsDone();
                System.out.println("JigaChat has marked task " + (Integer.parseInt(commands[1])) + " as done!");
            }
            catch(NullPointerException e) {
                System.out.println(commands[1] + " is not in your list!");
            }
            catch(ArrayIndexOutOfBoundsException e) {
                printMarkCommand();
            }
            return;
        case "unmark":
            try {
                taskList[Integer.parseInt(commands[1]) - 1].markAsUndone();
                System.out.println("JigaChat has marked task " + (Integer.parseInt(commands[1])) + " as not done!");
            }
            catch(NullPointerException e) {
                System.out.println(commands[1] + " is not in your list!");
            }
            catch(ArrayIndexOutOfBoundsException e) {
                printUnmarkCommand();
            }
            return;
        }
        try {
            addToList(commands);
        }
        catch (InvalidCommandException e) {
            System.out.println("Command " + input + " is not recognised!");
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Type [help] to learn how to add tasks");
        }
        catch (StringIndexOutOfBoundsException e) {
            System.out.println("Type [help] to learn how to add tasks");
        }
    }

    public void addToList(String[] taskToAdd) throws InvalidCommandException {
            if (taskToAdd[0].equals("todo")) {
                    taskList[taskCounter] = new ToDo(taskToAdd[1]);
            }
            else if (taskToAdd[0].equals("deadline")) {
                String[] deadline = taskToAdd[1].split("/", 2);
                String by = deadline[1].substring(3);
                String description = deadline[0].substring(0, deadline[0].length() - 1);
                taskList[taskCounter] = new Deadline(description, by);
            }
            else if (taskToAdd[0].equals("event")) {
                String[] event = taskToAdd[1].split("/", 3);
                String description = event[0].substring(0, event[0].length() - 1);
                String from = event[1].substring(5, event[1].length() - 1);
                String to = event[2].substring(3);
                taskList[taskCounter] = new Event(description, from, to);
            }
            else {
                throw new InvalidCommandException();
            }

            System.out.println("The following task has been added: ");
            taskList[taskCounter].printTask();
            taskCounter++;
            System.out.print("You have " + taskCounter + " task");
            if (taskCounter != 1) {
                System.out.print("s");
            }
            System.out.println(" in your list.");
    }

    public void printList() {
        if (taskCounter == 0) {
            System.out.println ("Your list is empty!");
            return;
        }
        if (taskCounter != 1) {
            System.out.print("Here are the tasks");
        }
        else {
            System.out.println("Here is the task");
        }
        System.out.println(" in your list:");

        for (int i = 0; i < taskCounter; i ++) {
            System.out.print((i + 1) + ". ");
            taskList[i].printTask();
        }
    }
    public static void main(String[] args) {
        System.out.println("Hello! I'm JigaChat");
        System.out.println("What can I do for you? Type [help] to learn how to use JigaChat!");
        JigaChat chat = new JigaChat();
        while (1 == 1) {
            chat.readCommand();
        }
    }
}

