package jake;
import java.util.Scanner;
import java.io.File; 
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import jake.task.Deadline;
import jake.task.Event;
import jake.task.Task;
import jake.task.ToDo;

public class Jake {
    static Task[] commands = new Task[100];
    static int totalTasks = 0;
    static final String LINE_STRING = "____________________________________________________________";
    static final String savedTaskFilePath = "./ip/src/main/java/jake/data/tasks.txt";
    // static final String home = System.getProperty("user.home");
    // static final java.nio.file.Path savedTaskFilePath = java.nio.file.Paths.get(home, "ip", "src", "main", "java", "jake", "data");

    // List out all tasks
    private static void listTasks() {
        System.out.println("Current commands being executed: ");
        for (int i = 0; i < totalTasks; i++){
            System.out.println(Integer.toString(i+1) + "." + commands[i].toString());
        }
        System.out.println(LINE_STRING);
    }

    // Mark or Unmark respective task.
    private static void toggleTask(String userInput, String taskType) throws JakeException {
        int taskNumber = Integer.parseInt(userInput.substring(userInput.lastIndexOf(" ")+1));
        if (taskNumber>totalTasks){
            System.out.println("Task does not exist!");
        } else if (taskType.equals("unmark")){
            System.out.println("Successfully unmarked");
            commands[taskNumber-1].markTask(false);
        } else {
            System.out.println("Successfully marked");
            commands[taskNumber-1].markTask(true);
        }
        System.out.println(LINE_STRING);
    }

    // Add ToDos, Deadlines, and Events
    private static void addTask(String userInput, String taskType, boolean isPrinted) {
        Task newTask;
        switch (taskType) {
            case "todo":
                newTask = new ToDo(userInput);
                break;
            case "deadline":
                String[] deadlineSections = userInput.split(" /by ");
                newTask = new Deadline(deadlineSections[0], deadlineSections[1]);;
                break;
            case "event":
                String[] eventSections = userInput.split(" /from ");
                String[] eventTimings = eventSections[1].split(" /to ");
                newTask = new Event(eventSections[0], eventTimings[0], eventTimings[1]);
                break;
            default:
                return;
        } 
        commands[totalTasks] = newTask;
        totalTasks++;

        // "True" for when adding task from user input. "False" for when adding task from tasks.txt at the start
        if (isPrinted) {
            System.out.println(LINE_STRING);
            System.out.println("Got it! I have successfully added: \n" + "    " + newTask.toString());
            System.out.printf("You have a total of %d tasks in the list \n", totalTasks);
            System.out.println(LINE_STRING);
        }
    }

    private static void saveTasks() {
        try {
            FileWriter writer = new FileWriter(savedTaskFilePath);
            for (int i = 0; i < totalTasks; i++){
                writer.write(commands[i].toString() + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Something went wrong..." + e.getMessage());
        }
    }

    private static void loadTasks() {
        try {
            File savedFile = new File(savedTaskFilePath);
            Scanner savedFileScanner = new Scanner(savedFile);
            while (savedFileScanner.hasNext()) {
                String userInput = savedFileScanner.nextLine();
                // Extract out the task description, and whether it is marked
                char taskType = userInput.charAt(1);
                boolean isCompleted = userInput.charAt(4) == 'X';
                switch (taskType) {
                    case 'T':
                        addTask("todo" + userInput.substring(6), "todo", false);
                        break;
                    case 'D':
                        addTask("deadline" + userInput.substring(6), "deadline", false);
                        System.out.println("Adding deadline task"); 
                        break;
                    case 'E':
                        addTask("event" + userInput.substring(6), "event", false);
                        System.out.println("Adding event task");
                        break;                  
                }
                commands[totalTasks-1].markTask(isCompleted);
            }
            savedFileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File cannot be found!");
        }
    }

    public static void main(String[] args) throws JakeException {
        loadTasks();
        System.out.println("Hello! I'm Jake\n" + "What can I do for you? \n" + LINE_STRING);
        Scanner myScanner = new Scanner(System.in);
        String userInput= "";

        do {
            userInput = myScanner.nextLine();
            String taskType; 
            // Check if the userInput contains a space, to handle inputs like "list" and "bye";
            if (userInput.contains(" ")) {
                taskType = userInput.substring(0, userInput.indexOf(" "));
            } else {
                taskType = userInput;
            }

            switch (taskType) {
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                case "list":
                    listTasks();
                    break;
                case "mark":
                case "unmark":
                    toggleTask(userInput, taskType);
                    saveTasks();
                    break;
                case "todo":
                case "deadline":
                case "event":
                    try {
                        addTask(userInput, taskType, true);
                        saveTasks();
                        break;
                    } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e) {
                        //throw new JakeException("Invalid task! Please try again!", e);
                        System.out.println("Invalid task! Please check your formatting!");
                        break;
                    } 
                default:
                    System.out.println("Command not recognised! Please try again!");
            }
        } while (!userInput.equals("bye"));

        myScanner.close();
    }
}