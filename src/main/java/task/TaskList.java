package task;

import java.util.ArrayList;
import java.io.IOException;
import exception.EkudException;

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
            if (dividerPosition == -1) {
                throw new EkudException();
            }
            else {
                int descriptionStart = dividerPosition + 1;
                tasks.add(new Todo(userInput.substring(descriptionStart)));
                System.out.println("Got it. I've added this task:");
                System.out.println(tasks.get(taskCount));
                taskCount++;
                System.out.println("Now you have " + taskCount + " tasks in the list.");
            }
        }
        catch (EkudException error) {
            System.out.println("The description of a todo cannot be empty.");
        }
    }

    public void addDeadline(String userInput) {
        int dividerPosition = userInput.indexOf(" ");
        try {
            if (dividerPosition == -1) {
                throw new EkudException();
            }
            else {
                int slashPosition = userInput.indexOf("/by");
                int descriptionStart = dividerPosition + 1;
                int descriptionEnd = slashPosition - 1;
                int byStart = slashPosition + 4;
                tasks.add(new Deadline(userInput.substring(descriptionStart, descriptionEnd), userInput.substring(byStart)));
                System.out.println("Got it. I've added this task:");
                System.out.println(tasks.get(taskCount));
                taskCount++;
                System.out.println("Now you have " + taskCount + " tasks in the list.");
            }
        }
        catch (EkudException error) {
            System.out.println("The description of a deadline cannot be empty.");
        }
    };

    public void addEvent(String userInput) {
        int dividerPosition = userInput.indexOf(" ");
        try {
            if (dividerPosition == -1) {
                throw new EkudException();
            }
            else {
                int descriptionStart = dividerPosition + 1;
                int descriptionEnd = userInput.indexOf("/from") - 1;
                int fromStart = userInput.indexOf("/from") + 6;
                int fromEnd = userInput.indexOf("/to") - 1;
                int toStart = userInput.indexOf("/to") + 4;

                tasks.add(new Event(userInput.substring(descriptionStart, descriptionEnd), userInput.substring(fromStart, fromEnd), userInput.substring(toStart)));
                System.out.println("Got it. I've added this task:");
                System.out.println(tasks.get(taskCount));
                taskCount++;
                System.out.println("Now you have " + taskCount + " tasks in the list.");
            }
        }
        catch (EkudException error) {
            System.out.println("The description of an event cannot be empty.");
        }
    };

    public void list() {
        System.out.println("Here are the tasks in your list:");
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
                System.out.println("Noted. I've removed this task:");
                System.out.println(tasks.get(taskIndex));
                tasks.remove(taskIndex);
                taskCount--;
                System.out.println("Now you have " + taskCount + " tasks in the list.");
            }
        }
        catch(EkudException error){
            System.out.println("The task number is not valid or not provided.");
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
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(tasks.get(taskIndex));
            }
        }
        catch (EkudException error) {
            System.out.println("The task number is not valid or not provided.");
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
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(tasks.get(taskIndex));
            }
        }
        catch (EkudException error) {
            System.out.println("The task number is not valid or not provided.");
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

    public void increaseTaskCount() {
        this.taskCount++;
    }

}
