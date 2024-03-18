package chatman;

import chatman.commands.GreetCommand;
import chatman.tasks.Task;
import chatman.utility.CommandReader;


import java.util.Scanner;
import java.util.ArrayList;

/**
 * Provides entry point for ChatMan.
 *
 * @author LWachtel1
 * */
public class ChatMan {
    public static final int MAX_NUM_TASKS = 100; //maximum number of tasks to be stored at once

    //Need to make arraylist reference private and provide public static getter and setter instead
    public static ArrayList<Task> storedTasks = new ArrayList<>(); //provides storage for task objects

    /**
     * Prints greeting for user upon initial program execution then instantiates CommandReader object and calls read
     * () to trigger ChatMan loop execution.
     **/
    public static void main(String[] args) {
        GreetCommand hello= new GreetCommand(" ");
        hello.perform();

        CommandReader chatbot= new CommandReader();
        chatbot.read();

    }
}
