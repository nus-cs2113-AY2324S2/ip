package misty;

import misty.exception.*;
import misty.task.Deadline;
import misty.task.Event;
import misty.task.Task;
import misty.task.Todo;
import java.util.ArrayList;

import java.io.IOException;

public class List {
    private ArrayList<Task> taskList;
    private Storage storage;
    private UserUi userUi;

    public List(Storage storage, UserUi userUi) {
        taskList = new ArrayList<>();
        this.storage = storage;
        this.userUi = userUi;
    }

    public void printTaskCount() {
        userUi.printTaskCount(taskList.size());
    }

    private void printAddTaskMessage(Task newTask) {
        userUi.printAddTaskMessage(newTask);
        printTaskCount();
    }

    private void addTask(Task newTask) {
        taskList.add(newTask);
    }

    public void addTodo(String description) throws EmptyTaskNameException {
        if (description.isEmpty()) {
            throw new EmptyTaskNameException();
        }

        Todo newTask = new Todo(description);
        addTask(newTask);

        try {
            storage.saveTodo(newTask);
        } catch (IOException e) {
            userUi.printErrorIO();
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
            storage.saveDeadLine(newTask);
        } catch (IOException e) {
            userUi.printErrorIO();
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
            storage.saveEvent(newTask);
        } catch (IOException e) {
            userUi.printErrorIO();
        }

        printAddTaskMessage(newTask);
    }

    public void markTask(int index) throws IllegalListIndexException {
        if (index <= 0 || index > taskList.size()) {
            throw new IllegalListIndexException();
        }

        taskList.get(index - 1).setTaskAsDone();

        try {
            storage.refreshSave(taskList);
        } catch (IOException e) {
            userUi.printErrorIO();
        }

        userUi.printTaskMarkAsDone(taskList.get(index-1));
    }

    public void unmarkTask(int index) throws IllegalListIndexException {
        if (index <= 0 || index > taskList.size()) {
            throw new IllegalListIndexException();
        }

        taskList.get(index - 1).setTaskAsNotDone();

        try {
            storage.refreshSave(taskList);
        } catch (IOException e) {
            userUi.printErrorIO();
        }

        userUi.printTaskUnmarkAsNotDone(taskList.get(index - 1));
    }

    public void deleteTask(int index) throws IllegalListIndexException {
        if (index <= 0 || index > taskList.size()) {
            throw new IllegalListIndexException();
        }

        Task temp = taskList.get(index - 1);
        taskList.remove(index - 1);

        try {
            storage.refreshSave(taskList);
        } catch (IOException e) {
            userUi.printErrorIO();
        }

        userUi.printDeleteTask(temp);
        printTaskCount();
    }

    public void listAll() {
        userUi.printList(taskList, taskList.size());
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
        if (index <= 0 || index > taskList.size()) {
            throw new IllegalListIndexException();
        }

        taskList.get(index - 1).setTaskAsDone();
    }

}
