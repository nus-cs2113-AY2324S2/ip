package Misty;

import Misty.Exception.*;
import Misty.Task.Deadline;
import Misty.Task.Event;
import Misty.Task.Task;
import Misty.Task.Todo;

import java.io.IOException;

public class List {
    private Task[] list;
    private int itemCount;

    public List() {
        itemCount = 0;
        list = new Task[100];
    }

    public int getItemCount() {
        return itemCount;
    }

    public void printTaskCount() {
        Parser.printTaskCount(itemCount);
    }

    private void printAddTaskMessage(Task newTask) {
        Parser.printAddTaskMessage(newTask);
        printTaskCount();
    }

    private void addTask(Task newTask) {
        list[itemCount] = newTask;
        itemCount++;
    }

    public void addTodo(String description) throws EmptyTaskNameException {
        if (description.isEmpty()) {
            throw new EmptyTaskNameException();
        }

        Todo newTask = new Todo(description);
        addTask(newTask);

        try {
            SaveFile.saveTodo(newTask);
        } catch (IOException e) {
            Parser.printErrorIO();
        }

        printAddTaskMessage(newTask);
    }

    public void addDeadline(String description, String by) throws EmptyTaskNameException, EmptyByException {
        if (description.isEmpty()) {
            throw new EmptyTaskNameException();
        } else if (by.isEmpty()) {
            throw new EmptyByException();
        }

        Deadline newTask = new Deadline(description, by);
        addTask(newTask);

        try {
            SaveFile.saveDeadLine(newTask);
        } catch (IOException e) {
            Parser.printErrorIO();
        }

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

        Event newTask = new Event(description, from, to);
        addTask(newTask);

        try {
            SaveFile.saveEvent(newTask);
        } catch (IOException e) {
            Parser.printErrorIO();
        }

        printAddTaskMessage(newTask);
    }

    public void markTask(int index) throws IllegalListIndexException {
        if (index <= 0 || index > itemCount) {
            throw new IllegalListIndexException();
        }

        list[index - 1].setTaskAsDone();
        Parser.printTaskMarkAsDone(list[index-1]);
    }

    public void unmarkTask(int index) throws IllegalListIndexException {
        if (index <= 0 || index > itemCount) {
            throw new IllegalListIndexException();
        }

        list[index - 1].setTaskAsNotDone();
        Parser.printTaskUnmarkAsNotDone(list[index-1]);
    }

    public void listAll() {
        Parser.printList(list, itemCount);
    }

    public void loadTodo(String description) throws EmptyTaskNameException {
        if (description.isEmpty()) {
            throw new EmptyTaskNameException();
        }

        Todo newTask = new Todo(description);
        addTask(newTask);
    }

    public void loadDeadline(String description, String by) throws EmptyTaskNameException, EmptyByException {
        if (description.isEmpty()) {
            throw new EmptyTaskNameException();
        } else if (by.isEmpty()) {
            throw new EmptyByException();
        }

        Deadline newTask = new Deadline(description, by);
        addTask(newTask);
    }

    public void loadEvent(String description, String from, String to) throws EmptyTaskNameException, EmptyFromException, EmptyToException {
        if (description.isEmpty()) {
            throw new EmptyTaskNameException();
        } else if (from.isEmpty()) {
            throw new EmptyFromException();
        } else if (to.isEmpty()) {
            throw new EmptyToException();
        }

        Event newTask = new Event(description, from, to);
        addTask(newTask);
    }

    public void loadMark(int index) throws IllegalListIndexException {
        if (index <= 0 || index > itemCount) {
            throw new IllegalListIndexException();
        }

        list[index - 1].setTaskAsDone();
    }

}
