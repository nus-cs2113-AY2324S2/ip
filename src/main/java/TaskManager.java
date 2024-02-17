import Exceptions.DeadlineLackInputsException;
import Exceptions.EventLackInputsException;
import Exceptions.TodoLackInputsException;
import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.Todo;

public class TaskManager {
    private int numItems;
    private Task[] taskList;

    public final int MAX_TASKS = 100;
    public final String TODO_REQUIRED_INPUTS = "'todo <task>'";
    public final String DEADLINE_REQUIRED_INPUTS = "'Deadline <task> /by <due date>'";
    public final String EVENT_REQUIRED_INPUTS = "'Event <task> /from <start date> /to <end date>'";
    

    public TaskManager() {
        this.numItems = 0;
        this.taskList = new Task[MAX_TASKS];
    }

    public void addListContents(String userInput) {
        Parser myParser = new Parser();
        try {
            String[] taskInformation = myParser.processTaskInformation(userInput);
            switch (taskInformation[0]) {
            case ("todo"):
                this.taskList[numItems] = new Todo(numItems, taskInformation[1], false);
                break;

            case ("deadline"):
                this.taskList[numItems] = new Deadline(numItems, taskInformation[1], false, taskInformation[2]);
                break;

            case ("event"):
                this.taskList[numItems] = new Event(numItems, taskInformation[1], false, taskInformation[2],
                        taskInformation[3]);
                break;

            case ("error"):
                //should not hit this line
                System.out.println("Please give a proper input for todo/deadline/event");
                break;

            default:
                break;
            }
            System.out.println("Got it. I've added this task:");
            System.out.println(taskList[numItems]);
            this.numItems += 1;
            String taskPluralString = numItems > 1 ? " tasks" : " task";
            System.out.println("Now you have " + numItems + taskPluralString + " in the list.");

        }
        catch (TodoLackInputsException e) {
            lackInputsErrorMessage(userInput, "todo", TODO_REQUIRED_INPUTS);
        }
        catch (DeadlineLackInputsException e) {
            lackInputsErrorMessage(userInput, "deadline", DEADLINE_REQUIRED_INPUTS);
        }
        catch (EventLackInputsException e) {
            lackInputsErrorMessage(userInput, "event", EVENT_REQUIRED_INPUTS);
        }
        catch (IndexOutOfBoundsException e) {
            // update this if /help is added
            if (userInput.contains("deadline")) {
                lackInputsErrorMessage(userInput, "deadline", DEADLINE_REQUIRED_INPUTS);
            } else if (userInput.contains("event")) {
                lackInputsErrorMessage(userInput, "event", EVENT_REQUIRED_INPUTS);
            }
        }

    }

    private static void lackInputsErrorMessage(String userInput, String errorType, String requiredInputs) {
        System.out.println("Your " + errorType + " task description seems to lack inputs. What you entered was " + userInput +
                ". Try typing it as " + requiredInputs);
    }

    public void showListContents() {
        if (this.numItems == 0) {
            System.out.println("List is empty. Please enter something first.");
        }
        else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < numItems; i += 1) {
                System.out.print(taskList[i].getId() + 1 + ". ");
                System.out.println(taskList[i]);
            }
        }
    }

    public static boolean isStringInteger(String number ){
        try{
            Integer.parseInt(number);
        }catch(Exception e ){
            return false;
        }
        return true;
    }

    public void changeTaskStatus(String userInput) {
        userInput = userInput.toLowerCase();
        String[] wordArray = userInput.split(" ");
        // need to check if the 1st index of the array is a valid number and an actual number
        if (wordArray.length != 2 || !isStringInteger(wordArray[1])) {
            System.out.println("Please give a command in the structure of \"mark x\" or \"unmark x\"" +
                    "where x is the task number");
            return;
        }

        int id = Integer.parseInt(wordArray[1]) - 1;
        if (id >= numItems || id < 0) {
            System.out.println("Please select a task number that exists. =)");
            return;
        }

        if (userInput.contains("unmark")) {
            taskList[id].setDone(false);
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("[ ] " + taskList[id].getContent());
        }
        // must contain mark at this point
        else {
            taskList[id].setDone(true);
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("[X] " + taskList[id].getContent());
        }
    }


}
