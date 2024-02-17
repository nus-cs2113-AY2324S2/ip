import Exceptions.DeadlineLackInputsException;
import Exceptions.EventLackInputsException;
import Exceptions.TodoLackInputsException;
import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.Todo;
import java.util.ArrayList;

public class TaskManager {
    private int numItems;
    ArrayList<Task> taskArrayList = new ArrayList<>();

    public final String TODO_REQUIRED_INPUTS = "'todo <task>'";
    public final String DEADLINE_REQUIRED_INPUTS = "'Deadline <task> /by <due date>'";
    public final String EVENT_REQUIRED_INPUTS = "'Event <task> /from <start date> /to <end date>'";
    

    public TaskManager() {
        this.numItems = 0;
    }
    Parser myParser = new Parser();
    public void addListContents(String userInput) {
        try {
            String[] taskInformation = myParser.processTaskInformation(userInput);
            switch (taskInformation[0]) {
            case ("todo"):
                taskArrayList.add(new Todo(taskInformation[1], false));
                break;

            case ("deadline"):
                taskArrayList.add(new Deadline(taskInformation[1], false, taskInformation[2]));
                break;

            case ("event"):
                taskArrayList.add(new Event(taskInformation[1], false, taskInformation[2],
                        taskInformation[3]));
                break;

            case ("error"):
                //should not hit this line
                System.out.println("Please give a proper input for todo/deadline/event");
                break;

            default:
                break;
            }
            System.out.println("Got it. I've added this task:");
            System.out.println(taskArrayList.get(numItems));
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
                System.out.print(i + 1 + ". ");
                System.out.println(taskArrayList.get(i));
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
        if (!isValidTaskId(userInput, "changeTaskStatus")) {
            return;
        }

        int id = myParser.processTaskIdforMarkingAndDeletingTask(userInput);

        if (userInput.contains("unmark")) {
            taskArrayList.get(id).setDone(false);
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(taskArrayList.get(id));

        }
        // must contain mark at this point
        else {
            taskArrayList.get(id).setDone(true);
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(taskArrayList.get(id));
        }
    }

    public boolean isValidTaskId(String userInput, String purpose) {
        userInput = userInput.toLowerCase();
        String[] wordArray = userInput.split(" ");
        String commandStructure;
        if (purpose.equals("changeTaskStatus")) {
            commandStructure = "\"mark x or unmark x\"";
        } else if (purpose.equals("deleteTask")) {
            commandStructure = "\"delete x\"";
        }
        else {
            System.out.println("not a valid purpose =(");
            return false;
        }

        if (wordArray.length != 2 || !isStringInteger(wordArray[1])) {
            System.out.println("Please give a command in the structure of " + commandStructure +
                    "where x is the task number");
            return false;
        }

        int id = Integer.parseInt(wordArray[1]) - 1;
        if (id >= numItems || id < 0) {
            System.out.println("Please select a task number that exists. =)");
            return false;
        }
        return true;
    }

    public void deleteTask(String userInput) {
        if (!isValidTaskId(userInput, "deleteTask")) {
            return;
        }
        int id = myParser.processTaskIdforMarkingAndDeletingTask(userInput);
        System.out.println("Noted. I've removed this task:");
        System.out.println(taskArrayList.get(id));
        this.numItems -= 1;
        String taskPluralString = numItems > 1 ? " tasks" : " task";
        System.out.println("Now you have " + numItems + taskPluralString + " in the list.");
        taskArrayList.remove(id);
    }
}
