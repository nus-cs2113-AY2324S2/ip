import java.util.Scanner;
//so scanner class can be used

public class Yoj {
    public static Task[] tasks = new Task[100];
    public static int taskNumber = 0;
    public static void printLine() {
        System.out.println("________________________________________");
    }
    public static void printShortLine() {
        System.out.println("_____________");
    }
    public static void printHello() {
        String logo =
                "__   __   ___    _____ \n"
                        + "\\ \\ / /  / _ \\  | ___ |\n"
                        + " \\ Y /  | | | |     | | \n"
                        + "  \\ /   | | | |     | | \n"
                        + "  | |   | |_| |  ___| | \n"
                        + "  |_|    \\___/  |____/          \n";
        printLine();
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm YOJ");
        System.out.println("What can I do for you?");
        printLine();
    }
    public static void addTask(String userInput) throws YojException{
        if (userInput.startsWith("todo")) {
            manageToDo(userInput);
        } else if (userInput.startsWith("deadline")) {
            manageDeadline(userInput);
        } else if (userInput.startsWith("event")) {
            manageEvent(userInput);
        }
        printShortLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks[taskNumber]);
        taskNumber++;
        System.out.println("Now you have " + taskNumber + " tasks in the list.");
        printLine();
    }

    public static void manageToDo(String userInput) throws YojException{
        String description = userInput.substring("todo".length()).trim();
        if (description.isEmpty()) {
            throw new YojException("The description of todo cannot be empty...");
        } else {
            tasks[taskNumber] = new ToDo(userInput.substring(5));
        }
    }
    public static void manageDeadline(String userInput) throws YojException{
        String description = userInput.substring("deadline".length()).trim();
        if (description.isEmpty()) {
            throw new YojException("The description of deadline cannot be empty...");
        } else {
            String[] parts = userInput.substring(9).split(" /by ");
            tasks[taskNumber] = new Deadline(parts[0].trim(), parts[1].trim());
        }
    }
    public static void manageEvent(String userInput) throws YojException{
        String description = userInput.substring("event".length()).trim();
        if (description.isEmpty()) {
            throw new YojException("The description of event cannot be empty...");
        } else {
            String[] parts = userInput.substring(6).split(" /from ");
            String[] times = parts[1].split(" /to ");
            tasks[taskNumber] = new Event(parts[0].trim(), times[0].trim(), times[1].trim());
        }
    }
    public static void manageUserInput(String userInput) throws YojException, InvalidCommandException {
        if (userInput.equals("list")) {
            printList();
        } else if(userInput.matches("mark \\d+")) {
            String[] taskIndex = userInput.split(" ");
            int index = Integer.parseInt(taskIndex[1] );
            tasks[index - 1].markDone(index - 1);
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("  [X] " + tasks[index-1].getDescription());
        } else if(userInput.matches("unmark \\d+")) {
            String[] taskIndex = userInput.split(" ");
            int index = Integer.parseInt(taskIndex[1]);
            tasks[index - 1].markUndone(index - 1);
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("  [ ] " + tasks[index - 1].getDescription());
        } else if(userInput.startsWith("todo") || userInput.startsWith("deadline") || userInput.startsWith("event")) {
            addTask(userInput);
        } else if (userInput.equals("bye")){
            System.out.println("bye bye!! hope to see u soon :)");
        } else throw new InvalidCommandException("command not recognised :<");
    }

    public static void printList() {
        for(int i = 0; i < taskNumber; i++) {
            System.out.println(i+1 + ". " + tasks[i].toString());
        }
    }
    public static void main(String[] args) throws YojException, InvalidCommandException {
        printHello();
        // get user input
        Scanner in = new Scanner(System.in);
        String userInput;
        do {
            userInput = in.nextLine();
            try {
                manageUserInput(userInput);
            } catch (YojException e){
                System.out.println(e.getMessage());
            } catch (InvalidCommandException e) {
                System.out.println(e.getMessage());
            }
        } while (!userInput.equals("bye"));
        in.close();

    }
}
