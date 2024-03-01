package kurobot;

import kurobot.exceptions.InvalidDescriptionException;
import kurobot.exceptions.InvalidTimeException;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;
    private int taskNum;
    private static final int LINE_LEN = 60;
    private static final String LINE =  "-".repeat(LINE_LEN);

    public TaskList(ArrayList<Task> prevTasks, int taskNum) {
        tasks = prevTasks;
        this.taskNum = taskNum;
    }

    public ArrayList<Task> addTodo(String userInput) throws InvalidDescriptionException {
        //check if description was given
        String[] words = userInput.split(" ",2);
        if (words.length < 2){
            throw new InvalidDescriptionException();
        }

        String taskName = words[1];
        Todo task = new Todo(taskName, false);
        tasks.add(task);
        taskNum++;
        printAddedTask(task);
        return tasks;
    }

    private void printAddedTask(Task task) {
        System.out.println(LINE);
        System.out.println("Got it. I've added this task:");
        System.out.println(task.printTask());
        System.out.println("Now you have " + taskNum + " tasks in the list.");
        System.out.println(LINE);
    }

    private void printDeletedTask(Task task) {
        System.out.println(LINE);
        System.out.println("Noted. I've removed this task:");
        System.out.println(task.printTask());
        System.out.println("Now you have " + taskNum + " tasks in the list.");
        System.out.println(LINE);
    }

    public int getTaskNum() {
        return taskNum;
    }

    public ArrayList<Task> addDeadline(String userInput) throws InvalidDescriptionException, InvalidTimeException {
        //check if description was given
        String[] words = userInput.split(" ",2);
        if (words.length < 2){
            throw new InvalidDescriptionException();
        }
        String description = words[1];

        //check if due date was given
        String[] phrases = description.split("/by", 2);
        if (phrases.length < 2){
            throw new InvalidTimeException();
        }
        String taskName = phrases[0];
        String by = phrases[1].strip();

        Deadline task = new Deadline(taskName, by, false);
        tasks.add(task);
        taskNum++;
        printAddedTask(task);
        return tasks;
    }

    public ArrayList<Task> addEvent(String userInput) throws InvalidDescriptionException, InvalidTimeException {
        //check if description was given
        String[] words = userInput.split(" ",2);
        if (words.length < 2){
            throw new InvalidDescriptionException();
        }
        String description = words[1];

        //check if duration was given
        String[] phrases = description.split("/from",2);
        if (phrases.length < 2){
            throw new InvalidTimeException();
        }
        String taskName = phrases[0];

        //check if both "from" and "to" was given
        String[] period = phrases[1].split("/to",2);
        if(period.length < 2){
            throw new InvalidTimeException();
        }
        String from = period[0].strip();
        String to = period[1].strip();

        Event task = new Event(taskName, from, to, false);
        tasks.add(task);
        taskNum++;
        printAddedTask(task);
        return tasks;
    }

    public ArrayList<Task> markTask(String userInput, boolean status) throws InvalidDescriptionException {
        //check if task number was given
        String[] words = userInput.split(" ");
        if (words.length != 2){
            throw new InvalidDescriptionException();
        }

        //get task number
        String taskIndex = words[1];
        int i = Integer.parseInt(taskIndex);
        try {
            if (status) {
                tasks.get(i - 1).mark();
            } else {
                tasks.get(i - 1).unmark();
            }
        } catch (IndexOutOfBoundsException e){
            System.out.println(LINE);
            System.out.println("there's no such task though...");
            System.out.println(LINE);
        }
        return tasks;
    }

    public ArrayList<Task> deleteTask(String userInput) throws InvalidDescriptionException {
        String[] words = userInput.split(" ");
        if (words.length != 2){
            throw new InvalidDescriptionException();
        }

        String taskIndex = words[1];
        int i = Integer.parseInt(taskIndex);
        try {
            taskNum--;
            printDeletedTask(tasks.get(i-1));
            tasks.remove(tasks.get(i - 1));
        } catch (IndexOutOfBoundsException e){
            System.out.println(LINE);
            System.out.println("there's no such task hmmm");
            System.out.println(LINE);
        }
        return tasks;
    }

}
