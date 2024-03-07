package GermaBot;

import Exceptions.*;
import Tasks.*;
import DataHandling.SaveData;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;

public class Logic {

    public static TaskManager taskManager = new TaskManager();

    public static int getIdx(String input) {
        return Integer.parseInt(input.substring(input.indexOf(" ") + 1)) - 1;
    }

    public static void createTodo(ArrayList<Task> toDoList, String input) throws EmptyTaskException, IOException {
        int idxBetweenTodoAndDescription = 5;
        String toDoTask = input.substring(input.indexOf("todo ") + idxBetweenTodoAndDescription);
        if (toDoTask.isBlank()) {
            throw new EmptyTaskException();
        }
        taskManager.addTodo(toDoList, toDoTask);
        Task.setNoOfTask(Task.getNoOfTask() + 1);
        Storage.saveTodo(new ToDo(toDoTask), 'T');
        System.out.println("Gotcha! Added '" + toDoTask + "' to your To Do List!");
    }

    public static void createDeadline(ArrayList<Task> toDoList, String input) throws EmptyTaskException, MissingDeadlineException, IOException {
        String description = input.replaceFirst("deadline ", "");
        if (description.equals("deadline")) {
            throw new EmptyTaskException();
        }
        int idxOfEndDate = description.indexOf("/");
        if (idxOfEndDate == -1) {
            throw new MissingDeadlineException();
        }
        int idxToDateFromEndDate = 4;
        String date = description.substring(idxOfEndDate + idxToDateFromEndDate);
        String toDoTask = description.substring(0, idxOfEndDate - 1);
        if (toDoTask.isBlank()) {
            throw new EmptyTaskException();
        }
        taskManager.addDeadline(toDoList, description, idxOfEndDate, date);
        Task.setNoOfTask(Task.getNoOfTask() + 1);
        Storage.saveDeadline(new Deadline(description.substring(0, idxOfEndDate - 1), date), 'D');
        UI.printCreateDeadlineMessage(toDoTask, date);
    }

    public static void createEvent(ArrayList<Task> toDoList, String input) throws EmptyTaskException, MissingDeadlineException,
            MissingStartDateException, IOException {
        String description = input.replaceFirst("event ", "");
        int idxOfFrom = description.indexOf("/from");
        if (idxOfFrom == -1) {
            throw new EmptyTaskException();
        }
        int idxOfBy = description.indexOf("/to");
        if (idxOfBy == -1) {
            throw new MissingDeadlineException();
        }
        int idxFromFromToStartDate = 6;
        String startDate = description.substring(idxOfFrom + idxFromFromToStartDate, idxOfBy - 1);
        if (startDate.isBlank()) {
            throw new MissingStartDateException();
        }
        int idxFromByToEndDate = 4;
        String endDate = description.substring(idxOfBy + idxFromByToEndDate);
        if (endDate.isBlank()) {
            throw new MissingDeadlineException();
        }
        String toDoTask = description.substring(0, idxOfFrom - 1);
        if (toDoTask.isBlank()) {
            throw new EmptyTaskException();
        }
        taskManager.addEvent(toDoList, description, idxOfFrom, startDate, endDate);
        Task.setNoOfTask(Task.getNoOfTask() + 1);
        Storage.saveEvent(new Event(description.substring(0, idxOfFrom - 1), startDate, endDate), 'E');
        UI.printCreateEventMessage(toDoTask, startDate, endDate);
    }

    public static void deleteTask(ArrayList<Task> toDoList, String input) throws TaskNotFoundException {
        int idxToDelete = getIdx(input);
        if (idxToDelete >= Task.getNoOfTask()) {
            throw new TaskNotFoundException();
        } else {
            UI.deleteTask(toDoList, idxToDelete);
            Task.setNoOfTask(Task.getNoOfTask() - 1);
            ;
        }
    }

    public static void createTask(ArrayList<Task> toDoList, String input) {
        if (input.startsWith("delete")) {
            try {
                deleteTask(toDoList, input);
            } catch (TaskNotFoundException e) {
                int idx = getIdx(input);
                UI.printTaskNotFoundException(idx);
            }
        } else if (input.startsWith("todo")) {
            try {
                createTodo(toDoList, input);
            } catch (EmptyTaskException e) {
                UI.printEmptyTaskException();
            } catch (IOException e) {
                UI.printIOException();
            }
        } else if (input.startsWith("deadline")) {
            try {
                createDeadline(toDoList, input);
            } catch (EmptyTaskException e) {
                UI.printEmptyTaskException();
            } catch (MissingDeadlineException e) {
                UI.printMissingDeadlineException();
            } catch (IOException e) {
                UI.printIOException();
            }
        } else if (input.startsWith("event")) {
            try {
                createEvent(toDoList, input);
            } catch (EmptyTaskException e) {
                UI.printEmptyTaskException();
            } catch (MissingStartDateException e) {
                UI.printMissingStartDateException();
            } catch (MissingDeadlineException e) {
                UI.printMissingDeadlineException();
            } catch (IOException e) {
                UI.printIOException();
            }
        }
    }

    public static void readCommand(ArrayList<Task> toDoList, String input) throws UnknownInputException {
        if (input.startsWith("delete") || input.startsWith("todo") || input.startsWith("deadline") || input.startsWith("event")) {
            createTask(toDoList, input);
        } else if (input.equals("list")) {
            if (toDoList.isEmpty()) {
                UI.printListEmptyMessage();
            } else {
                UI.printTaskList(toDoList);
            }
        } else if (input.contains("unmark")) {
            int idx = getIdx(input);
            taskManager.markTaskUndone(toDoList, idx);
            UI.printMarkUndone(idx, toDoList);
        } else if (input.contains("mark")) {
            int idx = getIdx(input);
            taskManager.markTaskDone(toDoList, idx);
            UI.printMarkDone(idx, toDoList);
        } else {
            throw new UnknownInputException();
        }
    }
}
