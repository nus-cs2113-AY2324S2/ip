import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Tony {
    public static final String LINE_BREAKER = "__________________________________________________"
            + System.lineSeparator();
    protected static Task[] tasks = new Task[100];
    public static int taskCounter = 0;
    public static void main(String[] args) throws IOException {
        printWelcomeMessage();
        FileLoader.checkFileExists();

        Scanner userInput = new Scanner(System.in);
        while(userInput.hasNextLine()) {
            String line = userInput.nextLine();
            if(line.equals("bye")){
                printByeMessage();
                return;
            } else if(line.equals("list")) {
                listTasks(tasks);
            } else if(line.contains("mark")) {
                try {
                    String[] subCommand = line.split(" ");
                    int num = Integer.parseInt(subCommand[1]);
                    checkNumberWithinRange(num);
                    markTasks(subCommand, num, tasks);
                } catch (NumberFormatException nfe) {
                    System.out.println("Suggest only number after 'mark'!");
                } catch (TonyException e) {
                    System.out.println("To mark a task, suggest a number available in the list!");
                }
            } else if(line.startsWith("todo") || line.startsWith("deadline")
                    || line.startsWith("event")) {
                try {
                    addATask(line, tasks);
                } catch (TonyException e) {
                    System.out.println(LINE_BREAKER + "OOPS!! The description of " + line
                            + " cannot be empty." + System.lineSeparator() + LINE_BREAKER);
                }
            } else {
                System.out.println("Please begin input with the following words: "
                        + System.lineSeparator()
                        + "'todo / deadline / event / mark'");
            }
        }
    }
    private static void listTasks(Task[] tasks) {
        System.out.println(LINE_BREAKER);
        System.out.println("\tHere are the tasks in your list:");
        for(int i = 0; i < tasks.length; i++) {
            Task task = tasks[i];
            System.out.println("\t" + (i+1) + "."
                    + task);
        }
        System.out.println(LINE_BREAKER);
    }

    private static void checkNumberWithinRange(int num) throws TonyException {
        if(num > tasks.length) {
            throw new TonyException();
        }
    }
    private static void markTasks(String[] subCommand, int num, Task[] tasks) {

        if(subCommand[0].equals("mark")) {
            tasks[num -1].markDone();
            System.out.println(
                    LINE_BREAKER
                        + "\tNice! I've marked this task as done:"
                        + System.lineSeparator() + tasks[num -1] + System.lineSeparator()
                        + LINE_BREAKER);
        } else {
            tasks[num -1].markNotDone();
            System.out.println(
                    LINE_BREAKER
                        + "\tOK, I've marked this task as not done yet:"
                        + System.lineSeparator() + tasks[num -1] + System.lineSeparator()
                        + LINE_BREAKER);
        }
    }
    private static void printByeMessage() {
        System.out.println(
                LINE_BREAKER
                        + "Bye. Hope to see you again soon!"
                        + System.lineSeparator()
                        + LINE_BREAKER);
    }
    private static void printWelcomeMessage() {
        String chatBot = "Hello! I'm TONY\n"
                + "What can I do for you?\n"
                + LINE_BREAKER;
        System.out.println(chatBot);
    }
    private static void addATask(String userInput, Task[] allTasks) throws TonyException, IOException {
        if(userInput.startsWith("todo")) {
            String[] toDoTask = userInput.split("todo");
            checkArrayLength(userInput, toDoTask);
            addTodoTask(allTasks, toDoTask);
        } else if(userInput.startsWith("deadline")) {
            String[] deadlineTask = userInput.split("deadline");
            checkArrayLength(userInput, deadlineTask);
            addDeadlineTask(allTasks, deadlineTask);
        } else if(userInput.startsWith("event")) {
            String[] eventTask = userInput.split("event");
            checkArrayLength(userInput, eventTask);
            addEventTask(allTasks, eventTask);
        }
    }
    private static void checkArrayLength(String userInput, String[] toDoTask) throws TonyException {
        if(toDoTask.length != 2) {
            throw new TonyException();
        }
    }
    private static void addEventTask(Task[] allTasks, String[] eventTask) throws IOException {
        String[] description = eventTask[1].split("/from | /to");
        Event event = new Event(description[0], description[1], description[2]);
        allTasks[taskCounter] = event;
        printAddNewTask(allTasks);
        FileSaver.saveEvent(event);
    }
    private static void addDeadlineTask(Task[] allTasks, String[] deadlineTask) throws IOException {
        String[] description = deadlineTask[1].split("/by");
        Deadline deadline = new Deadline(description[0], description[1]);
        allTasks[taskCounter] = deadline;
        printAddNewTask(allTasks);
        FileSaver.saveDeadline(deadline);
    }
    private static void addTodoTask(Task[] allTasks, String[] toDoTask) throws IOException {
        Todo todo = new Todo(toDoTask[1]);
        allTasks[taskCounter] = todo;
        printAddNewTask(allTasks);
        FileSaver.saveTodo(todo);
    }
    private static void printAddNewTask(Task[] allTasks) {
        taskCounter++;
        System.out.println(
                LINE_BREAKER
                    + "\t Got it. I've added this task:"
                    + System.lineSeparator()
                    + "\t\t " + allTasks[taskCounter-1]
                    + System.lineSeparator()
                    + "\t Now you have " + taskCounter + " tasks in the list."
                    + System.lineSeparator()
                    + LINE_BREAKER);
    }
}