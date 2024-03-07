package seedu.laika.parser;

import seedu.laika.LaikaException;
import seedu.laika.task.Deadline;
import seedu.laika.task.Event;
import seedu.laika.task.Task;
import seedu.laika.task.Todo;
import seedu.laika.tasklist.TaskList;
import seedu.laika.ui.TextUi;

import java.util.Objects;

/**
 * Parses user input into commands for execution.
 */
public class Parser {


    /**
     * Converts a string command into a Task object.
     * @param command input command split into an array with the first element indicating if the task is done.
     * @return Task object created from the command string.
     */
    public static Task commandtoTask(String[] command) {
        Task task = null;
        if(command[1].startsWith("todo")){
            task = new Todo(command[1].replaceFirst("todo ",""));
        }
        else if (command[1].startsWith("deadline")) {
            String[] words = command[1].split("/");
            task = new Deadline(words[0].replaceFirst("deadline ",""),
                    words[1].replaceFirst("by ",""));
        }
        else if (command[1].startsWith("event")) {
            String[] words = command[1].split("/");
            task = new Event(words[0].replaceFirst("event ",""),
                    words[1].replaceFirst("from ",""),
                    words[2].replaceFirst("to ",""));
        }
        if (Objects.equals(command[0], "1")) {
            assert task != null;
            task.setDone();
        }
        return task;
    }

    /**
     * Parses the user input line and executes the command.
     * @param line the complete input line provided by the user.
     * @param taskList the current list of tasks.
     * @return true if the application should continue running, false if it should terminate (on "bye" command).
     */
    public boolean parse(String line, TaskList taskList){
        String[] words = line.split(" ", 2);

        switch(words[0]){
            case "mark":
            case "unmark":
                try {
                    taskList.modifyTask(words);
                }
                catch (IndexOutOfBoundsException e) {
                    TextUi.showErrorMessage(TextUi.MESSAGE_INDEX_OUT_OF_BOUNDS);
                }
                return true;
            case "list":
                taskList.displayTasks();
                return true;
            case "find":
                taskList.findTasks(words[1]);
                return true;
            case "bye":
                return false;
            case "delete":
                try {
                    taskList.deleteTask(words);
                }
                catch (IndexOutOfBoundsException e) {
                    TextUi.showErrorMessage(TextUi.MESSAGE_INDEX_OUT_OF_BOUNDS);
                }
                TextUi.showNumberOfTasksLeft(taskList);
                return true;
            default:

                try{
                    taskList.addTask(line);
                }
                catch(LaikaException e) {
                    break;
                }
                TextUi.showTaskAdded(taskList);
                return true;
        }
        return true;
    }

}
