package MainRuntime;

import Exceptions.*;
import Tasks.*;
import DataHandling.*;
import GermaBot.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;

public class GermaBot {
    static int counter = 0;
    static ArrayList<Task> toDoList = new ArrayList<>();

    public static void loadToDo(String description) throws LoadFileException {
        if (description.isEmpty()) {
            throw new LoadFileException();
        }
        toDoList.add(new ToDo(description));
    }

    public static void loadDeadline(String description) throws LoadFileException {
        String[] deadlineDescription = description.split("\\|");
        String task = deadlineDescription[0].trim();
        String by = deadlineDescription[1].trim();
        if (task.isBlank() || by.isBlank()) {
            throw new LoadFileException();
        }
        toDoList.add(new Deadline(task, by));
    }

    public static void loadEvent(String description) throws LoadFileException {
        String[] eventDescription = description.split("\\|");
        String task = eventDescription[0].trim();
        String from = eventDescription[1].trim();
        String by = eventDescription[2].trim();
        if (task.isBlank() || from.isBlank() || by.isBlank()) {
            throw new LoadFileException();
        }
        toDoList.add(new Event(task, from, by));
    }

    public static void loadFile () throws FileNotFoundException, FileReadException {
        File data = new File("src/data/GermaBotData.txt");
        Scanner fileInput = new Scanner(data);
        do {
            String task = fileInput.nextLine();
            if (task.trim().isEmpty()) {
                throw new FileReadException();
            }
            counter++;
            char type = task.charAt(0);
            String description = task.substring(8);
            boolean isCompleted = task.charAt(4) != '0';
            if (type == 'T') {
                try {
                    loadToDo(description);
                } catch (LoadFileException e) {
                    UI.printLoadFileException();
                }
            } else if (type == 'D') {
                try {
                    loadDeadline(description);
                } catch (LoadFileException e) {
                    UI.printLoadFileException();
                }
            } else if (type == 'E') {
                try {
                    loadEvent(description);
                } catch (LoadFileException e) {
                    UI.printLoadFileException();
                }
            } else {
                break;
            }
            int idxToMark = toDoList.size() - 1;
            toDoList.get(idxToMark).setDone(isCompleted);
        } while (fileInput.hasNext());
    }
    public static int getIdx(String input) {
        return Integer.parseInt(input.substring(input.indexOf(" ") + 1)) - 1;
    }

    public static void createTodo(String input) throws EmptyTaskException, IOException {
        int idxBetweenTodoAndDescription = 5;
        String toDoTask = input.substring(input.indexOf("todo ") + idxBetweenTodoAndDescription);
        if (toDoTask.isBlank()) {
            throw new EmptyTaskException();
        }
        toDoList.add(new ToDo(toDoTask));
        counter++;
        SaveData.addTodoToFile(new ToDo(toDoTask), 'T');
        System.out.println("Gotcha! Added '" + toDoTask + "' to your To Do List!");
    }

    public static void createDeadline(String input) throws EmptyTaskException, MissingDeadlineException, IOException {
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
        toDoList.add(new Deadline(description.substring(0, idxOfEndDate - 1), date));
        counter++;
        SaveData.addDeadlineToFile(new Deadline(description.substring(0, idxOfEndDate - 1), date), 'D');
        UI.printCreateDeadlineMessage(toDoTask, date);
    }

    public static void createEvent(String input) throws EmptyTaskException, MissingDeadlineException,
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
        toDoList.add(new Event(description.substring(0, idxOfFrom - 1), startDate, endDate));
        counter++;
        SaveData.addEventToFile(new Event(description.substring(0, idxOfFrom - 1), startDate, endDate), 'E');
        UI.printCreateEventMessage(toDoTask, startDate, endDate);
    }

    public static void deleteTask(String input) throws TaskNotFoundException {
        int idxToDelete = getIdx(input);
        if (idxToDelete >= counter) {
            throw new TaskNotFoundException();
        } else {
            UI.deleteTask(toDoList, idxToDelete);
            counter--;
        }
    }

    public static void createTask(String input) throws UnknownInputException {
        if (input.startsWith("delete")) {
            try {
                deleteTask(input);
            } catch (TaskNotFoundException e) {
                int idx = getIdx(input);
                UI.printTaskNotFoundException(idx);
            }
        }
        else if (input.startsWith("todo")) {
            try {
                createTodo(input);
            } catch (EmptyTaskException e) {
                UI.printEmptyTaskException();
            } catch (IOException e) {
                UI.printIOException();
            }
        }
        else if (input.startsWith("deadline")) {
            try {
                createDeadline(input);
            } catch (EmptyTaskException e) {
                UI.printEmptyTaskException();
            } catch (MissingDeadlineException e) {
                UI.printMissingDeadlineException();
            } catch (IOException e) {
                UI.printIOException();
            }
        }
        else if (input.startsWith("event")) {
            try {
                createEvent(input);
            } catch (EmptyTaskException e) {
                UI.printEmptyTaskException();
            } catch (MissingStartDateException e) {
                UI.printMissingStartDateException();
            } catch (MissingDeadlineException e) {
                UI.printMissingDeadlineException();
            } catch (IOException e) {
                UI.printIOException();
            }
        } else {
            throw new UnknownInputException();
        }
    }


    public void run() {
        UI.printWelcomeMessage();
    }

    public static void main(String[] args) {
        UI.printWelcomeMessage();
        try {
            toDoList = LoadData.loadFile();
            counter = LoadData.getCounter();
            UI.printLoadComplete();
        } catch (FileNotFoundException e) {
            UI.printFileNotFoundException();
        } catch (FileReadException e) {
            UI.printFileReadException();
        }
        UI.printPostLoadingMessage();
        while (true) {
            String input;
            Scanner in = new Scanner(System.in);
            input = in.nextLine();
            if (input.equals("bye")) {
                break;
            }
            if (input.equals("list")) {
                if (toDoList.isEmpty()) {
                    UI.printListEmptyMessage();
                } else {
                    UI.printTaskList(toDoList);
                    }
            } else if (input.contains("unmark")) {
                int idx = getIdx(input);
                toDoList.get(idx).setDone(false);
                UI.printMarkUndone(idx, toDoList);
            } else if (input.contains("mark")) {
                int idx = getIdx(input);
                toDoList.get(idx).setDone(true);
                UI.printMarkDone(idx, toDoList);
            } else {
                try {
                    createTask(input);
                } catch (UnknownInputException e){
                    UI.printUnknownInputException(input);
                }
            }
        }
        UI.printByeMessage();
    }
}
