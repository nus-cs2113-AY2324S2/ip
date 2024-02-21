import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
public class JigaChat {
    ArrayList<Task> taskList = new ArrayList<>();
    int taskCounter = 0;
    DataFile previousData = new DataFile();

    private void initialisePreviousData() {
        try {
            String input;
            Scanner readPreviousData = new Scanner(previousData.data);
            input = readPreviousData.nextLine();
            this.taskCounter = Integer.parseInt(input);
            while (readPreviousData.hasNext()) {
                input = readPreviousData.nextLine();
                readCommandWithoutPrints(input);
            }
        }
        catch(FileNotFoundException e) {
        }
    }

    public static void printCommandList() {
        printTodoCommand();
        printDeadlineCommand();
        printEventCommand();
        printListCommand();
        printDeleteCommand();
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

    private static void printDeleteCommand() {
        System.out.println("delete [task index]                         Removes a task from your list");
    }
    public void readCommand(String input) {
        String[] commands = new String[2];
        commands = input.split(" ", 2);

        switch(commands[0]) {
        case "help":
            printCommandList();
            return;
        case "bye":
            System.out.println("Bye. Hope to see you again soon!");
            System.exit(0);
        case "delete":
            try {
                removeFromList(Integer.parseInt(commands[1]) - 1);
            }
            catch(NumberFormatException e) {
                printDeleteCommand();
            }
            return;
        case "list":
            printList();
            return;
        case "mark":
            try {
                taskList.get(Integer.parseInt(commands[1]) - 1).markAsDone();
                System.out.println("JigaChat has marked task " + (Integer.parseInt(commands[1])) + " as done!");
                previousData.appendToFile(input + "\n");
            }
            catch(NullPointerException e) {
                System.out.println(commands[1] + " is not in your list!");
            }
            catch(ArrayIndexOutOfBoundsException e) {
                printMarkCommand();
            }
            catch(IOException e) {
            }
            return;
        case "unmark":
            try {
                taskList.get(Integer.parseInt(commands[1]) - 1).markAsUndone();
                System.out.println("JigaChat has marked task " + (Integer.parseInt(commands[1])) + " as not done!");
                previousData.appendToFile(input + "\n");
            }
            catch(NullPointerException e) {
                System.out.println(commands[1] + " is not in your list!");
            }
            catch(ArrayIndexOutOfBoundsException e) {
                printUnmarkCommand();
            }
            catch(IOException e) {
            }
            return;
        }
        try {
            addToList(commands);
            previousData.appendToFile(input + "\n");
        }
        catch(InvalidCommandException e) {
            System.out.println("Command " + input + " is not recognised!");
        }
        catch(ArrayIndexOutOfBoundsException e) {
            System.out.println("Type [help] to learn how to add tasks");
        }
        catch(StringIndexOutOfBoundsException e) {
            System.out.println("Type [help] to learn how to add tasks");
        }
        catch(IOException e) {
        }
    }

    public void readCommandWithoutPrints(String input) {
        String[] commands = new String[2];
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
                taskList.get(Integer.parseInt(commands[1]) - 1).markAsDoneWithoutPrints();
            }
            catch(NullPointerException e) {
            }
            catch(ArrayIndexOutOfBoundsException e) {
                printMarkCommand();
            }
            return;
        case "unmark":
            try {
                taskList.get(Integer.parseInt(commands[1]) - 1).markAsUndoneWithoutPrints();
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
            addToListWithoutPrints(commands);
        }
        catch(InvalidCommandException e) {
        }
        catch(ArrayIndexOutOfBoundsException e) {
            System.out.println("Type [help] to learn how to add tasks");
        }
        catch (StringIndexOutOfBoundsException e) {
            System.out.println("Type [help] to learn how to add tasks");
        }
    }

    public void removeFromList(int taskIndex) {
        try {
            taskList.get(taskIndex).printTask();
            System.out.println("Has been removed from your list");
            taskList.remove(taskIndex);
            taskCounter--;
            System.out.print("You have " + taskCounter + " tasks in your list");
        }
        catch(IndexOutOfBoundsException e) {
            System.out.println("Task " + taskIndex + " is not in your list!");
        }
    }

