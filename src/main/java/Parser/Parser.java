package Parser;

import Ruby.TaskList;

import java.util.Arrays;
import Exception.*;

/**
 * Parses user input into commands and executes corresponding actions on a TaskList.
 * Supports operations such as adding, marking, unmarking, deleting, and finding tasks.
 */
public class Parser {
    public String userInput;
    public String[] userInputs;

    /**
     * Parses the given user input into a command and its arguments.
     * The input is split by spaces and stored for further processing.
     *
     * @param userInput The raw input string from the user.
     * @return An array of strings where the first element is the command and subsequent elements are arguments.
     */
    public String[] inputParser(String userInput){
        this.userInput = userInput;
        this.userInputs = userInput.split(" ");
        return userInputs;
    }

    /**
     * Checks if the user's input is a command to continue or to terminate the session.
     *
     * @return true if the session should continue, false if it should terminate.
     */
    public boolean isTerminateCommand(){
        return !userInputs[0].equalsIgnoreCase("bye");
    }

    /**
     * Parses and executes the command to mark a task as completed in the task list.
     * Handles input validation and error messages.
     *
     * @param t1 The TaskList on which the operation is to be performed.
     */
    public void parseMarkTask(TaskList t1) {
        try {
            t1.markTaskAsCompleted(Integer.parseInt(userInputs[1]));
        }catch (NumberFormatException e){
            print ("Sorry, please input number.");
        }catch (IndexOutOfBoundsException eee){
            print ("Sorry, I cannot find your task 2.");
        }
    }

    /**
     * Parses and executes the command to mark a task as incomplete in the task list.
     * Handles input validation and error messages.
     *
     * @param t1 The TaskList on which the operation is to be performed.
     */
    public void parseUnmarkTask(TaskList t1) {
        try {
            t1.markTaskAsIncompleted(Integer.parseInt(userInputs[1]));
        }catch (NumberFormatException e){
            print ("Sorry, please input number.");
        }catch (IndexOutOfBoundsException eee){
            print ("Sorry, I cannot find your task 2.");
        }
    }

    /**
     * Parses and adds a task based on the user input to the task list.
     * Handles different task types and input validation.
     *
     * @param t1 The TaskList to which the task will be added.
     */
    public void parseAddTask(TaskList t1) {
        try{
            t1.addTask(userInput);
        }catch (ArrayIndexOutOfBoundsException e){
            print ("Ruby requires more details about your task.");
        }catch(StringIndexOutOfBoundsException e){
            print ("Your input format is not correct. Please check one more time.");
        }
    }

    /**
     * Parses and executes the command to delete a task from the task list.
     * Validates the task number and handles errors.
     *
     * @param t1 The TaskList from which the task will be deleted.
     */
    public void parseDeleteTask(TaskList t1) {
        try {
            t1.deleteTask(Integer.parseInt(userInputs[1]));
        }catch (NumberFormatException e){
            print ("Sorry, please input number.");
        }catch (IndexOutOfBoundsException eee){
            print ("Sorry, I cannot find your task.");
        }
    }

    /**
     * Parses and handles default or unknown commands.
     * Checks if the command matches any predefined keywords and throws an exception if not.
     */
    public void parseDefault(){
        try {
            keywordCatcher(userInput);
        }catch (InvalidKeywordException e){
            print("Sorry sir. I am not intelligent enough to know what that means.");
        }
    }

    /**
     * Parses and finds tasks in the task list that match the given description.
     * Handles input validation and error messages.
     *
     * @param t1 The TaskList to search for matching tasks.
     */
    public void parseFindTask(TaskList t1){
        try{
            t1.findTask(userInput);
        }catch (StringIndexOutOfBoundsException e){
            print("Sorry sir, please add the task description.");
        }
    }

    /**
     * Checks if the user input begins with a valid task type keyword.
     * Throws an InvalidKeywordException if the keyword is invalid.
     *
     * @param userInput The user input to check.
     * @throws InvalidKeywordException If the first word of the input is not a recognized task type keyword.
     */
    public static void keywordCatcher(String userInput) throws InvalidKeywordException {
        String[] taskTypeList = {"todo", "deadline", "event"};
        String[] inputBreakdown = userInput.split(" ");
        if (!Arrays.asList(taskTypeList).contains(inputBreakdown[0])){
            throw new InvalidKeywordException("Invalid Keyword.");
        }
    }

    /**
     * Prints a formatted message to the console.
     *
     * @param thingToPrint The message to be printed.
     */
    private static void print(String thingToPrint){
        System.out.println("    " + "---REMINDER---");
        System.out.println("    " + thingToPrint);
        System.out.println("    " + "--------------");
    }
}
