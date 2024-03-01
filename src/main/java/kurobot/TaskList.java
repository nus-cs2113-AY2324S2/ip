package kurobot;

import kurobot.exceptions.InvalidDescriptionException;
import kurobot.exceptions.InvalidTimeException;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;
    private int taskNum;
    private final int LINE_LEN = 60;
    private final String LINE =  "-".repeat(LINE_LEN);

    private Parser parserInput;

    public TaskList(ArrayList<Task> prevTasks, int taskNum, String userInput) {
        tasks = prevTasks;
        this.taskNum = taskNum;
        parserInput = new Parser(userInput);
    }

    public ArrayList<Task> addTodo() {
        try{
            parserInput.parserToDo();
            String taskName = parserInput.getTaskName();
            Todo task = new Todo(taskName, false);
            tasks.add(task);
            taskNum++;
            printAddedTask(task);
        } catch (InvalidDescriptionException e) {
            System.out.println(LINE);
            System.out.println("Hmmm.. what is the task about?");
            System.out.println(LINE);
        }
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

    public ArrayList<Task> addDeadline() {
        try{
            parserInput.parserDeadline();
            String taskName = parserInput.getTaskName();
            String by = parserInput.getDeadline();
            Deadline task = new Deadline(taskName, by, false);
            tasks.add(task);
            taskNum++;
            printAddedTask(task);
        } catch (InvalidDescriptionException e) {
        System.out.println(LINE);
        System.out.println("Heyyy~ don't forget your task");
        System.out.println(LINE);
        } catch (InvalidTimeException e) {
        System.out.println(LINE);
        System.out.println("Did you forget your due date? :p");
        System.out.println(LINE);
        }
        return tasks;
    }

    public ArrayList<Task> addEvent() {
        try {
            parserInput.parserEvent();
            String taskName = parserInput.getTaskName();
            String from = parserInput.getStart();
            String to = parserInput.getEnd();
            Event task = new Event(taskName, from, to, false);
            tasks.add(task);
            taskNum++;
            printAddedTask(task);
        } catch (InvalidDescriptionException e) {
            System.out.println(LINE);
            System.out.println("aiyoyo, how can you forget the event XD");
            System.out.println(LINE);
        } catch (InvalidTimeException e) {
            System.out.println(LINE);
            System.out.println("uhoh! don't forget the timings!");
            System.out.println(LINE);
        }
        return tasks;
    }

    public ArrayList<Task> markTask(boolean status) {
        try {
            parserInput.parserTaskIndex();
            int i = parserInput.getIndex();
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
        } catch (InvalidDescriptionException e) {
            System.out.println(LINE);
            System.out.println("mhmm.. which task? >.<");
            System.out.println(LINE);
        }
        return tasks;
    }

    public ArrayList<Task> deleteTask() {
        try {
            parserInput.parserTaskIndex();
            int i = parserInput.getIndex();
            try {
                Task deleteTask = tasks.get(i - 1);
                taskNum--;
                printDeletedTask(deleteTask);
                tasks.remove(deleteTask);
            } catch (IndexOutOfBoundsException e){
                System.out.println(LINE);
                System.out.println("there's no such task hmmm");
                System.out.println(LINE);
            }
        } catch (InvalidDescriptionException e) {
            System.out.println(LINE);
            System.out.println("what task?");
            System.out.println(LINE);
        }
        return tasks;
    }

}
