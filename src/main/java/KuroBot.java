import java.util.Scanner;

public class KuroBot {

    private static Task[] tasks = new Task[100];
    private static int taskNum = 0;
    private static boolean isStart;
    private static final int LINE_LEN = 60;
    private static final String LINE =  "-".repeat(LINE_LEN);
    private static Scanner scanner;

    private static final String LOGO =
            " ___   ___    ___    ___ \n"
                    + "|   |/   /   |  |   |  | \n"
                    + "|       /    |  |   |  | \n"
                    + "|   |\\   \\   |_ |___| _| \n"
                    + "|___| \\___\\    |_____|   \n";


    private static void printTasks() {
        System.out.println(LINE);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskNum; i ++){
            System.out.println(i+1 + "." + tasks[i].printTask());
        }
        System.out.println(LINE);
    }

    private static void start() {
        System.out.println(LINE);
        System.out.println("Hello! I'm KuroBot\n" + "What can I do for you?");
        System.out.println(LINE);
        isStart = true;
        scanner = new Scanner(System.in);
    }

    private static void printAddedTask(Task task) {
        System.out.println(LINE);
        System.out.println("Got it. I've added this task:");
        System.out.println(task.printTask());
        System.out.println("Now you have " + taskNum + " tasks in the list.");
        System.out.println(LINE);
    }

    private static void addTodo(String userInput) throws InvalidDescriptionException {
        //check if description was given
        String[] words = userInput.split(" ",2);
        if (words.length < 2){
            throw new InvalidDescriptionException();
        }

        String taskName = words[1];
        Todo task = new Todo(taskName);
        tasks[taskNum++] = task;
        printAddedTask(task);
    }

    private static void addDeadline(String userInput) throws InvalidDescriptionException, InvalidTimeException {
        //check if description was given
        String[] words = userInput.split(" ",2);
        if (words.length < 2){
            throw new InvalidDescriptionException();
        }
        String description = words[1];

        //check if due date was given
        String[] phrases = description.split("/by", 2);
        if (phrases.length < 2){
            throw new InvalidTimeException();
        }
        String taskName = phrases[0];
        String by = phrases[1].strip();

        Deadline task = new Deadline(taskName, by);
        tasks[taskNum++] = task;
        printAddedTask(task);
    }

    private static void addEvent(String userInput) throws InvalidDescriptionException, InvalidTimeException {
        //check if description was given
        String[] words = userInput.split(" ",2);
        if (words.length < 2){
            throw new InvalidDescriptionException();
        }
        String description = words[1];

        //check if duration was given
        String[] phrases = description.split("/from",2);
        if (phrases.length < 2){
            throw new InvalidTimeException();
        }
        String taskName = phrases[0];

        //check if both "from" and "to" was given
        String[] period = phrases[1].split("/to",2);
        if(period.length < 2){
            throw new InvalidTimeException();
        }
        String from = period[0].strip();
        String to = period[1].strip();

        Event task = new Event(taskName, from, to);
        tasks[taskNum++] = task;
        printAddedTask(task);
    }

    private static void end() {
        System.out.println(LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
        System.out.println(LOGO);
        isStart = false;
        scanner.close();
    }

    private static void markTask(String userInput, boolean status) throws InvalidDescriptionException{
        //check if task number was given
        String[] words = userInput.split(" ",2);
        if (words.length < 2){
            throw new InvalidDescriptionException();
        }

        //get task number
        String taskIndex = words[1];
        int i = Integer.parseInt(taskIndex);
        try {
            if (status) {
                tasks[i - 1].mark();
            } else {
                tasks[i - 1].unmark();
            }
        } catch (NullPointerException e){
            System.out.println(LINE);
            System.out.println("there's no such task though...");
            System.out.println(LINE);
        }
    }

    private static void manageTasks(String input) throws InvalidCommandException{
        //extract command keyword from input
        String[] words = input.split(" ",2);
        //first phrase given is the command keyword
        String command = words[0];
        switch (command) {
        case "bye":
            end();
            break;
        case "list":
            printTasks();
            break;
        case "mark":
            try {
                markTask(input, true);
            } catch (InvalidDescriptionException e){
                System.out.println(LINE);
                System.out.println("mhmm.. which task have you completed? >.<");
                System.out.println(LINE);
            }
            break;
        case "unmark":
            try {
                markTask(words[1], false);
            } catch (InvalidDescriptionException e){
                System.out.println(LINE);
                System.out.println("oopsie, what task should I unmark?");
                System.out.println(LINE);
            }
            break;
        case "todo":
            try {
                addTodo(input);
            } catch (InvalidDescriptionException e){
                System.out.println(LINE);
                System.out.println("Hmmm.. what is the task about?");
                System.out.println(LINE);
            }
            break;
        case "deadline":
            try {
                addDeadline(input);
            } catch (InvalidDescriptionException e){
                System.out.println(LINE);
                System.out.println("Heyyy~ don't forget your task");
                System.out.println(LINE);
            } catch (InvalidTimeException e){
                System.out.println(LINE);
                System.out.println("Did you forget your due date? :p");
                System.out.println(LINE);
            }
            break;
        case "event":
            try {
                addEvent(input);
            } catch (InvalidDescriptionException e){
                System.out.println(LINE);
                System.out.println("aiyoyo, how can you forget the event XD");
                System.out.println(LINE);
            } catch (InvalidTimeException e){
                System.out.println(LINE);
                System.out.println("uhoh! don't forget the timings!");
                System.out.println(LINE);
            }
            break;
        default:
            throw new InvalidCommandException();
        }
    }

    public static void main(String[] args) {

        //display welcome message
        start();

        while (isStart) {
            String input = scanner.nextLine();
            try {
                manageTasks(input);
            } catch (InvalidCommandException e) {
                System.out.println(LINE);
                System.out.println("Whoops! Please enter a valid command~");
                System.out.println(LINE);
            }
        }
    }
}
