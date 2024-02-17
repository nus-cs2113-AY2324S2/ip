import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.util.Scanner;
import junbot.command.Todo;
import junbot.command.Deadline;
import junbot.command.Event;
import junbot.error.InvalidInputException;
import junbot.task.Task;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JunBot {
    public static String DIVIDER = "____________________________________________________________\n";
    public static String GREETING = "Hello! I'm JunBot\nWhat can I do for you?\n";
    public static String GOODBYE = "Bye. Hope to see you again soon!\n";
    public static ArrayList<Task> tasks = new ArrayList<>();
    public static String FILEPATH = "./data/tasks.txt";

    public static void addEvent(String description, Boolean toPrint) throws InvalidInputException, IOException {
        description = description.replace("event", "").trim();
        if (!description.contains("/from") || !description.contains("/to")) {
            throw new InvalidInputException("Include a /from and /to for event");
        }

        String[] details = new String[3];
        details = description.split("/from|/to", 3);

        Task userTask = new Event(details[0].trim(), details[1].trim(), details[2].trim());
        tasks.add(userTask);

        if(toPrint) {
            System.out.println(DIVIDER + "Got it. I've added this tasks:");
            System.out.println(userTask);
            System.out.println("Now you have " + tasks.size() + " tasks in the list\n" + DIVIDER);
            updateFile();
        }

    }

    public static void addDeadline(String description, Boolean toPrint) throws InvalidInputException, IOException {
        description = description.replace("deadline", "").trim();
        if (!description.contains("/by")) {
            throw new InvalidInputException("Include a /by for deadline");
        }

        String[] details = new String[2];
        details = description.split("/by", 2);

        Task userTask = new Deadline(details[0].trim(), details[1].trim());
        tasks.add(userTask);

        if (toPrint) {
            System.out.println(DIVIDER + "Got it. I've added this tasks:");
            System.out.println(userTask);
            System.out.println("Now you have " + tasks.size() + " tasks in the list\n" + DIVIDER);
            updateFile();
        }

    }

    public static void addTodo(String description, Boolean toPrint) throws InvalidInputException, IOException {
        description = description.replace("todo", "").trim();
        if(description.isEmpty()){
            throw new InvalidInputException("Please state a task : todo <task>");
        }

        Task userTask = new Todo(description);
        tasks.add(userTask);

        if (toPrint) {
            System.out.println(DIVIDER + "Got it. I've added this tasks:");
            System.out.println(userTask);
            System.out.println("Now you have " + tasks.size() + " tasks in the list\n" + DIVIDER);
            updateFile();
        }
    }

    public static boolean isValidListPosition(String command) {
        if (command == null) {
            return false;
        }
        try {
            int listPosition = Integer.parseInt(command);
            return listPosition <= tasks.size() && listPosition > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void unmarkTaskInList(String command, Boolean toPrint) throws IOException {
        command = command.replace("unmark", "").trim();

        if (!isValidListPosition(command)){
            System.out.println("Invalid List Number");
            return;
        }

        int listNumber = Integer.parseInt(command) - 1;
        tasks.get(listNumber).unmarkTask();

        if (toPrint) {
            System.out.println(DIVIDER + "Ok, I've marked this task as not done yet:\n");
            System.out.print(tasks.get(listNumber) + "\n" + DIVIDER);
            updateFile();
        }
    }
    public static void markTaskInList(String command, Boolean toPrint) throws IOException {
        command = command.replace("mark", "").trim();

        if (!isValidListPosition(command)){
            System.out.println("Invalid List Number");
            return;
        }

        int listNumber = Integer.parseInt(command) - 1;
        tasks.get(listNumber).markTask();

        if (toPrint) {
            System.out.println(DIVIDER + "Nice! I've marked this task as done:\n");
            System.out.print(tasks.get(listNumber) + "\n" + DIVIDER);
            updateFile();
        }
    }

    public static String getCommand(String userInput) {
        int i  = userInput.indexOf(' ');
        String command;
        if ( i != -1 ) {
            command = userInput.substring(0, i);
        } else {
            command = userInput;
        }
        return command;

    }
    public static void printList() {
        int taskNumber = 1;

        System.out.println(DIVIDER);
        System.out.println("Here are the tasks in your list: ");
        for(int i = 0; i < tasks.size(); i++){
            System.out.print( taskNumber + ". ");
            System.out.println(tasks.get(i));
            taskNumber += 1;
        }
        System.out.println(DIVIDER + "\n");
    }

    public static void deleteTask(String userInput) throws IOException{
        String command = userInput.replace("delete", "").trim();

        if (!isValidListPosition(command)){
            System.out.println("Invalid List Number");
            return;
        }

        int listNumber = Integer.parseInt(command) - 1;
        Task taskToDisplay = tasks.get(listNumber);
        tasks.remove(listNumber);

        System.out.println(DIVIDER + "Noted. I've removed this task:\n");
        System.out.print(taskToDisplay + "\n" + DIVIDER);
        updateFile();

    }

    public static void handleUserInput() throws IOException {

        Scanner userInputScanner = new Scanner(System.in);
        String userInput = userInputScanner.nextLine();

        while (!userInput.equals("bye")) {
            String command = getCommand(userInput);
            try {
                switch (command) {
                case "list":
                    printList();
                    break;
                case "mark":
                    markTaskInList(userInput, true);
                    break;
                case "unmark":
                    unmarkTaskInList(userInput, true);
                    break;
                case "todo":
                    addTodo(userInput, true);
                    break;
                case "deadline":
                    addDeadline(userInput, true);
                    break;
                case "event":
                    addEvent(userInput, true);
                    break;
                case "delete":
                    deleteTask(userInput);
                    break;
                default:
                    System.out.println("Enter a valid command");
                    break;
                }
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            }
            userInput = userInputScanner.nextLine();
        }

    }

    public static void updateFile() throws IOException{
        FileWriter fw = new FileWriter(FILEPATH);
        for(int i = 0; i < tasks.size(); i++){
            String taskType = tasks.get(i).getTag();

            switch (taskType) {
            case "T":
                fw.write(tasks.get(i).getTag() + "-" + tasks.get(i).getStatusIcon() +
                        "-" + tasks.get(i).getDescription() +
                        System.lineSeparator());
                break;
            case "D":
                fw.write(tasks.get(i).getTag() + "-" + tasks.get(i).getStatusIcon() +
                        "-" + tasks.get(i).getDescription() +
                        "-" + tasks.get(i).getEndDate() +
                        System.lineSeparator());
                break;
            case "E":
                fw.write(tasks.get(i).getTag() + "-"  + tasks.get(i).getStatusIcon() +
                        "-" + tasks.get(i).getDescription() +
                        "-" + tasks.get(i).getStartDate() +
                        "-" + tasks.get(i).getEndDate() +
                        System.lineSeparator());
                break;

            default :
                break;
            }

        }

        fw.close();
    }

    public static void importFile() throws FileNotFoundException,InvalidInputException,IOException {
        File f = new File(FILEPATH);
        if(!f.exists()){
            Path path = Paths.get(FILEPATH);
            Files.createDirectories(path.getParent());
            Files.createFile(path);
        }

        Scanner s = new Scanner(f);
        int counter = 1;

        while (s.hasNext()){
            String[] commands = s.nextLine().split("-");
            switch (commands[0]){
            case "T":
                addTodo("todo " + commands[2], false);
                break;
            case "D":
                addDeadline("deadline " + commands[2] +
                        " /by " + commands[3], false);
                break;
            case "E":
                addEvent("event " + commands[2] +
                        " /from " + commands[3] +
                        " /to " + commands[4], false);
                break;
            default :
            }

            if (commands[1].equals("X")) {
                markTaskInList("mark " + counter, false);
            }

            counter += 1;
        }

    }

    public static void main(String[] args) throws IOException, InvalidInputException{
        System.out.println(DIVIDER + GREETING + DIVIDER);
        importFile();
        handleUserInput();
        System.out.println(DIVIDER + GOODBYE + DIVIDER);
    }
}
