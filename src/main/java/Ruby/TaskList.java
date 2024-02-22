package Ruby;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import Exception.*;
import Task.*;


/**
 * Manages a list of tasks for the chatbot.
 * Supports adding tasks of different types (todo, deadline, event),
 * marking tasks as done or undone, and displaying all tasks.
 */
public class TaskList {
    public final Task[] taskList= new Task[100]; // Array to store tasks
    public int taskNo = 0; // Counter for the number of tasks

    /**
     * Adds a task to the task list based on user input.
     * The task can be of types: todo, deadline, event, or a general task.
     * Parses the user input to determine the task type and details.
     *
     * @param userInput The full command entered by the user to add a task.
     */
    public void addTask(String userInput) throws StringIndexOutOfBoundsException, ArrayIndexOutOfBoundsException{
        try {
            keywordCatcher(userInput);
        }catch (InvalidKeywordException e){
            print("Sorry sir. I am not intelligent enough to know what that means.");
            return;
        }catch (MissingDescriptionException e){
            print("Sorry sir. Please give me more detail to your task.");
            return;
        }
        String[] inputSplitBySlash = userInput.split(" /");
        switch (userInput.split(" ")[0]){
        case "todo":
            taskList[taskNo] = new Todo(userInput.substring(5),taskNo+1);
            break;
        case "deadline":
            String name = inputSplitBySlash[0].substring(9);
            String by = inputSplitBySlash[1].substring(3);
            taskList[taskNo] = new Deadline(name, taskNo+1, by);
            break;
        case "event":
            name = inputSplitBySlash[0].substring(6);
            String from = inputSplitBySlash[1].substring(5);
            String to = inputSplitBySlash[2].substring(3);
            taskList[taskNo]=new Event(name,taskNo+1, from, to);
            break;
        default:
            break;
        }
        taskAddMessage();
    }

    /**
     * Displays a message confirming the addition of a task.
     * Also shows the current number of tasks in the list.
     */
    private void taskAddMessage() {
        System.out.println("    " + "--------------");
        System.out.println("    Got it. I've added this task:");
        System.out.print("      ");
        taskList[taskNo].printTask();
        taskNo++;
        System.out.println("    Now you have " + taskNo + " tasks in the list.");
        System.out.println("    " + "--------------");
    }

    /**
     * Marks a task as completed based on its position in the task list.
     * If the task does not exist, prints an error message.
     *
     * @param n The index of the task in the task list (0-based).
     */
    public void markTask (int n){
        taskList[n].markedTask();
    }

    /**
     * Marks a task as not completed based on its position in the task list.
     * If the task does not exist, prints an error message.
     *
     * @param n The index of the task in the task list (0-based).
     */
    public void unmarkTask (int n){
        taskList[n].unmarkedTask();
    }

    /**
     * Prints all tasks in the task list.
     * Displays a numbered list of tasks along with their completion status and details.
     */
    public void showTaskList() {
        System.out.println("    " + "--------------");
        System.out.println("    " + "Here are the tasks in your list:");
        for (int i=0; i < taskNo; i++){
            System.out.print("    " + (i+1) +".");
            taskList[i].printTask();
        }
        System.out.println("    " + "--------------");
    }

    public void readFileRecords() throws IOException {
        Files.createDirectories(Paths.get("./data"));
        String path = "./data/Ruby.txt";
        BufferedReader br = new BufferedReader(new FileReader(path));
        String nextLine = br.readLine();
        String[] result;

        while (nextLine != null) {
            result = nextLine.split(" \\| ");
            String taskType = result[0];
            boolean hasDone = Boolean.parseBoolean(result[1]);
            String taskName = result[2];

            switch (taskType){
            case "T":
                taskList[taskNo] = new Todo(taskName,taskNo+1);
                taskNo++;
                break;
            case "D":
                taskList[taskNo] = new Deadline(taskName, taskNo+1, result[3]);
                taskNo++;
                break;
            case "E":
                String[] duration = result[3].split(" - ");
                taskList[taskNo] = new Event(taskName, taskNo+1, duration[0],duration[1]);
                taskNo++;
                break;
            default:
                break;
            }
            nextLine = br.readLine();
        }
        showTaskList();
    }

    private void listWrite() throws IOException {
        Files.createDirectories(Paths.get("./data"));
        FileWriter out = new FileWriter("./data/Ruby.txt", false);
        for (int i=0; i < taskNo; i++){
            out.write(taskList[i].toString());
            out.write(System.lineSeparator());
        }
        out.close() ;
    }

    public void saveToFile(){
        try{
            listWrite();
        } catch (IOException e) {
            System.out.println("Sorry, something wrong with my recording function.");
        }
    }

    /**
     * Prints a formatted message to the console.
     *
     * @param thingToPrint The message to be printed.
     */
    private static void print(String thingToPrint){
        System.out.println("    " + "--------------");
        System.out.println("    " + thingToPrint);
        System.out.println("    " + "--------------");
    }

    public static void keywordCatcher(String userInput) throws InvalidKeywordException, MissingDescriptionException {
        String[] taskTypeList = {"todo", "deadline", "event"};
        String[] inputBreakdown = userInput.split(" ");
        if (!Arrays.asList(taskTypeList).contains(inputBreakdown[0])){
            throw new InvalidKeywordException();
        }
        if (inputBreakdown.length<2){
            throw new MissingDescriptionException();
        }
    }
}
