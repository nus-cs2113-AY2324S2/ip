package Casper;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.ArrayList;

public class Casper {
    private static final String SEPARATOR = "    _______________________________________________________________________";
    private static final ArrayList<Task> taskList = new ArrayList<>();
    private static int noOfTasks = 0;
    private static final String[] keywordList = {"bye", "list", "mark", "unmark", "deadline", "event", "todo", "delete",
            "save"};
    private static final String pathToSaveDirectory = "./save/";
    private static final String saveFilename= "savedCasper.txt";
    private static void wrapEchoMessage(String message){
        System.out.println(SEPARATOR);
        System.out.println("     "+message);
        System.out.println(SEPARATOR);
    }


    private static void echoGreetings(){
        String logo = "       ___ __ _ ___ _ __   ___ _ __\n"
                + "      / __/ _` / __| '_ \\ / _ \\ '__|\n"
                + "     | (_| (_| \\__ \\ |_) |  __/ |   \n"
                + "      \\___\\__,_|___/ .__/ \\___|_|   \n"
                + "                   | |              \n"
                + "                   |_|               ";
        System.out.println("    Starting\n" + logo);
        wrapEchoMessage("Boo! I'm Casper!\n     What can I do for you?");
    }

    private static void handleQueries(){
        Scanner inputScanner = new Scanner(System.in);
        boolean isRunning = true;
        while (isRunning) {
            String userInput = inputScanner.nextLine();
            try{
                validateInputKeyword(userInput);
                isRunning = handleKeywordRouting(userInput);
            } catch (CasperUnrecognizedKeywordException e){
                System.out.println(SEPARATOR);
                System.out.println("     Pardon? I didn't quite understand \""+ e.getUnrecognizedKeyword()+"\"");
                System.out.println("     Maybe refer to the following list of commands?");
                for(String keyword:keywordList){
                    System.out.println("      - " + keyword);
                }
                System.out.println(SEPARATOR);
            }
        }
    }

    private static boolean handleKeywordRouting(String userInput){
        String commandPrefix = userInput.split(" ")[0];
        switch (commandPrefix) {
        case "bye":
            return false;
        case "list":
            echoTaskList();
            break;
        case "mark":
        case "unmark":
            handleMarkTask(userInput);
            break;
        case "event":
            handleEvent(userInput);
            break;
        case "deadline":
            handleDeadline(userInput);
            break;
        case "todo":
            handleTodo(userInput);
            break;
        case "delete":
            deleteTask(userInput);
            break;
        case "save":
            handleSaveFile();
        default:
            break;
        }
        return true;
    }

    private static void handleSaveFile(){
        try{
            File saveFile = new File(pathToSaveDirectory + saveFilename);
            PrintWriter writer = new PrintWriter(saveFile);
            writer.close();
            wrapEchoMessage("File cleared.");
        } catch (FileNotFoundException e){
            handleMissingDirectory();
        }
    }
    
    private static void handleMissingDirectory(){
        Path absolutePath = Paths.get(pathToSaveDirectory).toAbsolutePath();
        try{
            Files.createDirectory(absolutePath);
            Path filePath = absolutePath.resolve(saveFilename);
            Files.createFile(filePath);
            wrapEchoMessage("File created successfully");
        } catch (IOException e){
            wrapEchoMessage(e.getMessage());
        }
    }
    private static void handleMarkTask(String userInput){
        boolean toMark = userInput.split(" ")[0].equals("mark");
        int targetTaskNumber = validateTargetedInput(userInput);
        if (targetTaskNumber == -1) {
            return;
        }
        if(toMark){
            taskList.get(targetTaskNumber-1).markTask();
        }else{
            taskList.get(targetTaskNumber-1).unMarkTask();
        }
    }

    private static void deleteTask(String userInput){
        int targetTaskNumber = validateTargetedInput(userInput);
        if(targetTaskNumber != -1){
            Task taskToRemove = taskList.get(targetTaskNumber-1);
            taskList.remove(targetTaskNumber-1);
            noOfTasks--;
            wrapEchoMessage("Nicely done!. It's about time you get things done: \n       "
                    +taskToRemove+"\n     Now you have "+noOfTasks+" task(s) in the list");
        }
    }

    private static void validateInputKeyword(String userInput) throws CasperUnrecognizedKeywordException {
        String userInputKeyword = userInput.split(" ")[0];
        boolean isValidKeyword = false;
        for(String keyword : keywordList){
            if (keyword.equals(userInputKeyword)) {
                isValidKeyword = true;
                break;
            }
        }
        if(!isValidKeyword){
            throw new CasperUnrecognizedKeywordException(userInputKeyword);
        }
    }

