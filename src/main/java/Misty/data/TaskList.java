package misty.data;

import misty.data.exception.*;
import misty.storage.Storage;
import misty.data.task.Deadline;
import misty.data.task.Event;
import misty.data.task.Task;
import misty.data.task.Todo;
import misty.ui.UserUi;

import java.util.ArrayList;

import java.io.IOException;

public class TaskList {
    private ArrayList<Task> taskList;
    private Storage storage;
    private UserUi userUi;

    public TaskList(Storage storage, UserUi userUi) {
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

    public void addTodo(String description) throws EmptyParameterException {
        if (description.isEmpty()) {
            userUi.printUsageUsageTodo();
            throw new EmptyParameterException();
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

    public void addDeadline(String description, String by) throws EmptyParameterException {
        if (description.isEmpty() || by.isEmpty()) {
            userUi.printUsageDeadline();
            throw new EmptyParameterException();
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

    public void addEvent(String description, String from, String to) throws EmptyParameterException {
        if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
            userUi.printUsageEvent();
            throw new EmptyParameterException();
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
            userUi.printUsageMark();
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
            userUi.printUsageUnmark();
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
            userUi.printUsageDelete();
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

    public void loadTodo(String description) throws CorruptedFileException {
        if (description.isEmpty()) {
            throw new CorruptedFileException();
        }

        Todo newTask = new Todo(description);
        addTask(newTask);
    }

    public void loadDeadline(String description, String by) throws CorruptedFileException {
        if (description.isEmpty() || by.isEmpty()) {
            throw new CorruptedFileException();
        }

        Deadline newTask = new Deadline(description, by);
        addTask(newTask);
    }

    public void loadEvent(String description, String from, String to) throws CorruptedFileException {
        if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw new CorruptedFileException();
        }

        Event newTask = new Event(description, from, to);
        addTask(newTask);
    }

    public void loadMark(int index) throws CorruptedFileException {
        if (index <= 0 || index > taskList.size()) {
            throw new CorruptedFileException();
        }

        taskList.get(index - 1).setTaskAsDone();
    }

}
