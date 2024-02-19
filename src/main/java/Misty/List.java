package Misty;

import Misty.Exception.*;
import Misty.Task.Deadline;
import Misty.Task.Event;
import Misty.Task.Task;
import Misty.Task.Todo;
import java.util.ArrayList;

public class List {
    private ArrayList<Task> taskList;
    private int itemCount;

    public List() {
        itemCount = 0;
        taskList = new ArrayList<>();
    }

    public int getItemCount() {
        return taskList.size();
    }

    public void printTaskCount() {
        Parser.printTaskCount(taskList.size());
    }

    private void printAddTaskMessage(Task newTask) {
        Parser.printAddTaskMessage(newTask);
        printTaskCount();
    }

    private void addTask(Task newTask) {
        taskList.add(newTask);
    }

    public void addTodo(String description) throws EmptyTaskNameException {
        if (description.isEmpty()) {
            throw new EmptyTaskNameException();
        }

        Task newTask = new Todo(description);
        addTask(newTask);
        printAddTaskMessage(newTask);
    }

    public void addDeadline(String description, String by) throws EmptyTaskNameException, EmptyByException {
        if (description.isEmpty()) {
            throw new EmptyTaskNameException();
        } else if (by.isEmpty()) {
            throw new EmptyByException();
        }

        Task newTask = new Deadline(description, by);
        addTask(newTask);
        printAddTaskMessage(newTask);
    }

    public void addEvent(String description, String from, String to) throws EmptyTaskNameException, EmptyFromException, EmptyToException {
        if (description.isEmpty()) {
            throw new EmptyTaskNameException();
        } else if (from.isEmpty()) {
            throw new EmptyFromException();
        } else if (to.isEmpty()) {
            throw new EmptyToException();
        }

        Task newTask = new Event(description, from, to);
        addTask(newTask);
        printAddTaskMessage(newTask);
    }

    public void markTask(int index) throws IllegalListIndexException {
        if (index <= 0 || index > taskList.size()) {
            throw new IllegalListIndexException();
        }

        taskList.get(index - 1).setTaskAsDone();
        Parser.printTaskMarkAsDone(taskList.get(index - 1));
    }

    public void unmarkTask(int index) throws IllegalListIndexException {
        if (index <= 0 || index > taskList.size()) {
            throw new IllegalListIndexException();
        }

        taskList.get(index - 1).setTaskAsNotDone();
        Parser.printTaskUnmarkAsNotDone(taskList.get(index - 1));
    }

    public void listAll() {
        Parser.printList(taskList, itemCount);
    }
}
