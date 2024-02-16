import java.util.Scanner;


public class Allez {
    public static final int LIST_SIZE = 100;
    protected static Task[] tasks = new Task[LIST_SIZE];
    protected static  int taskCount = 0;
    protected static Scanner in = new Scanner(System.in);

    public enum TaskType {
        TODO, DEADLINE, EVENT
    }

    public static void printList(Task[] tasks){
        int count = 0;
        System.out.println("Here are the tasks in your list:");
        while(tasks[count] != null){
            System.out.println((count+1) + ". " + tasks[count].toString());
            count+=1;
        }
    }

    public static void main(String[] args) {
        boolean hasEnded = false;

        printGreeting();

        while(!hasEnded) {
            hasEnded = executeCommands();
        }

        printExit();
    }

    private static boolean executeCommands() {
//        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        String firstWord = line.split(" ",2)[0];

        switch(firstWord){
        case "bye":
            return true;
        case "mark":
            markTask(line);
            break;
        case "todo":
            createTask(line, TaskType.TODO);
            break;
        case "deadline":
            createTask(line, TaskType.DEADLINE);
            break;
        case "event":
            createTask(line, TaskType.EVENT);
            break;
        case "list":
            printList(tasks);
            break;
        default:
            System.out.println("Invalid command. Please try again.");
            break;
        }

        return false;
    }

    private static void createTask(String lineWithCommand, TaskType type) {
        String[] lineSegment;
        String description;
        String by;
        String from;
        String to;
        String line = lineWithCommand.split(" ",2)[1];


        switch (type){
        case TODO:
            description = line.trim();
            tasks[taskCount] = new ToDo(description);
            break;
        case DEADLINE:
            lineSegment = line.split("/", 2);
            description = lineSegment[0].trim();
            by = lineSegment[1].substring(2).trim();
            tasks[taskCount] = new Deadline(description, by);
            break;
        case EVENT:
            lineSegment = line.split("/", 3);
            description = lineSegment[0].trim();
            from = lineSegment[1].substring(4).trim();
            to = lineSegment[2].substring(2).trim();
            tasks[taskCount] = new Event(description, from, to);
            break;
        default:
            System.out.println("Invalid TaskType occurred.");
            return;
        }

        System.out.println("Got it. I've added this task:");
        System.out.println("\t" + tasks[taskCount].toString());
        taskCount +=1;
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

    private static void markTask(String line) {
        int toMark;
        toMark = Integer.parseInt(line.substring(4).trim()) -1;
        tasks[toMark].markDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("\t" + tasks[toMark].toString());
    }

    private static void printExit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void printGreeting() {
        System.out.println("_________________________");
        System.out.println("Hello! I'm Allez");
        System.out.println("What can I do for you?");
        System.out.println("_________________________");
    }
}
