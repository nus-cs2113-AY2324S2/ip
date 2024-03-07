package task;

import java.util.ArrayList;
import exception.EkudException;
import ui.UI;

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
        int dividerPosition = userInput.indexOf(" ");
        try {
            if (dividerPosition != -1) {
                int descriptionStart = dividerPosition + 1;
                tasks.add(new Todo(userInput.substring(descriptionStart)));
                System.out.println(UI.TASK_ADDED_MESSAGE);
                System.out.println(tasks.get(taskCount));
                taskCount++;
                UI.showTaskCountMessage(taskCount);
            }
            else {
                throw new EkudException();
            }
        }
        catch (EkudException error) {
            System.out.println(UI.EMPTY_TODO_DESCRIPTION_MESSAGE);
        }
    }

    public void addDeadline(String userInput) {
        int dividerPosition = userInput.indexOf(" ");
        try {
            if (dividerPosition != -1) {
                int slashPosition = userInput.indexOf("/by");
                int descriptionStart = dividerPosition + 1;
                int descriptionEnd = slashPosition - 1;
                int byStart = slashPosition + 4;
                tasks.add(new Deadline(userInput.substring(descriptionStart, descriptionEnd),
                        userInput.substring(byStart)));
                System.out.println(UI.TASK_ADDED_MESSAGE);
                System.out.println(tasks.get(taskCount));
                taskCount++;
                UI.showTaskCountMessage(taskCount);
            }
            else {
                throw new EkudException();
            }
        }
        catch (EkudException error) {
            System.out.println(UI.EMPTY_DEADLINE_DESCRIPTION_MESSAGE);
        }
    };

    public void addEvent(String userInput) {
        int dividerPosition = userInput.indexOf(" ");
        try {
            if (dividerPosition != -1) {
                int descriptionStart = dividerPosition + 1;
                int descriptionEnd = userInput.indexOf("/from") - 1;
                int fromStart = userInput.indexOf("/from") + 6;
                int fromEnd = userInput.indexOf("/to") - 1;
                int toStart = userInput.indexOf("/to") + 4;

                tasks.add(new Event(userInput.substring(descriptionStart, descriptionEnd),
                        userInput.substring(fromStart, fromEnd),
                        userInput.substring(toStart)));
                System.out.println(UI.TASK_ADDED_MESSAGE);
                System.out.println(tasks.get(taskCount));
                taskCount++;
                UI.showTaskCountMessage(taskCount);
            }
            else {
                throw new EkudException();
            }
        }
        catch (EkudException error) {
            System.out.println(UI.EMPTY_EVENT_DESCRIPTION_MESSAGE);
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
