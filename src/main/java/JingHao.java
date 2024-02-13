import tasktype.Deadline;
import tasktype.Event;
import tasktype.Task;
import tasktype.Todo;

import java.util.Scanner;
public class JingHao {
    private static final String LINE_SEP = "____________________________________________________________";
    private static final int MAX_SIZE = 100;
    protected Task[] taskList;
    protected int numberOfTask;
    protected Scanner in;

    public JingHao() {
        this.numberOfTask = 0;
        this.taskList = new Task[MAX_SIZE];
        this.in = new Scanner(System.in);
    }
    public void start(){
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
            handleInput(userInput);

        }
        while(!userInput.equalsIgnoreCase("bye"));
    }

    private void handleInput(String userInput){
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
            for (int i = 0; i < numberOfTask; i++) {
                //System.out.println(i+1 + ".[" +taskList[i].getStatusIcon()+ "] " + taskList[i].description);
                System.out.println(taskList[i]);
            }
            System.out.println(LINE_SEP);
        }
    }

    private void handleMarkCommand(String description){
        try {
            int index = Integer.parseInt(description)-1;
            taskList[index].check();
            System.out.println("Nice! I've marked this task as done:\n  "+ taskList[index]);
            System.out.println(LINE_SEP);
        } catch (Exception e){
            System.out.println("Invalid index. Please try again\n" + LINE_SEP);
        }
    }
    private void handleUnmarkCommand(String description){
        try {
            int index = Integer.parseInt(description)-1;
            taskList[index].uncheck();
            System.out.println("OK, I've marked this task as not done yet:\n  "+ taskList[index]);
            System.out.println(LINE_SEP);
        } catch (Exception e){
            System.out.println("Invalid index. Please try again\n" + LINE_SEP);
        }
    }
    private void handleTodoCommand(String userInput){
        Task newTodo = new Todo(userInput);
        taskList[numberOfTask] = newTodo;
        numberOfTask++;
        System.out.println("Got it. I've added this task:\n " + newTodo);
        printTotalTask();
        System.out.println(LINE_SEP);
    }
    private void handleDeadlineCommand(String userInput){
        String[] deadlineDescription = userInput.split("/by",2);
        if(deadlineDescription.length == 2){
            String description = deadlineDescription[0];
            String date = deadlineDescription[1];
            Task newDeadline = new Deadline(description, date);
            taskList[numberOfTask] = newDeadline;
            numberOfTask++;
            System.out.println("Got it. I've added this task:\n " + newDeadline);
            printTotalTask();
            System.out.println(LINE_SEP);
        }
        else{
            System.out.println("Invalid deadline command.\n" + LINE_SEP);
        }
    }
    private void handleEventCommand(String userInput){
        String[] eventDescriptions = userInput.split("/from",2);
        if(eventDescriptions.length == 2){
            String description = eventDescriptions[0];
            String[] eventTime = eventDescriptions[1].split("/to", 2);
            if(eventTime.length == 2){
                String fromDate = eventTime[0];
                String toDate = eventTime[1];
                Task newEvent = new Event(description,fromDate,toDate);
                taskList[numberOfTask] = newEvent;
                numberOfTask++;
                System.out.println("Got it. I've added this task:\n " +newEvent);
                printTotalTask();
                System.out.println(LINE_SEP);
                return;
            }
        }
        System.out.println("Please use the format: deadline (event) /from (Start date) /to (End date)\n" + LINE_SEP);
    }
    private void printTotalTask(){
        System.out.println("Now you have " + numberOfTask + " tasks in the list.");
    }

}
