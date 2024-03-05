import Exceptions.ArgumentNotFoundException;
import Exceptions.TaskNotFoundException;
import Tasks.Task;
import Tasks.Todo;

import java.io.IOException;
import java.util.ArrayList;

public class TaskList{
    private final ArrayList<Task> taskArray;
    private final Parser parser = new Parser();
    private final Ui ui = new Ui();

    public TaskList() throws IOException {
        this.taskArray = DataHandler.readFileContents(DataHandler.FILE_PATH);
    }

    public void processDelete(String input) {
        int indexDelete = parser.getIndexDelete(input);
        ui.displayDeleteMsg(taskArray.get(indexDelete), taskArray.size());
        taskArray.remove(indexDelete);
    }

    public void processEvent(String input) throws ArgumentNotFoundException {
        Task newEvent = new Todo(parser.parseEvent(input));
        taskArray.add(newEvent);
        ui.displayAcknowledgement(newEvent, taskArray.size());
    }

    public void processDeadline(String input) throws ArgumentNotFoundException {
        Task newDeadline = new Todo(parser.parseDeadline(input));
        taskArray.add(newDeadline);
        ui.displayAcknowledgement(newDeadline, taskArray.size());
    }

    public void processTodo(String input) throws ArgumentNotFoundException {
        Task newTodo = new Todo(parser.parseTodo(input));
        taskArray.add(newTodo);
        ui.displayAcknowledgement(newTodo, taskArray.size());
    }

    public void processUnmark(String input) throws ArgumentNotFoundException, TaskNotFoundException {
        int indexUnmark = parser.getIndexUnmark(taskArray, input);
        taskArray.get(indexUnmark).setDone(false);
        ui.displayUnmarkMsg(indexUnmark, taskArray);
    }

    public void processMark(String input) throws ArgumentNotFoundException, TaskNotFoundException {
        int indexMark = parser.getIndexMark(taskArray, input);
        taskArray.get(indexMark).setDone(true);
        ui.displayMarkMsg(indexMark, taskArray);
    }

    public void printList() {
        ui.displayList(taskArray, false);
    }

    public void printMatchingList(String input) throws ArgumentNotFoundException {
        ArrayList<Task> matchingList = new ArrayList<>();
        String matchString = parser.parseFind(input);
        for(Task t : taskArray){
            if(t.description.contains(matchString)){
                matchingList.add(t);
            }
        }
        ui.displayList(matchingList, true);
    }

    public void updateTaskList() throws IOException {
        DataHandler.tasksToFile(taskArray);
    }
}