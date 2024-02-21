import java.util.ArrayList;
import java.io.IOException;
import java.util.Scanner;

public class Tony {
    public static final String LINE_BREAKER = "__________________________________________________"
            + System.lineSeparator();
    public static ArrayList<Task> tasks = new ArrayList<>();

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
                listTasks();
            } else if(line.contains("mark")) {
                try {
                    String[] subCommand = line.split(" ");
                    int num = Integer.parseInt(subCommand[1]);
                    checkNumberWithinRange(num);
                    markTasks(subCommand, num);
                } catch (NumberFormatException nfe) {
                    System.out.println("Suggest only number after 'mark'!");
                } catch (TonyException e) {
                    System.out.println("To mark a task, suggest a number available in the list!");
                }
            } else if(line.startsWith("todo") || line.startsWith("deadline")
                    || line.startsWith("event")) {
                try {
                    addATask(line);
                } catch (TonyException e) {
                    System.out.println(LINE_BREAKER + "OOPS!! The description of " + line
                            + " cannot be empty." + System.lineSeparator() + LINE_BREAKER);
                }
            } else if(line.startsWith("delete")) {
                try {
                    String[] subCommand = line.split(" ");
                    int num = Integer.parseInt(subCommand[1]);
                    System.out.println(num);
                    checkNumberWithinRange(num);
                    deleteATask(subCommand[0], num);
                } catch (NumberFormatException nfe) {
                    System.out.println("Suggest only number after 'delete'!");
                } catch (TonyException e) {
                    System.out.println("To delete a task, suggest a number available in the list!");
                }
            }else {
                System.out.println("Please begin input with the following words: "
                        + System.lineSeparator()
                        + "'todo / deadline / event / delete / mark'");
            }
        }
    }

    private static void deleteATask(String subCommand, int num) throws IOException {
        printAddOrDeleteTask(subCommand, num-1);
        tasks.remove(num-1);
        FileSaver.updateFile();
    }

    private static void listTasks() {
        System.out.println(LINE_BREAKER);
        System.out.println("\tHere are the tasks in your list:");
        for(Task t : tasks) {
            int numbering = tasks.indexOf(t) + 1;
            System.out.println("\t" + numbering + "."
                    + t);
        }
        System.out.println(LINE_BREAKER);
    }

    private static void checkNumberWithinRange(int num) throws TonyException {
        if(num > tasks.size()) {
            throw new TonyException();
        }
    }
    private static void markTasks(String[] subCommand, int num) throws IOException {
        if(subCommand[0].equals("mark")) {
            tasks.get(num-1).markDone();
            System.out.println(
                    LINE_BREAKER
                        + "\tNice! I've marked this task as done:"
                        + System.lineSeparator() + tasks.get(num-1) + System.lineSeparator()
                        + LINE_BREAKER);
        } else {
            tasks.get(num-1).markNotDone();
            System.out.println(
                    LINE_BREAKER
                        + "\tOK, I've marked this task as not done yet:"
                        + System.lineSeparator() + tasks.get(num-1) + System.lineSeparator()
                        + LINE_BREAKER);
        }
        FileSaver.updateFile();
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
    private static void addATask(String userInput) throws TonyException, IOException {
        if(userInput.startsWith("todo")) {
            String[] toDoTask = userInput.split("todo");
            checkArrayLength(toDoTask);
            addTodoTask(toDoTask);
        } else if(userInput.startsWith("deadline")) {
            String[] deadlineTask = userInput.split("deadline");
            checkArrayLength(deadlineTask);
            addDeadlineTask(deadlineTask);
        } else if(userInput.startsWith("event")) {
            String[] eventTask = userInput.split("event");
            checkArrayLength(eventTask);
            addEventTask(eventTask);
        }
    }
    private static void checkArrayLength(String[] toDoTask) throws TonyException {
        if(toDoTask.length != 2) {
            throw new TonyException();
        }
    }
    private static void addEventTask(String[] eventTask)  throws IOException {
        String[] description = eventTask[1].split("/from | /to");
        Event event = new Event(description[0], description[1], description[2]);
        tasks.add(event);
        printAddOrDeleteTask(description[0] ,tasks.indexOf(event));
        String eventLine = FileSaver.saveEvent(event);
        FileSaver.saveData(eventLine, true);
    }
    private static void addDeadlineTask(String[] deadlineTask)  throws IOException {
        String[] description = deadlineTask[1].split("/by");
        Deadline deadline = new Deadline(description[0], description[1]);
        tasks.add(deadline);
        printAddOrDeleteTask(description[0], tasks.indexOf(deadline));
        String deadllineLine = FileSaver.saveDeadline(deadline);
        FileSaver.saveData(deadllineLine, true);
    }
    private static void addTodoTask(String[] toDoTask)  throws IOException {
        Todo todo = new Todo(toDoTask[1]);
        tasks.add(todo);
        printAddOrDeleteTask(toDoTask[0], tasks.indexOf(todo));
        String todoLine = FileSaver.saveTodo(todo);
        FileSaver.saveData(todoLine, true);
    }
    private static void printAddOrDeleteTask(String command, int index) {
        String deleteOrAdd = (command.equals("delete") ? "removed" : "added");
        int taskSize = (command.equals("delete") ? tasks.size() - 1  : tasks.size());
        System.out.println(
                LINE_BREAKER
                    + "\t Got it. I've " + deleteOrAdd
                    + " this task:"
                    + System.lineSeparator()
                    + "\t\t " + tasks.get(index)
                    + System.lineSeparator()
                    + "\t Now you have " + taskSize + " tasks in the list."
                    + System.lineSeparator()
                    + LINE_BREAKER);
    }
}