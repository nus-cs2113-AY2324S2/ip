package kurobot;

import kurobot.exceptions.InvalidDescriptionException;
import kurobot.exceptions.InvalidTimeException;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;
    private Ui display = new Ui();
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
            display.showNoTaskGiven();
        }
        return tasks;
    }

    private void printAddedTask(Task task) {
        display.printGivenTask(task, taskNum, true);
    }

    private void printDeletedTask(Task task) {
        display.printGivenTask(task, taskNum, false);
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
            display.showNoTaskGiven();
        } catch (InvalidTimeException e) {
            display.showNoTimingGiven();
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
            display.showNoTaskGiven();
        } catch (InvalidTimeException e) {
            display.showNoTimingGiven();
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
                display.showNoSuchTask();
            }
        } catch (InvalidDescriptionException e) {
            display.showNoIndexGiven();
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
                display.showNoSuchTask();
            }
        } catch (InvalidDescriptionException e) {
            display.showNoIndexGiven();
        }
        return tasks;
    }

}
