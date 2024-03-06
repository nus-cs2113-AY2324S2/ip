import filemanager.Storage;
import taskmanager.Ui;
import taskmanager.Task;
import taskmanager.TaskList;

import java.util.Scanner;
import java.util.ArrayList;

import java.io.File;

/**
 * <h1> Serf, your very own medieval servant </h1>
 * Serf is a task keeper. He keep tracks of the tasks you have to do and start and end time of each task (If applicable)
 *
 * @author ChinYanXu
 * @version 0.1
 * @since 2024-03-06
 */

public class Serf {

    /**
     * The program greets the user and load the tasks from the save file into the task list
     * Then continuously read in user commands and process user commands based on type of commands given
     * If the user writes "bye", the program will load the tasks into a new save file and delete the old save file
     * Them the program exits with a farewell message
     *
     * @param args User commands
     */
    public static void main(String[] args) {
        ArrayList<Task> taskList = new ArrayList<Task>();
        int taskCounter = 0; // tracks number of tasks in taskList
        String chatBotName = "Serf";
        Ui.printGreetingMessage(chatBotName);

        //load data from save file to taskList
        File saveFile = new File("./Serf.txt");

        taskList = Storage.loadTasksFromFile(saveFile, taskCounter, taskList);
        taskCounter = taskList.size();

        //start reading in inputs from user
        Scanner requestedMessage = new Scanner(System.in);
        String receivedMessage = requestedMessage.nextLine();


        while(!receivedMessage.equals("bye")) {

            taskList = TaskList.processUserInputIntoTaskList(receivedMessage, taskCounter, requestedMessage, taskList);
            taskCounter = taskList.size();
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