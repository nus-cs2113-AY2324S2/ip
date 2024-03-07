package GermaBot;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Exceptions.*;
import Tasks.*;

public class TaskManager {
    private final ArrayList<Task> tasks = new ArrayList<>();

    public TaskManager() {
    }

    public void markTaskDone(ArrayList<Task> toDoList, int idx) {
        toDoList.get(idx).setDone(true);
    }

    public void markTaskUndone(ArrayList<Task> toDoList, int idx) {
        toDoList.get(idx).setDone(false);
    }

    public void addTodo(ArrayList<Task> toDoList, String toDoTask) {
        toDoList.add(new ToDo(toDoTask));
    }

    public void addDeadline(ArrayList<Task> toDoList, String description, int idxOfEndDate, String date) {
        toDoList.add(new Deadline(description.substring(0, idxOfEndDate - 1), date));
    }

    public void addEvent(ArrayList<Task> toDoList, String description, int idxOfFrom, String startDate, String endDate) {
        toDoList.add(new Event(description.substring(0, idxOfFrom - 1), startDate, endDate));
    }

}
