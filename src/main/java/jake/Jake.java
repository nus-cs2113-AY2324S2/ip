package jake;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File; 
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import jake.task.Deadline;
import jake.task.Event;
import jake.task.Task;
import jake.task.ToDo;

public class Jake {
    private static final String LINE_STRING = "____________________________________________________________";
    private static ArrayList<Task> commands = new ArrayList<>();

    static final String savedTaskFilePath = "./ip/src/main/java/jake/data/tasks.txt";
    // static final String home = System.getProperty("user.home");
    // static final java.nio.file.Path savedTaskFilePath = java.nio.file.Paths.get(home, "ip", "src", "main", "java", "jake", "data");

    // List out all tasks
    private static void listTasks() {
        System.out.println("Current commands being executed: ");
        for (int i = 0; i < commands.size(); i++){
            System.out.println(Integer.toString(i+1) + "." + commands.get(i));
        }
        System.out.println(LINE_STRING);
    }

    // Retrieve task number from user input. Used in toggleTask() & delete()
    private static int retrieveTaskNumber(String userInput) {
        int taskNumber = Integer.parseInt(userInput.substring(userInput.lastIndexOf(" ")+1));
        return taskNumber;
    }

    // Mark or Unmark respective task.
    private static void toggleTask(String userInput, String taskType) {
        int taskNumber = retrieveTaskNumber(userInput);
        if (taskNumber>commands.size()){
            System.out.println("Task does not exist!");
        } else if (taskType.equals("unmark")){
            System.out.println("Successfully unmarked");
            commands.get(taskNumber-1).markTask(false);
        } else {
            System.out.println("Successfully marked");
            commands.get(taskNumber-1).markTask(true);
        }
        System.out.println(LINE_STRING);
    }

    // Add tasks (Based off individual inputs)
    private static void addInputtedTask(String userInput, String taskType) {
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
        commands.add(newTask);
        System.out.println(LINE_STRING);
        System.out.println("Got it! I have successfully added: \n" + "    " + newTask.toString());
        System.out.printf("You have a total of %d tasks in the list \n", commands.size());
        System.out.println(LINE_STRING);
    }

    // Add tasks (Based off saved data from data.txt)
    private static void addSavedTask(String userInput, char taskType) {
        Task newTask;
        String shortenedTask = userInput.substring(6);
        boolean isCompleted = userInput.charAt(4) == 'X';
        switch (taskType) {
            case 'T':
                newTask = new ToDo("todo" + shortenedTask);
                break;
            case 'D':
                // "\\" deals with PatternSyntaxException due to the (
                String[] deadlineSections = shortenedTask.split(" \\(by: "); 
                newTask = new Deadline("deadline" + deadlineSections[0], 
                        deadlineSections[1].substring(0, deadlineSections[1].length()-1));;
                break;
            case 'E':
                String[] eventSections = shortenedTask.split(" \\(from: ");
                String[] eventTimings = eventSections[1].split(" to: ");
                newTask = new Event("event" + eventSections[0], eventTimings[0], 
                        eventTimings[1].substring(0, eventTimings[1].length()-1));
                break;
            default:
                return;
        } 
        newTask.markTask(isCompleted);
        commands.add(newTask);
    }

    // Delete respective task
    private static void deleteTask(String userInput) {
        int taskNumber = retrieveTaskNumber(userInput);
        try {
            System.out.println(LINE_STRING);
            System.out.println("Got it! I have successfully removed: \n" + "    " + commands.get(taskNumber-1));
            commands.remove(taskNumber-1);
            System.out.printf("You have a total of %d tasks in the list \n", commands.size());
            System.out.println(LINE_STRING);    
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Task does not exist!");
        }
    }

    private static void saveTasks() {
        try {
            FileWriter writer = new FileWriter(savedTaskFilePath);
            for (int i = 0; i < commands.size(); i++){
                writer.write(commands.get(i) + System.lineSeparator());
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
                char taskType = userInput.charAt(1);
                addSavedTask(userInput, taskType);                
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
                case "delete":
                    deleteTask(userInput);
                case "todo":
                case "deadline":
                case "event":
                    try {
                        addInputtedTask(userInput, taskType);
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