import Exceptions.TaskNotFoundException;
import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.Todo;
import Exceptions.ArgumentNotFoundException;
import Exceptions.CommandNotFoundException;


import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Spike {
    public static final int MARK_TASK_INDEX = 5;
    public static final int UNMARK_TASK_INDEX = 7;
    public static final int EVENT_TASK_INDEX = 6;
    public static final int DEADLINE_TASK_INDEX = 9;
    public static final int TODO_TASK_INDEX = 5;
    public static final int DELETE_TASK_INDEX = 7;

    public static void main(String[] args) throws IOException {
        Ui.displayWelcomeMsg();
        ArrayList<Task> inputList = DataHandler.readFileContents(DataHandler.FILE_PATH);
        Scanner in = new Scanner(System.in);

        while (true) {
            try {
                startChatbot(in, inputList);
                Ui.displayByeMsg();
                break;
            } catch (CommandNotFoundException e) {
                System.out.println("Command Not Found! Please Try Again");
            } catch (ArgumentNotFoundException e) {
                System.out.println("Argument Not Found! Please Try Again");
            } catch (TaskNotFoundException e) {
                System.out.println("Task Not Found! Please Try Again");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


    private static void startChatbot(Scanner in, ArrayList<Task> inputList)
            throws CommandNotFoundException, ArgumentNotFoundException, TaskNotFoundException, IOException {
        outerLoop:
        while (true) {
            String input = in.nextLine();
            switch (input.split(" ")[0]) {
            case "list":
                Ui.displayList(inputList);
                break;
            case "mark":
                if (MARK_TASK_INDEX > input.length()) {
                    throw new ArgumentNotFoundException();
                }
                int indexMark = Integer.parseInt(input.substring(MARK_TASK_INDEX)) - 1;
                if (inputList.get(indexMark) == null) {
                    throw new TaskNotFoundException();
                }
                inputList.get(indexMark).setDone(true);
                Ui.displayMarkMsg(indexMark, inputList);
                break;
            case "unmark":
                if (UNMARK_TASK_INDEX > input.length()) {
                    throw new ArgumentNotFoundException();
                }
                int indexUnmark = Integer.parseInt(input.substring(UNMARK_TASK_INDEX)) - 1;
                if (inputList.get(indexUnmark) == null) {
                    throw new TaskNotFoundException();
                }
                inputList.get(indexUnmark).setDone(false);
                Ui.displayUnmarkMsg(indexUnmark, inputList);
                break;
            case "todo":
                Task newTodo = new Todo(processTodo(input));
                inputList.add(newTodo);
                Ui.displayAcknowledgement(newTodo, inputList.size());
                break;
            case "deadline":
                Task newDeadline = new Todo(processDeadline(input));
                inputList.add(newDeadline);
                Ui.displayAcknowledgement(newDeadline, inputList.size());
                break;
            case "event":
                Task newEvent = new Todo(processEvent(input));
                inputList.add(newEvent);
                Ui.displayAcknowledgement(newEvent, inputList.size());
                break;
            case "delete":
                int indexDelete = Integer.parseInt(input.substring(DELETE_TASK_INDEX)) - 1;
                Ui.displayDeleteMsg(inputList.get(indexDelete), inputList.size());
                inputList.remove(indexDelete);
                break;
            case "bye":
                break outerLoop;
            default:
                throw new CommandNotFoundException();
            }
            DataHandler.tasksToFile(inputList);
        }
    }

    private static String processEvent(String input) throws ArgumentNotFoundException {
        if (EVENT_TASK_INDEX > input.length()) {
            throw new ArgumentNotFoundException();
        }
        String event = input.substring(EVENT_TASK_INDEX);
        String[] parts = event.split(" /from ");
        String[] time = parts[1].split(" /to ");
        return parts[0] + " (from: " + time[0] + " to: " + time[1] + ")";
    }

    private static String processDeadline(String input) throws ArgumentNotFoundException {
        if (DEADLINE_TASK_INDEX > input.length()) {
            throw new ArgumentNotFoundException();
        }
        String deadline = input.substring(DEADLINE_TASK_INDEX);
        String[] parts = deadline.split(" /by ");
        return parts[0] + " (by: " + parts[1] + ")";
    }

    private static String processTodo(String input) throws ArgumentNotFoundException {
        if (TODO_TASK_INDEX > input.length()) {
            throw new ArgumentNotFoundException();
        }
        return input.substring(TODO_TASK_INDEX);
    }
}
