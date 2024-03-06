import Exceptions.ArgumentNotFoundException;
import Exceptions.TaskNotFoundException;
import Tasks.Task;
import Tasks.Todo;

import java.io.IOException;
import java.util.ArrayList;

/**
 * The TaskList class manages a list of tasks and provides methods to
 * process various user commands.
 */
public class TaskList{
    private final ArrayList<Task> taskArray;
    private final Parser parser = new Parser();
    private final Ui ui = new Ui();

    public TaskList() throws IOException {
        // Read the data from the file and store it in the taskArray
        this.taskArray = DataHandler.readFileContents(DataHandler.FILE_PATH);
    }

    /**
     *  Processes and initiate the command to delete a task from the list.
     */
    public void processDelete(String input) {
        int indexDelete = parser.getIndexDelete(input);
        ui.displayDeleteMsg(taskArray.get(indexDelete), taskArray.size());
        taskArray.remove(indexDelete);
    }

    /**
     *  Processes and initiate the command to add an Event to the list.
     */
    public void processEvent(String input) throws ArgumentNotFoundException {
        Task newEvent = new Todo(parser.parseEvent(input));
        taskArray.add(newEvent);
        ui.displayAcknowledgement(newEvent, taskArray.size());
    }

    /**
     *  Processes and initiate the command to add a Deadline to the list.
     */
    public void processDeadline(String input) throws ArgumentNotFoundException {
        Task newDeadline = new Todo(parser.parseDeadline(input));
        taskArray.add(newDeadline);
        ui.displayAcknowledgement(newDeadline, taskArray.size());
    }

    /**
     *  Processes and initiate the command to add a Todo to the list.
     */
    public void processTodo(String input) throws ArgumentNotFoundException {
        Task newTodo = new Todo(parser.parseTodo(input));
        taskArray.add(newTodo);
        ui.displayAcknowledgement(newTodo, taskArray.size());
    }

    /**
     *  Processes and initiate the command to mark a task as
     *  not done in the list.
     */
    public void processUnmark(String input) throws ArgumentNotFoundException, TaskNotFoundException {
        int indexUnmark = parser.getIndexUnmark(taskArray, input);
        taskArray.get(indexUnmark).setDone(false);
        ui.displayUnmarkMsg(indexUnmark, taskArray);
    }

    /**
     *  Processes and initiate the command to mark a task as
     *  done in the list.
     */
    public void processMark(String input) throws ArgumentNotFoundException, TaskNotFoundException {
        int indexMark = parser.getIndexMark(taskArray, input);
        taskArray.get(indexMark).setDone(true);
        ui.displayMarkMsg(indexMark, taskArray);
    }

    public void printList() {
        ui.displayList(taskArray);
    }

    public void updateTaskList() throws IOException {
        // Writes the taskArray to the file
        DataHandler.tasksToFile(taskArray);
    }
}