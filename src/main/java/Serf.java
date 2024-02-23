import taskmanager.ConditionHandlers;
import taskmanager.Messages;
import taskmanager.Task;

import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
public class Serf {
    public static void main(String[] args) {
        Task[] taskList = new Task[100];
        int taskCounter = 0; // tracks number of tasks in taskList
        String chatBotName = "Serf";
        Messages.printGreetingMessage(chatBotName);

        //load data from save file to taskList
        File saveFile = new File("./Serf.txt");
        try {
            if (!saveFile.isFile()) {
                saveFile.createNewFile();
            }
        } catch (java.io.IOException e){
            System.out.println("     Sire I believe you have already created this file called 'Serf.txt'");
        }
        try {
            Scanner loadMessage = new Scanner(saveFile);

            while (loadMessage.hasNext()) {
                String store = loadMessage.nextLine();
                if (store.contains("todo")) {
                    taskCounter = ConditionHandlers.addTodoTaskToList(store, taskList, taskCounter);
                    //System.out.println(loadMessage.nextLine());
                } else if (store.contains("deadline")) {
                    taskCounter = ConditionHandlers.addDeadlineTaskToList(store, taskList, taskCounter);
                    //System.out.println(loadMessage.nextLine());
                } else if (store.contains("event")) {
                    taskCounter = ConditionHandlers.addEventTaskToList(store, taskList, taskCounter);
                    //System.out.println(loadMessage.nextLine());
                } else {
                    break;
                }
            }
            loadMessage.close();
        } catch (java.io.FileNotFoundException e){
            System.out.println("Sire, the file is not created yet");
        } catch (java.util.NoSuchElementException e) {
            System.out.println("Sire, there is nothing else in the save file to load");
        }

        //start reading in inputs from user
        Scanner requestedMessage = new Scanner(System.in);
        String receivedMessage = requestedMessage.nextLine();


        while(!receivedMessage.equals("bye")) {

            if (receivedMessage.equals("list") && taskCounter == 0) { // list is empty
                Messages.listIsEmptyMessage();
                receivedMessage = requestedMessage.nextLine();
                continue;
            } else if (receivedMessage.equals("list") && taskCounter > 0) { // list is not empty
                ConditionHandlers.listIsNotEmpty(taskList, taskCounter);
            } else if (receivedMessage.contains("mark") && !receivedMessage.contains("unmark")) {
                // user keys in mark
                ConditionHandlers.markTask(receivedMessage, taskList);
            } else if (receivedMessage.contains("unmark")) { // user keys in unmark
                ConditionHandlers.unmarkTask(receivedMessage, taskList);
            } else if (receivedMessage.contains("todo")) { //user keys in todo
                taskCounter = ConditionHandlers.addTodoTaskToList(receivedMessage, taskList, taskCounter);
            } else if (receivedMessage.contains("deadline")) { //user keys in deadline
                taskCounter = ConditionHandlers.addDeadlineTaskToList(receivedMessage, taskList, taskCounter);
            } else if (receivedMessage.contains("event")) { //user keys in event
                taskCounter = ConditionHandlers.addEventTaskToList(receivedMessage, taskList, taskCounter);
            } else {
                Messages.typoErrorMessage();
            }
            if (requestedMessage.hasNextLine()) { //check if user added another line
                receivedMessage = requestedMessage.nextLine();
            } else {
                break;
            }
        }
        saveFile.delete();
        // load tasks in task list to save file
        FileWriter loadFile = null;
        try {
            loadFile = new FileWriter("./Serf.txt", true);
            for (int iterator = 0; iterator < taskCounter; iterator += 1) {
                if (taskList[iterator].getTaskType().equals("T")) {
                    loadFile.write("todo " + taskList[iterator].getDescription() + System.lineSeparator());
                } else if (taskList[iterator].getTaskType().equals("D")) {
                    loadFile.write("deadline " + taskList[iterator].getDescription()
                            + " /by " + taskList[iterator].getEndDate() + System.lineSeparator());
                } else if (taskList[iterator].getTaskType().equals("E")) {
                    loadFile.write("event " + taskList[iterator].getDescription()
                            + " /from " + taskList[iterator].getStartDate()
                            + " /by " + taskList[iterator].getEndDate() + System.lineSeparator());
                } else {
                    System.out.println("error");
                }
            }
        } catch (java.io.IOException e) {
            System.out.println("Sire there is an error with the load file please try again");
        } finally {
            try {
                loadFile.flush();
                loadFile.close();
            } catch (java.io.IOException e){
                System.out.println("Sire there is an error with the load file please try again");
            }
        }
        Messages.printFarewellMessage();
    }
}