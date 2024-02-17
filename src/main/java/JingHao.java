import commandexceptions.InvalidDeadlineCommandException;
import commandexceptions.InvalidEventCommandException;
import commandexceptions.InvalidTodoCommandException;
import commandexceptions.JingHaoExceptions;
import storage.Storage;
import tasktype.*;

import java.io.File;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;
import java.nio.file.Paths;
public class JingHao {
    private static final String LINE_SEP = "____________________________________________________________";
    protected static TaskList taskList;
    protected int numberOfTask;
    protected Scanner in;

    public JingHao() {
        try {
            File f = new File("data/tasklist.txt");
            if (!f.exists()) {
                Files.createFile(Paths.get("data/tasklist.txt"));
            }
            taskList = Storage.readFile();
        } catch (IOException e) {
            // Handle IOException here
            System.out.println("Something went wrong: " + e.getMessage());
            return;
        } catch (JingHaoExceptions e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
        this.numberOfTask = taskList.size();
        this.in = new Scanner(System.in);
    }

    public void start(){
        /*try {
            File f = new File("data/tasklist.txt");
            if (!f.exists()) {
                Files.createFile(Paths.get("data/tasklist.txt"));
            }
            taskList = Storage.readFile();
        } catch (IOException e) {
            // Handle IOException here
            System.out.println("Something went wrong: " + e.getMessage());
            return;
        } catch (JingHaoExceptions e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }*/
        greetUser();
        getUserInput();
    }

    private void greetUser(){
        System.out.println(LINE_SEP + "\nHello! I'm JingHao" );
        System.out.println("What can I do for you?\n" + LINE_SEP);
    }

    private void getUserInput(){
        String userInput;
        do{
            System.out.print("Input: ");
            userInput = in.nextLine();
            try {
                handleInput(userInput);
                Storage.updateDisk(taskList);
            }
            catch (InvalidTodoCommandException e){
                System.out.println("Invalid Todo Command Format!\n" +
                        "Use: todo (Todo description)\n" + LINE_SEP);
            }
            catch (InvalidDeadlineCommandException e){
                System.out.println("Invalid Deadline Command Format!\n" +
                        "Use: deadline (Deadline description) + /by + (date)\n" + LINE_SEP);
            }
            catch (InvalidEventCommandException e){
                System.out.println("Invalid Deadline Command Format!\n" +
                        "Use: event (Event description) + /from (date) /to (date)\n" + LINE_SEP);
            } catch (IOException e) {
                System.out.println("Error updating database");
            }
        }
        while(!userInput.equalsIgnoreCase("bye"));
    }

    private void handleInput(String userInput)
            throws InvalidTodoCommandException,
            InvalidEventCommandException,
            InvalidDeadlineCommandException{

        String[] words = userInput.split(" ", 2);
        String command = words[0].toLowerCase();
        String description = (words.length == 2) ? words[1] : "";
        switch (command){
        case "bye":
            handleByeCommand();
            break;
        case "list":
            handleListCommand();
            break;
        case "mark":
            handleMarkCommand(description);
            break;
        case "unmark":
            handleUnmarkCommand(description);
            break;
        case "todo":
            handleTodoCommand(description);
            break;
        case "deadline":
            handleDeadlineCommand(description);
            break;
        case "event":
            handleEventCommand(description);
            break;
        default:
            System.out.println("unknown command\n" + LINE_SEP);
        }
    }

    private void handleByeCommand(){
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE_SEP);
    }

    private void handleListCommand(){
        if (numberOfTask == 0) {
            System.out.println("You have no task\n"+ LINE_SEP);
        } else {
            System.out.println("Here are the tasks in your list:");
            for (Task item: taskList) {
                System.out.println(item);
            }
            System.out.println(LINE_SEP);
        }
    }

    private void handleMarkCommand(String description){
        try {
            int index = Integer.parseInt(description)-1;
            taskList.get(index).check();
            System.out.println("Nice! I've marked this task as done:\n  "+ taskList.get(index));
            System.out.println(LINE_SEP);
        } catch (Exception e){
            System.out.println("Invalid index. Please try again\n" + LINE_SEP);
        }
    }

    private void handleUnmarkCommand(String description){
        try {
            int index = Integer.parseInt(description)-1;
            taskList.get(index).uncheck();
            System.out.println("OK, I've marked this task as not done yet:\n  "+ taskList.get(index));
            System.out.println(LINE_SEP);
        } catch (Exception e){
            System.out.println("Invalid index. Please try again\n" + LINE_SEP);
        }
    }

    private void handleTodoCommand(String userInput) throws InvalidTodoCommandException{
        if(userInput.isBlank()){
            throw new InvalidTodoCommandException();
        }
        Task newTodo = new Todo(userInput);
        taskList.add(newTodo);
        numberOfTask++;
        System.out.println("Got it. I've added this task:\n " + newTodo);
        printTotalTask();
        System.out.println(LINE_SEP);
    }

    private void handleDeadlineCommand(String userInput) throws InvalidDeadlineCommandException{
        String[] deadlineDescription = userInput.split("/by");
        if(deadlineDescription.length != 2){
            throw new InvalidDeadlineCommandException();
        }
        String description = deadlineDescription[0];
        String date = deadlineDescription[1];
        Task newDeadline = new Deadline(description, date);
        taskList.add(newDeadline);
        numberOfTask++;
        System.out.println("Got it. I've added this task:\n " + newDeadline);
        printTotalTask();
        System.out.println(LINE_SEP);
    }

    private void handleEventCommand(String userInput) throws InvalidEventCommandException{
        String[] eventDescriptions = userInput.split("/from", 2);
        if(eventDescriptions.length != 2 || eventDescriptions[0].isBlank() || eventDescriptions[1].isBlank()){
            throw new InvalidEventCommandException();
        }
        String description = eventDescriptions[0];
        String[] eventTime = eventDescriptions[1].split("/to", 2);
        if(eventTime.length != 2 || eventTime[0].isBlank() || eventTime[1].isBlank()){
            throw new InvalidEventCommandException();
        }
        String fromDate = eventTime[0];
        String toDate = eventTime[1];
        Task newEvent = new Event(description,fromDate,toDate);
        taskList.add(newEvent);
        numberOfTask++;
        System.out.println("Got it. I've added this task:\n " +newEvent);
        printTotalTask();
        System.out.println(LINE_SEP);
    }

    private void printTotalTask(){
        System.out.println("Now you have " + numberOfTask + " tasks in the list.");
    }
}
