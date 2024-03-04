package jake.task;

import java.util.ArrayList;
import jake.ui.Ui;

import static jake.common.Messages.MESSAGE_TASK_ADDED;
import static jake.common.Messages.MESSAGE_TASK_DELETED;

public class TaskList {

    private static Ui ui = new Ui();
    private static ArrayList<Task> commands = new ArrayList<>();

    /**
     * Returns the number of tasks in TaskList
     *
     * @return Integer representing the number of tasks in TaskList
     */
    public int size() {
        return commands.size();
    }

    /**
     * Returns a task from TaskList, based on the given index
     *
     * @param index Takes in an integer, representing the index of the task within TaskList 
     * @return Task at the specified index
     */
    public Task get(int index) {
        return commands.get(index);
    }

    /**
     * Iterates through TaskList, and prints out each task within the TaskList
     *
     */
    public void listTasks() {
        ui.showListedTasks();
        for (int i = 0; i < commands.size(); i++){
            System.out.println(Integer.toString(i+1) + "." + commands.get(i));
        }
        ui.showLineString();
    }

    /**
     * Reads a string input, and returns the given index within this String
     * For example, the string input "delete task 4" will return 4
     *
     * @param userInput Takes in a String, representing the user input 
     * @return An integer represented in the user input string
     */
    public int retrieveTaskNumber(String userInput) {
        int taskNumber = Integer.parseInt(userInput.substring(userInput.lastIndexOf(" ")+1));
        return taskNumber;
    }

    /**
     * Reads a string input, and extracts out the given index within this String
     * For example, the string input "unmark task 4" will extract out the index 4
     * Will mark/unmark the task at the specified index as completed/uncompleted
     *
     * @param userInput Takes in a String, representing the user input
     * @param markOrUnmark Takes in a String, either "mark" or "unmark", to determine how to toggle the task
     */
    public void toggleTask(String userInput, String markOrUnmark) {
        int taskNumber = retrieveTaskNumber(userInput);
        if (taskNumber>commands.size()){
            ui.showNonexistentTask();
        } else if (markOrUnmark.equals("unmark")){
            ui.showTaskUnmarked();
            commands.get(taskNumber-1).markTask(false);
        } else {
            ui.showTaskMarked();
            commands.get(taskNumber-1).markTask(true);
        }
        ui.showLineString();
    }

    /**
     * Reads a user's input, and a task type
     * Based on the task type (Event, ToDo, Deadline etc), creates the respective object using the user's input 
     * addInputtedTask() is specifically used to deal with new user inputs only 
     * It cannot deal with the saved tasks being loaded from the saved text file
     *
     * @param userInput Takes in the user's input command
     * @param taskType Takes in a task type, such as "event"/"todo", which determines the object created
     */
    public void addInputtedTask(String userInput, String taskType) {
        Task newTask;
        switch (taskType) {
        case "todo":
            newTask = new ToDo(userInput);
            break;
        case "deadline":
            String[] deadlineSections = userInput.split(" by ");
            newTask = new Deadline(deadlineSections[0], deadlineSections[1], false);;
            break;
        case "event":
            String[] eventSections = userInput.split(" from ");
            String[] eventTimings = eventSections[1].split(" to ");
            newTask = new Event(eventSections[0], eventTimings[0], eventTimings[1], false);
            break;
        default:
            return;
        } 
        commands.add(newTask);
        System.out.printf(MESSAGE_TASK_ADDED, newTask.toString(), commands.size());
    }

    /**
     * Reads a user's input, and a task type
     * Based on the task type (Event, ToDo etc), creates the respective object using the user's input description
     * addSavedTask() is specifically used to deal with the saved tasks being loaded from the saved text file only
     * It cannot deal with new user inputs 
     *
     * @param userInput Takes in the user's input command
     * @param taskType Takes in character representing task type, such as T for ToDo. Determines object created
     */
    public void addSavedTask(String userInput, char taskType) {
        Task newTask;
        String shortenedTask = userInput.substring(6);
        boolean isCompleted = userInput.charAt(4) == 'X';
        switch (taskType) {
        case 'T':
            newTask = new ToDo("todo" + shortenedTask);
            break;
        case 'D':
            // "\\" deals with PatternSyntaxException due to the (
            String[] deadlineSections = shortenedTask.split(" \\(by: "); 
            newTask = new Deadline("deadline" + deadlineSections[0], 
                    deadlineSections[1].substring(0, deadlineSections[1].length()-1), 
                    true);;
            break;
        case 'E':
            String[] eventSections = shortenedTask.split(" \\(from: ");
            String[] eventTimings = eventSections[1].split(" to: ");
            newTask = new Event("event" + eventSections[0], eventTimings[0], 
                    eventTimings[1].substring(0, eventTimings[1].length()-1),
                    true);
            break;
        default:
            return;
        } 
        newTask.markTask(isCompleted);
        commands.add(newTask);
    }

    /**
     * Reads a user input, and extracts out the given index within this String
     * For example, the string input "delete task 4" will extract out the index 4
     * Will delete the task at the specified index
     *
     * @param userInput Takes in a String, representing the user input
     */
    public void deleteTask(String userInput) {
        int taskNumber = retrieveTaskNumber(userInput);
        try {
            System.out.printf(MESSAGE_TASK_DELETED, commands.get(taskNumber-1), commands.size()-1);
            commands.remove(taskNumber-1);
        } catch (IndexOutOfBoundsException e) {
            ui.showNonexistentTask();
        }
    }
}