    public void addToList(String[] taskToAdd) throws InvalidCommandException {
            if (taskToAdd[0].equals("todo")) {
                    ToDo todoToAdd = new ToDo(taskToAdd[1]);
                    taskList.add(todoToAdd);
            }
            else if (taskToAdd[0].equals("deadline")) {
                String[] deadline = taskToAdd[1].split("/", 2);
                String by = deadline[1].substring(3);
                String description = deadline[0].substring(0, deadline[0].length() - 1);

                Deadline deadlineToAdd = new Deadline(description, by);
                taskList.add(deadlineToAdd);
            }
            else if (taskToAdd[0].equals("event")) {
                String[] event = taskToAdd[1].split("/", 3);
                String description = event[0].substring(0, event[0].length() - 1);
                String from = event[1].substring(5, event[1].length() - 1);
                String to = event[2].substring(3);
                Event eventToAdd = new Event(description, from, to);
                taskList.add(eventToAdd);
            }
            else {
                throw new InvalidCommandException();
            }

            System.out.println("The following task has been added: ");
            taskList.get(taskCounter).printTask();
            taskCounter++;
            try {
                String taskCounterString = taskCounter + "\n";
                previousData.appendAtFirstLine(taskCounterString);
            }
            catch(IOException e) {
            }
            System.out.print("You have " + taskCounter + " task");
            if (taskCounter != 1) {
                System.out.print("s");
            }
            System.out.println(" in your list.");
    }

    public void addToListWithoutPrints(String[] taskToAdd) throws InvalidCommandException {
        if (taskToAdd[0].equals("todo")) {
            ToDo todoToAdd = new ToDo(taskToAdd[1]);
            taskList.add(todoToAdd);
        }
        else if (taskToAdd[0].equals("deadline")) {
            String[] deadline = taskToAdd[1].split("/", 2);
            String by = deadline[1].substring(3);
            String description = deadline[0].substring(0, deadline[0].length() - 1);

            Deadline deadlineToAdd = new Deadline(description, by);
            taskList.add(deadlineToAdd);
        }
        else if (taskToAdd[0].equals("event")) {
            String[] event = taskToAdd[1].split("/", 3);
            String description = event[0].substring(0, event[0].length() - 1);
            String from = event[1].substring(5, event[1].length() - 1);
            String to = event[2].substring(3);
            Event eventToAdd = new Event(description, from, to);
            taskList.add(eventToAdd);
        }
        else {
            throw new InvalidCommandException();
        }
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
            System.out.print("Here is the task");
        }
        System.out.println(" in your list:");

        for (int i = 0; i < taskCounter; i ++) {
            System.out.print((i + 1) + ". ");
            taskList.get(i).printTask();
        }
    }
    public static void main(String[] args) {
        System.out.println("Hello! I'm JigaChat");
        System.out.println("What can I do for you? Type [help] to learn how to use JigaChat!");
        JigaChat chat = new JigaChat();
        if (!chat.previousData.data.exists()) {
            System.out.println("No existing data was found!");
            try {
                if (chat.previousData.data.createNewFile()) {
                    System.out.println("New data file created");
                    chat.previousData.writeToFile("0");
                }
                else {
                    System.out.println("File creation failed");
                }
            }
            catch(IOException e) {
            }
        }
        else {
            try {
                chat.initialisePreviousData();
                System.out.println("Data from your previous session was loaded");
            }
            catch(NoSuchElementException e) {
                try {
                System.out.println("Previous Data corrupted and will be deleted!");
                    chat.previousData.writeToFile("0");
                }
                catch(IOException ex) {
                }
            }
        }
        while (1 == 1) {
            String input;
            Scanner in = new Scanner(System.in);
            input = in.nextLine();
            chat.readCommand(input);
        }
    }
}

