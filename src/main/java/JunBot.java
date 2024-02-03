import java.util.Scanner;
public class JunBot {
    public static String DIVIDER = "____________________________________________________________\n";
    public static String GREETING = "Hello! I'm JunBot\nWhat can I do for you?\n";
    public static String GOODBYE = "Bye. Hope to see you again soon!\n";
    public static Task[] tasks = new Task[100];
    public static int tasksCount = 0;

    public static void addEvent(String description){
        description = description.replace("event", "").trim();
        if (!description.contains("/from") || !description.contains("/to")) {
            System.out.println("Include a /from and /to for event");
            return;
        }

        String[] details = new String[3];
        details = description.split("/from|/to", 3);

        Task userTask = new Event(details[0].trim(), details[1].trim(), details[2].trim());
        tasks[tasksCount] = userTask;
        tasksCount += 1;

        System.out.println(DIVIDER + "Got it. I've added this tasks:");
        System.out.println(userTask);
        System.out.println("Now you have " + tasksCount + " tasks in the list\n" + DIVIDER);



    }

    public static void addDeadline(String description){
        description = description.replace("deadline", "").trim();
        if (!description.contains("/by")) {
            System.out.println("Include a /by for deadline");
            return;
        }

        String[] details = new String[2];
        details = description.split("/by", 2);

        Task userTask = new Deadline(details[0].trim(), details[1].trim());
        tasks[tasksCount] = userTask;
        tasksCount += 1;

        System.out.println(DIVIDER + "Got it. I've added this tasks:");
        System.out.println(userTask);
        System.out.println("Now you have " + tasksCount + " tasks in the list\n" + DIVIDER);

    }

    public static void addTodo(String description){
        description = description.replace("todo", "").trim();
        Task userTask = new Todo(description);
        tasks[tasksCount] = userTask;
        tasksCount++;

        System.out.println(DIVIDER + "Got it. I've added this tasks:");
        System.out.println(userTask);
        System.out.println("Now you have " + tasksCount + " tasks in the list\n" + DIVIDER);
    }

    public static void unmarkTaskInList(String command){
        command = command.replace("unmark", "").trim();
        int listNumber = Integer.parseInt(command) - 1;
        tasks[listNumber].unmarkTask();
    }
    public static void markTaskInList(String command){
        command = command.replace("mark", "").trim();
        int listNumber = Integer.parseInt(command) - 1;
        tasks[listNumber].markTask();
    }

    public static String getCommand(String userInput){
        int i  = userInput.indexOf(' ');
        String command;
        if ( i != -1 ) {
            command = userInput.substring(0, i);
        } else {
            command = userInput;
        }
        return command;

    }
    public static void printList(){
        int taskNumber = 1;

        System.out.println(DIVIDER);
        System.out.println("Here are the tasks in your list: ");
        for(int i = 0; i < tasksCount; i++){
            System.out.print( taskNumber + ". ");
            //tasks[i].printTask();
            System.out.println(tasks[i]);
            taskNumber += 1;
        }
        System.out.println(DIVIDER + "\n");
    }

    public static void addToList(String description){
        Task userTask = new Task(description);
        tasks[tasksCount] = userTask;
        System.out.println(DIVIDER + "added: " + userTask.description + "\n" + DIVIDER);
    }

    public static void handleUserInput(){

        Scanner userInputScanner = new Scanner(System.in);
        String userInput = userInputScanner.nextLine();

        while (!userInput.equals("bye")){
            String command = getCommand(userInput);
            switch (command) {
            case "list" :
                printList();
                break;
            case "mark" :
                markTaskInList(userInput);
                break;
            case "unmark":
                unmarkTaskInList(userInput);
                break;
            case "todo" :
                addTodo(userInput);
                break;
            case "deadline" :
                addDeadline(userInput);
                break;
            case "event" :
                addEvent(userInput);
                break;
            default :
                System.out.println("Enter a valid command");
                break;
            }
            userInput = userInputScanner.nextLine();
        }

    }

    public static void main(String[] args) {
        System.out.println(DIVIDER + GREETING + DIVIDER);
        handleUserInput();
        System.out.println(DIVIDER + GOODBYE + DIVIDER);
    }
}
