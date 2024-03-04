import filemanager.Storage;
import taskmanager.Ui;
import taskmanager.Task;
import taskmanager.TaskList;

import java.util.Scanner;
import java.util.ArrayList;

import java.io.File;

public class Serf {
    public static void main(String[] args) {
        ArrayList<Task> taskList = new ArrayList<Task>();
        int taskCounter = 0; // tracks number of tasks in taskList
        String chatBotName = "Serf";
        Ui.printGreetingMessage(chatBotName);

        //load data from save file to taskList
        File saveFile = new File("./Serf.txt");

        taskCounter= Storage.loadTasksFromFile(saveFile, taskCounter, taskList);


        //start reading in inputs from user
        Scanner requestedMessage = new Scanner(System.in);
        String receivedMessage = requestedMessage.nextLine();


        while(!receivedMessage.equals("bye")) {

            TaskList.processUserInputIntoTaskList(receivedMessage, taskCounter, requestedMessage, taskList);
            if (requestedMessage.hasNextLine()) { //check if user added another line
                receivedMessage = requestedMessage.nextLine();
            } else {
                break;
            }
        }
        saveFile.delete();

        //load data from save file to taskList
        Storage.saveTaskListToFile(taskCounter, taskList);

        Ui.printFarewellMessage();
    }
}