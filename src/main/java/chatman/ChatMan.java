package chatman;

import chatman.commands.GreetCommand;
import chatman.storage.StorageHandler;
import chatman.tasks.Task;
import chatman.utility.CommandReader;


import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Provides entry point for ChatMan.
 *
 * @author LWachtel1
 * */
public class ChatMan {
    public static final int MAX_NUM_TASKS = 100; //maximum number of tasks to be stored at once


    private static ArrayList<Task> storedTasks = new ArrayList<>(); //provides storage for task objects

    /**
     * Provides access to current list of stored tasks by returning arraylist reference.
     *
     * @return Reference to arraylist containing currently stored tasks.
     **/
    public static ArrayList<Task> accessTasks() {
        return storedTasks;
    }


    /**
     * Opens task storage file. If successful, prints greeting for user upon initial program execution then
     * instantiates CommandReader object and calls read() to trigger ChatMan loop execution.
     **/
    public static void main(String[] args) {

        try {
            StorageHandler.openStorageFile();
            GreetCommand hello= new GreetCommand(" ");
            hello.perform();

            CommandReader chatbot= new CommandReader();
            chatbot.read();
        } catch(IOException e) {
           System.out.println(e.getMessage());
        }


    }
}
