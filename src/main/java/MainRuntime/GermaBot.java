package MainRuntime;

import Exceptions.*;
import Tasks.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;

public class GermaBot {
    static int counter = 0;
    static final String LINE= "____________________________________________";
    static final String WELCOME_MESSAGE = "Hello! GermaBot here! \n"
            + "Let me load your saved To Do List first...";
    static final String GOODBYE_MESSAGE = "Thanks for using me! Hope to see you again soon~!";
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
                    System.out.println("Oh no, There was an error loading your save file! Please check" +
                            " to make sure it follows the format...");
                }
            } else if (type == 'D') {
                try {
                    loadDeadline(description);
                } catch (LoadFileException e) {
                    System.out.println("Oh no, There was an error loading your save file! Please check" +
                            " to make sure it follows the format...");
                }
            } else if (type == 'E') {
                try {
                    loadEvent(description);
                } catch (LoadFileException e) {
                    System.out.println("Oh no, There was an error loading your save file! Please check" +
                            " to make sure it follows the format...");
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
        String toDoTask = input.substring(input.indexOf("todo ") + 5);
        if (toDoTask.isBlank()) {
            throw new EmptyTaskException();
        }
        toDoList.add(new ToDo(toDoTask));
        counter++;
        DataHandling.SaveData.addTodoToFile(new ToDo(toDoTask), 'T');
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
        String date = description.substring(idxOfEndDate + 4);
        String toDoTask = description.substring(0, idxOfEndDate - 1);
        if (toDoTask.isBlank()) {
            throw new EmptyTaskException();
        }
        toDoList.add(new Deadline(description.substring(0, idxOfEndDate - 1), date));
        counter++;
        DataHandling.SaveData.addDeadlineToFile(new Deadline(description.substring(0, idxOfEndDate - 1), date), 'D');
        System.out.println("Gotcha! Added '" + toDoTask + "' to your To Do List!" +
                " You have to finish this by "  + date + ", so be reminded!");
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
        String startDate = description.substring(idxOfFrom + 6, idxOfBy - 1);
        if (startDate.isBlank()) {
            throw new MissingStartDateException();
        }
        String endDate = description.substring(idxOfBy + 4);
        if (endDate.isBlank()) {
            throw new MissingDeadlineException();
        }
        String toDoTask = description.substring(0, idxOfFrom - 1);
        if (toDoTask.isBlank()) {
            throw new EmptyTaskException();
        }
        toDoList.add(new Event(description.substring(0, idxOfFrom - 1), startDate, endDate));
        counter++;
        DataHandling.SaveData.addEventToFile(new Event(description.substring(0, idxOfFrom - 1), startDate, endDate), 'E');
        System.out.println("Gotcha! Added '" + toDoTask + "' to your To Do List!" +
                " This will happen from " + startDate + " to " + endDate + ", so please remember to mark it" +
                " on your calender! (Or ask me!)");
    }

    public static void deleteTask(String input) throws TaskNotFoundException {
        int idxToDelete = getIdx(input);
        if (idxToDelete >= counter) {
            throw new TaskNotFoundException();
        } else {
            System.out.println("Okay! I've removed this task from your To Do List:");
            System.out.println(toDoList.get(idxToDelete));
            toDoList.remove(idxToDelete);
            System.out.println("Now you have, let me count... " + toDoList.size() + " items left in your " +
                    "To Do List!");
            counter--;
        }
    }

    public static void createTask(String input) throws UnknownInputException {
        if (input.startsWith("delete")) {
            try {
                deleteTask(input);
            } catch (TaskNotFoundException e) {
                System.out.println("Sorry but... There is no task number " + (getIdx(input) + 1) + "... " +
                        "Let's try again!");
            }
        }
        else if (input.startsWith("todo")) {
            try {
                createTodo(input);
            } catch (EmptyTaskException e) {
                System.out.println("Uh oh, you did not specify a task to add! Let's try again!");
            } catch (IOException e) {
                System.out.println("Uh oh, I could not save that to the file...");
            }
        }
        else if (input.startsWith("deadline")) {
            try {
                createDeadline(input);
            } catch (EmptyTaskException e) {
                System.out.println("Uh oh, you did not specify a task to add! Let's try again!");
            } catch (MissingDeadlineException e) {
                System.out.println("Uh oh, you did not specify the deadline! Let's try again!");
            } catch (IOException e) {
                System.out.println("Uh oh, I could not save that to the file...");
            }
        }
        else if (input.startsWith("event")) {
            try {
                createEvent(input);
            } catch (EmptyTaskException e) {
                System.out.println("Uh oh, you did not specify a task to add! Let's try again!");
            } catch (MissingStartDateException e) {
                System.out.println("Uh oh, you did not specify a start time! Let's try again!");
            } catch (MissingDeadlineException e) {
                System.out.println("Uh oh, you did not specify the deadline! Let's try again!");
            } catch (IOException e) {
                System.out.println("Uh oh, I could not save that to the file...");
            }
        } else {
            throw new UnknownInputException();
        }
    }

    public static void printList() {
        System.out.println("Gotcha! Here are your tasks:");
        for (int i = 0; i < counter; i++) {
            if (toDoList.get(i) == null) {
                break;
            }
            System.out.println(i + 1 + ". " + toDoList.get(i));
        }
    }

    public static void main(String[] args) {
        System.out.println(LINE);
        System.out.println(WELCOME_MESSAGE);
        try {
            loadFile();
            System.out.println("Loading complete!");
        } catch (FileNotFoundException e) {
            System.out.println("Uh oh, there is no saved file yet! Please create a GermaBotsData.txt " +
                    "file under './src/data/GermaBotData'!!\n"
            + "Because there is no saved file found, I will start with an empty list!");
        } catch (FileReadException e) {
            System.out.println("Uh oh, there seems to be an issue with the save file...\n" +
                    "Because there is an error with the save file, I will start with an empty list!");
        }
        System.out.println("What may I do for you this fine day?");
        System.out.println(LINE);
        while (true) {
            String input;
            Scanner in = new Scanner(System.in);
            input = in.nextLine();
            if (input.equals("bye")) {
                break;
            }
            if (input.equals("list")) {
                if (toDoList.isEmpty()) {
                    System.out.println("Umm... You haven't added any Tasks yet... Let's try adding " +
                            "some now!");
                } else {
                    printList();
                    }
            } else if (input.contains("unmark")) {
                int idx = getIdx(input);
                toDoList.get(idx).setDone(false);
                System.out.println("Aww, not done? Okay, I'll mark this task as undone: "
                        + "[" + toDoList.get(idx).getStatusIcon() + "] " + toDoList.get(idx).getDescription());
            } else if (input.contains("mark")) {
                int idx = getIdx(input);
                toDoList.get(idx).setDone(true);
                System.out.println("Good job! I'll mark this task as done: "
                        + "[" + toDoList.get(idx).getStatusIcon() + "] " + toDoList.get(idx).getDescription());
            } else {
                try {
                    createTask(input);
                } catch (UnknownInputException e){
                    System.out.println("Uhh.. I'm sorry but I'm not quite sure what in the world '" + input + "' means..." +
                            " Remember to include the Task Type in front of the input!!");
                }
            }
        }
        System.out.println(LINE);
        System.out.println(GOODBYE_MESSAGE);
        System.out.println(LINE);
    }
}
