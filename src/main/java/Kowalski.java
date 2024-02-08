import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;


public class Kowalski {

    private static final String DIVIDING_LINE = "____________________________________________________________";

    public static List <Task> currentTask = new ArrayList<>();
    public static Scanner in = new Scanner (System.in);

    /**
     * Prints out the message introducing the functionalities of Kowalski Bot
     *
     */
    public static void printIntro(){
        // Introduction to User
        System.out.println(DIVIDING_LINE);
        System.out.println("Welcome Skipper! I'm Kowalski, reporting for Duty!" + System.lineSeparator() +
                "What can I do for you today?" );
        System.out.println(DIVIDING_LINE);
    }

    /**
     * Used to process the different variations of the users inputs
     *
     * @param userInput : String which the user inputs
     * @return String which is in lowercase and clear of any unnecessary whitespace
     */
    public static String processInput(String userInput){
        return (userInput.trim()).toLowerCase();
    }

    public static void printCurrentTaskMessage(int number){
        switch (number){
        case 0:
            System.out.println("Now you have 0 tasks in the list.");
            break;
        case 1:
            System.out.println("Now you have 1 task in the list.");
            break;
        default:
            System.out.println("Now you have " + number + " tasks in the list.");
        }
    }

    public static void parseUserCommand(String UserCommand){

        int taskNumber;
        int lastTaskIndex = currentTask.size() - 1;
        String remainingCommand;

        switch (UserCommand){
        case "bye":
            break;

        case "list":
            for (int i = 1; i <= currentTask.size(); i++){
                    System.out.println(i + "." + currentTask.get(i-1));
                }
            System.out.println(DIVIDING_LINE);
            break;

        case "unmark":
            taskNumber = in.nextInt();

            if(taskNumber >= 0 || taskNumber < lastTaskIndex ){
                currentTask.get(taskNumber - 1).markAsNotDone();
                System.out.println( "Nice! I've marked this task as done:");
                System.out.println("  " + currentTask.get(taskNumber - 1));
            }
            System.out.println(DIVIDING_LINE);
            break;

        case "mark":
            taskNumber = in.nextInt();

            if(taskNumber >= 0 || taskNumber < lastTaskIndex ){
                currentTask.get(taskNumber - 1).markAsDone();
                System.out.println( "Nice! I've marked this task as done:");
                System.out.println("  " + currentTask.get(taskNumber - 1));
            }
            System.out.println(DIVIDING_LINE);
            break;

        case "todo":
            String toDoDetails = in.nextLine();

            Task newToDoTask = new Todo(toDoDetails.trim());
            currentTask.add(newToDoTask);
            lastTaskIndex = currentTask.size() - 1;

            System.out.println("Got it. I've added this task:");
            System.out.println("  " + currentTask.get( lastTaskIndex));
            printCurrentTaskMessage(currentTask.size());
            System.out.println(DIVIDING_LINE);
            break;

        case "deadline":
            //Processing
            String deadlineDetails = in.nextLine();

            if (deadlineDetails.contains("/by")) {
                String[] deadlineArray = deadlineDetails.split("/by");
                for (int i = 0; i < deadlineArray.length; i++) {
                    deadlineArray[i] = deadlineArray[i].trim();
                }

                Task newDeadlineTask = new Deadline(deadlineArray[0], deadlineArray[1]);
                currentTask.add(newDeadlineTask);
                lastTaskIndex = currentTask.size() - 1;

                System.out.println("Got it. I've added this task:");
                System.out.println("  " + currentTask.get( lastTaskIndex));
                printCurrentTaskMessage(currentTask.size());
                System.out.println(DIVIDING_LINE);
            } else {
                System.out.println("Something wrong with the input! Please try again!");
            }
            break;

        case "event":
            String eventDetails = in.nextLine();
            if ((eventDetails.contains("/from")) && (eventDetails.contains("/to"))) {
                String[] eventArray = eventDetails.split("/from");
                String eventInformation = eventArray[0].trim();
                String [] fromAndTo = eventArray[1].split("/to");
                String eventFrom = fromAndTo[0].trim();
                String eventTo = fromAndTo[1].trim();

                Task newEventTask = new Event(eventInformation, eventFrom, eventTo);
                currentTask.add(newEventTask);
                lastTaskIndex = currentTask.size() - 1;

                System.out.println("Got it. I've added this task:");
                System.out.println("  " + currentTask.get(lastTaskIndex));
                printCurrentTaskMessage(currentTask.size());
                System.out.println(DIVIDING_LINE);
            } else {
                System.out.println("Something wrong with the input! Please try again!");
            }
            break;


        default:
            remainingCommand = in.nextLine();
            Task newTask = new Task(UserCommand + remainingCommand);
            currentTask.add(newTask);
            System.out.println("added: " + UserCommand + remainingCommand);
            System.out.println(DIVIDING_LINE);
            break;
        }
    }

    /**
     * Prints out the message to end conversation with the user
     *
     */
    public static void printEndConversation(){
        System.out.println("Bye Skipper! Hope to serve you again for your next mission!");
        System.out.println(DIVIDING_LINE);
    }

    public static void main(String[] args){
        printIntro();
        String userCommand = processInput(in.next());

        while (!(userCommand.equals("bye"))){
            parseUserCommand(processInput(userCommand));
            userCommand = in.next();
        }

        printEndConversation();
    }
}
