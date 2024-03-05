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
    private static final String BYE = "bye";
    private static final String LIST = "list";
    private static final String DELETE = "delete";
    private static final String UNMARK = "unmark";
    private static final String MARK = "mark";
    private static final String TODO = "todo";
    private static final String DEADLINE = "deadline";
    private static final String EVENT = "event";
    private static final String BY = "/by";
    private static final String FROM = "/from";
    private static final String TO = "/to";
    private static final String INPUT_MISMATCH_EXCEPTION_MESSAGE = "Skipper input a god damn number!";
    private static final String INDEX_OUT_OF_BOUNDS_EXCEPTION_MESSAGE = "Invalid Task Number! Skipper stop acting like Private!";
    private static final String KOWALSKI_EXCEPTION_MESSAGE = "Skipper your inputs are wrong! Try again!";
    private static final String DEFAULT_ERROR_MESSAGE = "Skipper pull up your socks!";
    private static final String TASK_DELETE_MESSAGE = "Damn Skipper, you've got some courage removing this task:";
    private static final String MARK_AS_DONE_MESSAGE = "Way to go Skipper! I've marked this task as done:";
    private static final String MARK_AS_NOT_DONE_MESSAGE = "C'mon Skipper, you're much better than that! I've marked this task as undone:";
    private static final String TODO_MESSAGE = "Skipper you've got this work to do:";
    private static final String DEADLINE_MESSAGE = "Skipper, I have recorded this deadline:";
    private static final String EVENT_MESSAGE = "Skipper I've noted this event in my calendar:";
    private static final String TWO_SPACE_GAP = "  ";

    /**
     * Used to check if the user has accurately input the deadline by stating the "/by"
     * @param deadlineDetails : Contains the details of the deadline task
     * @throws KowalskiException In the event that the input has no "/by"
     */
    public static void checkDeadlineInput(String deadlineDetails) throws KowalskiException{
        if (!(deadlineDetails.contains(BY))) {
            throw new KowalskiException();
        }
    }

    /**
     * Used to check if the user has accurately input the deadline by stating the "/from" and "/to"
     * @param eventDetails : Contains the details of the event task
     * @throws KowalskiException In the event that the input has no "/from" or "/to" or both
     */
    public static void checkEventInput(String eventDetails) throws KowalskiException{
        if (!((eventDetails.contains(FROM)) && (eventDetails.contains(TO)))) {
            throw new KowalskiException();
        }
    }

    /**
     * Cleans up the user input and forms new Deadline Task
     * @param deadlineDetails : User input for details of the deadline task
     * @return new deadline task created
     */
    private static Task getNewDeadlineTask(String deadlineDetails) {
        String[] deadlineArray = deadlineDetails.split(BY);
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
        String[] eventArray = eventDetails.split(FROM);
        String eventInformation = eventArray[0].trim();
        String [] fromAndTo = eventArray[1].split(TO);
        String eventFrom = fromAndTo[0].trim();
        String eventTo = fromAndTo[1].trim();

        return new Event(eventInformation, eventFrom, eventTo);
    }

    public static void parseUserCommand(String UserCommand, List<Task> currentTask, Scanner in) {

        int taskNumber;
        int lastTaskIndex;

        switch (UserCommand){
        case BYE:
            break;

        case LIST:
            TaskList.printCurrentTaskItems();
            Ui.printDivider();
            break;

        case DELETE:
            try {
                taskNumber = in.nextInt();
            } catch (InputMismatchException e) {
                System.out.println(INPUT_MISMATCH_EXCEPTION_MESSAGE);
                break;
            }

            try {
                System.out.println(TASK_DELETE_MESSAGE);
                System.out.println(TWO_SPACE_GAP + currentTask.get(taskNumber - 1));
                Ui.printCurrentTaskMessage(currentTask.size()-1);
                currentTask.remove(taskNumber-1);
                Storage.writeText(currentTask);
            } catch (IndexOutOfBoundsException e){
                System.out.println(INDEX_OUT_OF_BOUNDS_EXCEPTION_MESSAGE);
            }
            Ui.printDivider();
            break;

        case UNMARK:
            try {
                taskNumber = in.nextInt();
            } catch (InputMismatchException e) {
                System.out.println(INPUT_MISMATCH_EXCEPTION_MESSAGE);
                Ui.printDivider();
                break;
            }

            try{
                currentTask.get(taskNumber - 1).markAsNotDone();
                System.out.println(MARK_AS_NOT_DONE_MESSAGE);
                System.out.println(TWO_SPACE_GAP + currentTask.get(taskNumber - 1));
                Storage.writeText(currentTask);
            } catch (IndexOutOfBoundsException e) {
                System.out.println(INDEX_OUT_OF_BOUNDS_EXCEPTION_MESSAGE);
                Ui.printDivider();
                break;
            }
            break;

        case MARK:
            try {
                taskNumber = in.nextInt();
            } catch (InputMismatchException e) {
                System.out.println(INPUT_MISMATCH_EXCEPTION_MESSAGE);
                Ui.printDivider();
                break;
            }

            try{
                currentTask.get(taskNumber - 1).markAsDone();
                System.out.println(MARK_AS_DONE_MESSAGE);
                System.out.println(TWO_SPACE_GAP + currentTask.get(taskNumber - 1));
                Storage.writeText(currentTask);
            } catch (IndexOutOfBoundsException e) {
                System.out.println(INDEX_OUT_OF_BOUNDS_EXCEPTION_MESSAGE);
                Ui.printDivider();
                break;
            }
            Ui.printDivider();
            break;

        case TODO:
            String toDoDetails = in.nextLine();

            Task newToDoTask = new Todo(toDoDetails.trim());
            currentTask.add(newToDoTask);
            lastTaskIndex = currentTask.size() - 1;

            System.out.println(TODO_MESSAGE);
            System.out.println(TWO_SPACE_GAP + currentTask.get(lastTaskIndex));
            Ui.printCurrentTaskMessage(currentTask.size());
            Storage.writeText(currentTask);
            Ui.printDivider();
            break;

        case DEADLINE:
            String deadlineDetails = in.nextLine();

            try{
                checkDeadlineInput(deadlineDetails);

                //Adding the new deadline task into currentTask List after processing and cleaning inputs
                Task newDeadlineTask = getNewDeadlineTask(deadlineDetails);
                currentTask.add(newDeadlineTask);
                lastTaskIndex = currentTask.size() - 1;

                //Printing the appropriate information for the User
                System.out.println(DEADLINE_MESSAGE);
                System.out.println(TWO_SPACE_GAP + currentTask.get( lastTaskIndex));
                Ui.printCurrentTaskMessage(currentTask.size());
                Storage.writeText(currentTask);
                Ui.printDivider();
            } catch (KowalskiException e){
                System.out.println(KOWALSKI_EXCEPTION_MESSAGE);
                Ui.printDivider();
            }
            break;

        case EVENT:
            String eventDetails = in.nextLine();

            try{
                checkEventInput(eventDetails);

                //Adding the new event task into currentTask List after processing and cleaning inputs
                Task newEventTask = getNewEventTask(eventDetails);
                currentTask.add(newEventTask);
                lastTaskIndex = currentTask.size() - 1;

                //Printing the appropriate information for the User
                System.out.println(EVENT_MESSAGE);
                System.out.println(TWO_SPACE_GAP + currentTask.get(lastTaskIndex));
                Ui.printCurrentTaskMessage(currentTask.size());
                Storage.writeText(currentTask);
                Ui.printDivider();
            } catch (KowalskiException e) {
                System.out.println(KOWALSKI_EXCEPTION_MESSAGE);
                Ui.printDivider();
            }
            break;


        default:
            System.out.println(DEFAULT_ERROR_MESSAGE+ System.lineSeparator());
            Ui.printHelpCommands();
            Ui.printDivider();
            break;
        }
    }

}
