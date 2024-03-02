package Parser;

import Ruby.TaskList;

import java.util.Arrays;
import Exception.*;

public class Parser {
    public String userInput;
    public String[] userInputs;

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

    public void parseMarkTask(TaskList t1) {
        try {
            t1.markTask(Integer.parseInt(userInputs[1]));
        }catch (NumberFormatException e){
            print ("Sorry, please input number.");
        }catch (IndexOutOfBoundsException eee){
            print ("Sorry, I cannot find your task 2.");
        }
    }

    public void parseUnmarkTask(TaskList t1) {
        try {
            t1.unmarkTask(Integer.parseInt(userInputs[1]));
        }catch (NumberFormatException e){
            print ("Sorry, please input number.");
        }catch (IndexOutOfBoundsException eee){
            print ("Sorry, I cannot find your task 2.");
        }
    }

    public void parseAddTask(TaskList t1) {
        try{
            t1.addTask(userInput);
        }catch (ArrayIndexOutOfBoundsException e){
            print ("Ruby requires more details about your task.");
        }catch(StringIndexOutOfBoundsException e){
            print ("Your input format is not correct. Please check one more time.");
        }
    }
    public void parseDeleteTask(TaskList t1) {
        try {
            t1.deleteTask(Integer.parseInt(userInputs[1]));
        }catch (NumberFormatException e){
            print ("Sorry, please input number.");
        }catch (IndexOutOfBoundsException eee){
            print ("Sorry, I cannot find your task.");
        }
    }

    public void parseDefault(){
        try {
            keywordCatcher(userInput);
        }catch (InvalidKeywordException e){
            print("Sorry sir. I am not intelligent enough to know what that means.");
        }
    }

    public void parseFindTask(TaskList t1){
        t1.findTask(userInput);
    }

    public static void keywordCatcher(String userInput) throws InvalidKeywordException {
        String[] taskTypeList = {"todo", "deadline", "event"};
        String[] inputBreakdown = userInput.split(" ");
        if (!Arrays.asList(taskTypeList).contains(inputBreakdown[0])){
            throw new InvalidKeywordException();
        }
    }

    private static void print(String thingToPrint){
        System.out.println("    " + "---REMINDER---");
        System.out.println("    " + thingToPrint);
        System.out.println("    " + "--------------");
    }
}
