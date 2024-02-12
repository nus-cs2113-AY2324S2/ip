import java.util.Scanner;

public class Tony {
    public static final String LINE_BREAKER = "__________________________________________________"
            + System.lineSeparator();
    public static int taskCounter = 0;
    public static void main(String[] args) {
        printWelcomeMessage();
        Task[] tasks = new Task[100];
        Scanner userInput = new Scanner(System.in);
        while(userInput.hasNextLine()) {
            String line = userInput.nextLine();
            if(line.equals("bye")){
                printByeMessage();
                return;
            } else if(line.equals("list")) {
                listTasks(tasks);
            } else if(line.contains("mark")) {
                String[] subCommand = line.split(" ");
                int num = Integer.parseInt(subCommand[1]);
                if(num > taskCounter) {
                    System.out.println("Suggest a number available in the list!");
                } else {
                    markTasks(subCommand, tasks, num);
                }
            } else if(line.startsWith("todo") || line.startsWith("deadline")
                    || line.startsWith("event")) {
                addTasks(line, tasks);
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
        for(int i = 0; i < taskCounter; i++) {
            Task task = tasks[i];
            System.out.println("\t" + (i+1) + "."
                    + task);
        }
        System.out.println(LINE_BREAKER);
    }

    private static void markTasks(String[] subCommand, Task[] tasks, int num) {
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
    private static void addTasks(String userInput, Task[] allTasks) {
        if(userInput.startsWith("todo")) {
            String[] toDoTask = userInput.split("todo");
            Todo todo = new Todo(toDoTask[1]);
            allTasks[taskCounter] = todo;
            printAddNewTask(allTasks);
        } else if(userInput.startsWith("deadline")) {
            String[] deadlineTask = userInput.split("deadline");
            String[] description = deadlineTask[1].split("/by");
            Deadline deadline = new Deadline(description[0], description[1]);
            allTasks[taskCounter] = deadline;
            printAddNewTask(allTasks);
        } else if(userInput.startsWith("event")) {
            String[] eventTask = userInput.split("event");
            String[] description = eventTask[1].split("/from | /to");
            Event event = new Event(description[0], description[1], description[2]);
            allTasks[taskCounter] = event;
            printAddNewTask(allTasks);
        }
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