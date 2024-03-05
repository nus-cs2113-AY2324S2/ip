package Kowalski;

import Kowalski.UI.Ui;
import Kowalski.commands.KowalskiException;
import Kowalski.tasks.Deadline;
import Kowalski.tasks.Event;
import Kowalski.tasks.Task;
import Kowalski.tasks.Todo;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Parser {

    /**
     * Used to check if the user has accurately input the deadline by stating the "/by"
     * @param deadlineDetails : Contains the details of the deadline task
     * @throws KowalskiException In the event that the input has no "/by"
     */
    public static void checkDeadlineInput(String deadlineDetails) throws KowalskiException{
        if (!(deadlineDetails.contains("/by"))) {
            throw new KowalskiException();
        }
    }

    /**
     * Used to check if the user has accurately input the deadline by stating the "/from" and "/to"
     * @param eventDetails : Contains the details of the event task
     * @throws KowalskiException In the event that the input has no "/from" or "/to" or both
     */
    public static void checkEventInput(String eventDetails) throws KowalskiException{
        if (!((eventDetails.contains("/from")) && (eventDetails.contains("/to")))) {
            throw new KowalskiException();
        }
    }

    /**
     * Cleans up the user input and forms new Deadline Task
     * @param deadlineDetails : User input for details of the deadline task
     * @return new deadline task created
     */
    private static Task getNewDeadlineTask(String deadlineDetails) {
        String[] deadlineArray = deadlineDetails.split("/by");
        for (int i = 0; i < deadlineArray.length; i++) {
            deadlineArray[i] = deadlineArray[i].trim();
        }


        return new Deadline(deadlineArray[0], deadlineArray[1]);
    }

    /**
     * Cleans up the user input and forms new event Task
     * @param eventDetails : User input for details of the event task
     * @return new event task created
     */
    private static Task getNewEventTask(String eventDetails) {
        String[] eventArray = eventDetails.split("/from");
        String eventInformation = eventArray[0].trim();
        String [] fromAndTo = eventArray[1].split("/to");
        String eventFrom = fromAndTo[0].trim();
        String eventTo = fromAndTo[1].trim();

        return new Event(eventInformation, eventFrom, eventTo);
    }

    public static void parseUserCommand(String UserCommand, List<Task> currentTask, Scanner in) {

        int taskNumber;
        int lastTaskIndex;

        switch (UserCommand){
        case "bye":
            break;

        case "list":
            TaskList.printCurrentTaskItems();
            Ui.printDivider();
            break;

        case "delete":
            try {
                taskNumber = in.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Skipper input a god damn number! I am now gonna add the text which you inputted into our list!");
                break;
            }

            try {
                System.out.println( "Damn Skipper, you're got some courage removing this task:");
                System.out.println("  " + currentTask.get(taskNumber - 1));
                Ui.printCurrentTaskMessage(currentTask.size()-1);
                currentTask.remove(taskNumber-1);
                Storage.writeText(currentTask);
            } catch (IndexOutOfBoundsException e){
                System.out.println("Invalid Task Number! Skipper stop acting like Private!");
            }
            Ui.printDivider();
            break;

        case "unmark":
            try {
                taskNumber = in.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Skipper input a god damn number! I am now gonna add the text which you inputted into our list!");
                Ui.printDivider();
                break;
            }

            try{
                currentTask.get(taskNumber - 1).markAsNotDone();
                System.out.println( "C'mon Skipper, you're much better than that! I've marked this task as undone:");
                System.out.println("  " + currentTask.get(taskNumber - 1));
                Storage.writeText(currentTask);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Invalid Task Number! Skipper stop acting like Private!");
                Ui.printDivider();
                break;
            }
            break;

        case "mark":
            try {
                taskNumber = in.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Skipper input a god damn number! I am now gonna add the text which you inputted into our list!");
                Ui.printDivider();
                break;
            }

            try{
                currentTask.get(taskNumber - 1).markAsDone();
                System.out.println( "Way to go Skipper! I've marked this task as done:");
                System.out.println("  " + currentTask.get(taskNumber - 1));
                Storage.writeText(currentTask);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Invalid Task Number! Skipper stop acting like Private!");
                Ui.printDivider();
                break;
            }
            Ui.printDivider();
            break;

        case "todo":
            String toDoDetails = in.nextLine();

            Task newToDoTask = new Todo(toDoDetails.trim());
            currentTask.add(newToDoTask);
            lastTaskIndex = currentTask.size() - 1;

            System.out.println("Skipper you've got this work to do:");
            System.out.println("  " + currentTask.get( lastTaskIndex));
            Ui.printCurrentTaskMessage(currentTask.size());
            Storage.writeText(currentTask);
            Ui.printDivider();
            break;

        case "deadline":
            String deadlineDetails = in.nextLine();

            try{
                checkDeadlineInput(deadlineDetails);

                //Adding the new deadline task into currentTask List after processing and cleaning inputs
                Task newDeadlineTask = getNewDeadlineTask(deadlineDetails);
                currentTask.add(newDeadlineTask);
                lastTaskIndex = currentTask.size() - 1;

                //Printing the appropriate information for the User
                System.out.println("Skipper, I have recorded this deadline:");
                System.out.println("  " + currentTask.get( lastTaskIndex));
                Ui.printCurrentTaskMessage(currentTask.size());
                Storage.writeText(currentTask);
                Ui.printDivider();
            } catch (KowalskiException e){
                System.out.println("Skipper your inputs are wrong! Try again!");
                Ui.printDivider();
            }
            break;

        case "event":
            String eventDetails = in.nextLine();

            try{
                checkEventInput(eventDetails);

                //Adding the new event task into currentTask List after processing and cleaning inputs
                Task newEventTask = getNewEventTask(eventDetails);
                currentTask.add(newEventTask);
                lastTaskIndex = currentTask.size() - 1;

                //Printing the appropriate information for the User
                System.out.println("Skipper I've noted this event in my calendar:");
                System.out.println("  " + currentTask.get(lastTaskIndex));
                Ui.printCurrentTaskMessage(currentTask.size());
                Storage.writeText(currentTask);
                Ui.printDivider();
            } catch (KowalskiException e) {
                System.out.println("Skipper your inputs are wrong! Try again!");
                Ui.printDivider();
            }
            break;


        default:
            System.out.println("Skipper pull up your socks!"
                    + System.lineSeparator());
            Ui.printHelpCommands();
            Ui.printDivider();
            break;
        }
    }

}
