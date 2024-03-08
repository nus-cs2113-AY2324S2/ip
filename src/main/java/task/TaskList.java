package task;

import java.util.ArrayList;
import exception.EkudException;
import ui.UI;
import ui.Parser;

/**
 * This TaskList class contains a list of tasks and methods to interact with it.
 */
public class TaskList {

    private int taskCount = 0;
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public int getTaskCount() {
        return this.taskCount;
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public void addTodo(String userInput) {
        try {
            tasks.add(Parser.parseTodo(userInput));
            System.out.println(UI.TASK_ADDED_MESSAGE);
            System.out.println(tasks.get(taskCount));
            taskCount++;
            UI.showTaskCountMessage(taskCount);
        }
        catch (EkudException error) {
            System.out.println(UI.TODO_FORMAT_ERROR_MESSAGE);
        }
    }

    public void addDeadline(String userInput) {
        try {
            tasks.add(Parser.parseDeadline(userInput));
                System.out.println(UI.TASK_ADDED_MESSAGE);
                System.out.println(tasks.get(taskCount));
                taskCount++;
                UI.showTaskCountMessage(taskCount);
        }
        catch (EkudException error) {
            System.out.println(UI.DEADLINE_ERROR_MESSAGE);
        }
        catch (IndexOutOfBoundsException error) {
            System.out.println(UI.DEADLINE_FORMAT_ERROR_MESSAGE);
        }
    };

    public void addEvent(String userInput) {
        try {
            tasks.add(Parser.parseEvent(userInput));
            System.out.println(UI.TASK_ADDED_MESSAGE);
            System.out.println(tasks.get(taskCount));
            taskCount++;
            UI.showTaskCountMessage(taskCount);
        }
        catch (EkudException error) {
            System.out.println(UI.EVENT_ERROR_MESSAGE);
        }
        catch (IndexOutOfBoundsException error) {
            System.out.println(UI.EVENT_FORMAT_ERROR_MESSAGE);
        }
    };

    public void list() {
        System.out.println(UI.LIST_TASK_MESSAGE);
        for(int i = 0; i < taskCount; i++){
            System.out.println((i + 1) + "." + tasks.get(i));
        }
    }

    public void delete(String userInput) {
        String[] userInputWords = userInput.split(" ");
        try {
            if(userInputWords.length == 1 || Integer.parseInt(userInputWords[1]) > taskCount){
                throw new EkudException();
            }
            else{
                int taskIndex = Integer.parseInt(userInputWords[1]) - 1;
                System.out.println(UI.DELETE_TASK_MESSAGE);
                System.out.println(tasks.get(taskIndex));
                tasks.remove(taskIndex);
                taskCount--;
                UI.showTaskCountMessage(taskCount);
            }
        }
        catch(EkudException error){
            System.out.println(UI.INVALID_TASK_NUM_MESSAGE);
        }
    }

    public void mark(String userInput) {
        String[] userInputWords = userInput.split(" ");
        try {
            if (userInputWords.length == 1 || Integer.parseInt(userInputWords[1]) > taskCount){
                throw new EkudException();
            }
            else {
                int taskIndex = Integer.parseInt(userInputWords[1]) - 1;
                tasks.get(taskIndex).markAsDone();
                System.out.println(UI.MARK_TASK_MESSAGE);
                System.out.println(tasks.get(taskIndex));
            }
        }
        catch (EkudException error) {
            System.out.println(UI.INVALID_TASK_NUM_MESSAGE);
        }
    }

    public void unmark(String userInput) {
        String[] userInputWords = userInput.split(" ");
        try {
            if (userInputWords.length == 1 || Integer.parseInt(userInputWords[1]) > taskCount){
                throw new EkudException();
            }
            else {
                int taskIndex = Integer.parseInt(userInputWords[1]) - 1;
                tasks.get(taskIndex).markAsNotDone();
                System.out.println(UI.UNMARK_TASK_MESSAGE);
                System.out.println(tasks.get(taskIndex));
            }
        }
        catch (EkudException error) {
            System.out.println(UI.INVALID_TASK_NUM_MESSAGE);
        }
    }

    /**
     * Finds the tasks which are relevant to the keyword provided.
     *
     * @param userInput contains the keyword to query.
     */
    public void find(String userInput) {
        String[] userInputWords = userInput.split(" ");
        ArrayList<Task> relevantTasks = new ArrayList<Task>();

        try {
            if(userInputWords.length <= 1) {
                throw new EkudException();
            }
            else {
                String keyword = userInputWords[1].toLowerCase();

                for(int i = 0; i < taskCount; i++) {
                    String currentTask = tasks.get(i).toString().toLowerCase();
                    if(currentTask.contains(keyword)) {
                        relevantTasks.add(tasks.get(i));
                    }
                }
                System.out.println(UI.FIND_RESULT_MESSAGE);

                for(int i = 0; i < relevantTasks.size(); i++) {
                    System.out.println((i + 1) + "." + relevantTasks.get(i));
                }
            }
        }
        catch (EkudException error) {
            System.out.println(UI.FIND_ERROR_MESSAGE);
        }
    }

    /**
     * Loads pre-existing tasks from text file.
     *
     * @param currentLine contains a particular task read from the text file.
     */
    public void addTaskFromFile(String currentLine) {
        if(currentLine.contains("[T]")){
            int descriptionStart = currentLine.indexOf("[T]") + 7;
            tasks.add(new Todo(currentLine.substring(descriptionStart)));
            taskCount++;
        }
        else if (currentLine.contains("[D]")){
            int descriptionStart = currentLine.indexOf("[D]") + 7;
            int descriptionEnd = currentLine.indexOf("(by:") - 1;
            int byStart = currentLine.indexOf("(by:") + 5;
            int byEnd = currentLine.indexOf(')', byStart);
            tasks.add(new Deadline(currentLine.substring(descriptionStart, descriptionEnd),
                    currentLine.substring(byStart, byEnd)));
            taskCount++;
        }
        else if (currentLine.contains("[E]")){
            int descriptionStart = currentLine.indexOf("[E]") + 7;
            int descriptionEnd = currentLine.indexOf("(from:") - 1;
            int fromStart = currentLine.indexOf("(from:") + 7;
            int fromEnd = currentLine.indexOf("to:") - 1;
            int toStart = currentLine.indexOf("to:") + 4;
            int toEnd = currentLine.indexOf(')', toStart);
            tasks.add(new Event(currentLine.substring(descriptionStart, descriptionEnd),
                    currentLine.substring(fromStart, fromEnd),
                    currentLine.substring(toStart, toEnd)));
            taskCount++;
        }
    }

}
