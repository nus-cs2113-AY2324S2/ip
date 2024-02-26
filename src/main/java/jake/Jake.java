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

import static jake.common.Messages.MESSAGE_LINE_STRING;
import static jake.common.Messages.MESSAGE_GREETING;
import static jake.common.Messages.MESSAGE_GOODBYE;
import static jake.common.Messages.MESSAGE_INVALID_COMMAND;
import static jake.common.Messages.MESSAGE_INVALID_FILEPATH;
import static jake.common.Messages.MESSAGE_LISTED_TASKS;
import static jake.common.Messages.MESSAGE_TASK_DOES_NOT_EXIST;
import static jake.common.Messages.MESSAGE_TASK_MARKED;
import static jake.common.Messages.MESSAGE_TASK_UNMARKED;
import static jake.common.Messages.MESSAGE_TASK_ADDED;
import static jake.common.Messages.MESSAGE_TASK_DELETED;
import static jake.common.Messages.MESSAGE_TASK_ERROR_ENCOUNTERED;

public class Jake {
    private static ArrayList<Task> commands = new ArrayList<>();

    static final String savedTaskFilePath = "./ip/src/main/java/jake/data/tasks.txt";
    // static final String home = System.getProperty("user.home");
    // static final java.nio.file.Path savedTaskFilePath = java.nio.file.Paths.get(home, "ip", "src", "main", "java", "jake", "data");

    // List out all tasks
    private static void listTasks() {
        System.out.println(MESSAGE_LISTED_TASKS);
        for (int i = 0; i < commands.size(); i++){
            System.out.println(Integer.toString(i+1) + "." + commands.get(i));
        }
        System.out.println(MESSAGE_LINE_STRING);
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
            System.out.println(MESSAGE_TASK_DOES_NOT_EXIST);
        } else if (taskType.equals("unmark")){
            System.out.println(MESSAGE_TASK_UNMARKED);
            commands.get(taskNumber-1).markTask(false);
        } else {
            System.out.println(MESSAGE_TASK_MARKED);
            commands.get(taskNumber-1).markTask(true);
        }
        System.out.println(MESSAGE_LINE_STRING);
    }

    // Add tasks (Based off individual inputs)
    private static void addInputtedTask(String userInput, String taskType) {
        Task newTask;
        switch (taskType) {
        case "todo":
            newTask = new ToDo(userInput);
            break;
        case "deadline":
            String[] deadlineSections = userInput.split(" by ");
            newTask = new Deadline(deadlineSections[0], deadlineSections[1]);;
            break;
        case "event":
            String[] eventSections = userInput.split(" from ");
            String[] eventTimings = eventSections[1].split(" to ");
            newTask = new Event(eventSections[0], eventTimings[0], eventTimings[1]);
            break;
        default:
            return;
        } 
        commands.add(newTask);
        System.out.printf(MESSAGE_TASK_ADDED, newTask.toString(), commands.size());
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
            System.out.printf(MESSAGE_TASK_DELETED, commands.get(taskNumber-1), commands.size()-1);
            commands.remove(taskNumber-1);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(MESSAGE_TASK_DOES_NOT_EXIST);
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
            System.out.printf(MESSAGE_TASK_ERROR_ENCOUNTERED, e.getMessage());
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
            System.out.println(MESSAGE_INVALID_FILEPATH);
        }
    }

    public static void main(String[] args) throws JakeException {
        loadTasks();
        System.out.println(MESSAGE_GREETING);
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
                System.out.println(MESSAGE_GOODBYE);
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
                    System.out.println(MESSAGE_INVALID_COMMAND);
                    break;
                } 
            default:
                System.out.println(MESSAGE_INVALID_COMMAND);
            }
        } while (!userInput.equals("bye"));

        myScanner.close();
    }
}