    private static Task getEvent(String userInput) throws StringIndexOutOfBoundsException {
        int fromIndex = userInput.indexOf("/from") + "/from".length();
        int toIndex = userInput.indexOf("/to") + "/to".length();
        int descIndex = userInput.indexOf("event")+"event".length();

        boolean isValidEventInput = fromIndex < descIndex || toIndex < descIndex;
        if (isValidEventInput) {
            throw new StringIndexOutOfBoundsException();
        }

        String eventDesc = userInput.substring(descIndex, userInput.indexOf("/from")).trim();
        String from = userInput.substring(fromIndex, userInput.indexOf("/to")).trim();
        String to = userInput.substring(toIndex).trim();
        return new Event(eventDesc, from, to);
    }

    private static void handleEvent(String userInput) {
        try{
            Task newTask = getEvent(userInput);
            taskList.add(newTask);
            noOfTasks++;
            wrapEchoMessage("Got it. I've added this task: \n       "
                    +newTask+"\n     Now you have "+noOfTasks+" tasks in the list");
        } catch (StringIndexOutOfBoundsException e){
            wrapEchoMessage("What event did you say? Try again?\n\n"
                    +"     Make sure its in the format: \n"
                    +"       event {description} /from {date} /to {date}");
        }
    }

    private static Task getDeadline(String userInput) throws StringIndexOutOfBoundsException {
        int byIndex = userInput.indexOf("/by") + "/by".length();
        int descIndex = userInput.indexOf("deadline")+"deadline".length();

        boolean isValidDeadlineInput = byIndex < descIndex;
        if (isValidDeadlineInput) {
            throw new StringIndexOutOfBoundsException();
        }

        String deadlineDesc = userInput.substring(descIndex, userInput.indexOf("/by")).trim();
        String by = userInput.substring(byIndex).trim();
        return new Deadline(deadlineDesc, by);
    }

    private static void handleDeadline(String userInput){
        try{
            Task newTask = getDeadline(userInput);
            taskList.add(newTask);
            noOfTasks++;
            wrapEchoMessage("Got it. I've added this task: \n       "
                    +newTask+"\n     Now you have "+noOfTasks+" tasks in the list");
        } catch (StringIndexOutOfBoundsException e) {
            wrapEchoMessage("A deadline to what? Try again?\n\n"
                    +"     Make sure its in the format: \n"
                    +"       deadline {description} /by {date}");
        }
    }

    private static Task getTodo(String userInput) throws CasperEmptyTodoException {
        int descIndex = userInput.indexOf("todo")+"todo".length();
        String todoDesc = userInput.substring(descIndex).trim();
        if(todoDesc.isEmpty()){
            throw new CasperEmptyTodoException();
        }
        return new Todo(todoDesc);
    }

    private static void handleTodo(String userInput) {
        try{
            Task newTask = getTodo(userInput);
            taskList.add(newTask);
            noOfTasks++;
            wrapEchoMessage("Got it. I've added this task: \n       "
                    +newTask+"\n     Now you have "+noOfTasks+" tasks in the list");
        } catch (CasperEmptyTodoException e){
            wrapEchoMessage("What did you want to do? Try again?\n\n"
                    +"     Make sure its in the format: \n"
                    +"       todo {description}");
        }
    }

    private static void echoTaskList() {
        System.out.println(SEPARATOR);
        if (noOfTasks==0) {
            System.out.println("     You have no tasks this time around.");
        } else {
            System.out.println("     Here are the tasks in your list:");
            for (int i=1; i<=noOfTasks; i++) {
                System.out.println("     "+i+". "+taskList.get(i-1));
            }
        }
        System.out.println(SEPARATOR);
    }

    private static boolean checkNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static int validateTargetedInput(String userInput){
        String[] userInputSplit = userInput.split(" ");
        if (userInputSplit.length==2 && checkNumeric(userInputSplit[1])) {
            int targetTaskNumber = Integer.parseInt(userInputSplit[1]);
            if (targetTaskNumber<=noOfTasks&&targetTaskNumber>0) {
                return targetTaskNumber;
            } else {
                wrapEchoMessage("That task does not exist!");
                return -1;
            }
        } else {
            wrapEchoMessage("Invalid input!");
            return -1;
        }
    }

    public static void main(String[] args) {
        echoGreetings();
        handleQueries();
        wrapEchoMessage("Alright, see you around!");
    }
}
