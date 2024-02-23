package input;

import exceptions.*;
import static savedData.Data.loadData;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class HandleInput {
    static String line = "\t____________________________________________________________";
    static ArrayList<Task> tasks = new ArrayList<>(100);

    public static void loadDataFromFile(){
        try {
            ArrayList<Task> data = loadData();
            tasks.addAll(data);
        } catch (FileNotFoundException e) {
            System.out.println("Saved file not found.");
        }
    }

    private static void echo(String input) {
        System.out.println("\t Got it. I've added this task: ");
        System.out.println(("\t   " + tasks.get(tasks.size() - 1).toString()));
        String task = (tasks.size() == 1) ? "task " : "tasks ";
        System.out.println("\t Now you have " + tasks.size() + " " + task + "in the tasks.");
    }

    private static void sayBye() {
        System.out.println("\t Bye. Hope to see you again soon!");
    }

    private static void createTodo(String input) throws EmptyTaskException {
        String todo = input.substring(input.indexOf("todo ") + 5);
        if (todo.isBlank()) {
            throw new EmptyTaskException();
        }
        tasks.add(new ToDo(todo));

        echo(input);
    }


    private static void createDeadline(String input) throws EmptyTaskException, MissingDeadlineException {
        String description = input.replaceFirst("deadline ", "");
        int by = description.indexOf("/");
        if (by == -1){
            throw new MissingDeadlineException();
        }
        String deadline = description.substring(0, by - 1);
        if (deadline.isBlank()){
            throw new EmptyTaskException();
        }
        String date = description.substring(by + 4);
        tasks.add(new Deadline(deadline, date));
        echo(input);
    }

    private static void createEvent(String input) throws EmptyTaskException,
            MissingDeadlineException, MissingStartException {
        String description = input.replaceFirst("event ", "");
        int from = description.indexOf("/from");
        if (from == -1) {
            throw new MissingStartException();
        }
        int by = description.indexOf("/to");
        if (by == -1) {
            throw new MissingDeadlineException();
        }
        String startDate = description.substring(from + 6, by - 1);
        String endDate = description.substring(by + 4);
        if (from == 0) {
            throw new EmptyTaskException();
        }
        String event = description.substring(0, from - 1);
        if (event.isBlank()){
            throw new EmptyTaskException();
        }
        tasks.add(new Event(event, startDate, endDate));
        echo(input);
    }

    private static void deleteTask(String input) throws ArrayListOutOfBoundsException {
        int index = Integer.parseInt(input.substring(7)) - 1;
        if (tasks.size() < index) {
            throw new ArrayListOutOfBoundsException();
        }
        System.out.println("\t Noted. I've removed this task:");
        System.out.println(("\t   " + tasks.get(index).toString()));
        tasks.remove(index);
        String task = (tasks.size() == 1) ? "task " : "tasks ";
        System.out.println("\t Now you have " + tasks.size() + " " + task + "in the list.");
    }

    private static void handleInput(String input) throws UnknownInputException {
        if (input.contains("todo")) {
            try {
                createTodo(input);
            } catch (EmptyTaskException e) {
                System.out.println("Todo should not be empty!");
            }
        } else if (input.contains("deadline")) {
            try {
                createDeadline(input);
            } catch (MissingDeadlineException e) {
                System.out.println("Include when this deadline is due as such:" +
                        "{deadline} /by {deadline}");
            } catch (EmptyTaskException e){
                System.out.println("Deadline should not be empty!");
            }
        } else if (input.contains("event")) {
            try {
                createEvent(input);
            } catch (MissingDeadlineException e) {
                System.out.println("Include when this event ends as such:" +
                        "{event} /from {start date} /to {end date}");
            } catch (MissingStartException e) {
                System.out.println("Include when this event starts as such:" +
                        "{event} /from {start date} /to {end date}");
            } catch (EmptyTaskException e) {
                System.out.println("Event should not be empty!");
            }
        } else if (input.contains("bye")) {
            sayBye();
        } else if (input.startsWith("delete")) {
            try {
                deleteTask(input);
            } catch (ArrayListOutOfBoundsException e) {
                System.out.println("This item does not exist in your list. Try a different item.");
            }
        } else {
            throw new UnknownInputException();
        }
    }

    public static int getIndexToMark(String input) {
        int idx = Integer.parseInt(input.substring(input.indexOf(" ") + 1));
        return idx - 1;
    }

    public static void listItems() {
        System.out.println("\t Here are the tasks in your list:");
        for (int x = 0; x < tasks.size(); x++) {
            System.out.println("\t  " + (x + 1) + "." + tasks.get(x).toString());
        }
    }

    public static void unmarkItem(String input) {
        int idx = getIndexToMark(input);
        tasks.get(idx).setDone(false);
        System.out.println("\t OK, I've marked this task as not done yet:");
        System.out.println("\t " + tasks.get(idx).toString());
    }

    public static void markItem(String input) {
        int idx = getIndexToMark(input);
        tasks.get(idx).setDone(true);
        System.out.println("\t Nice! I've marked this task as done:");
        System.out.println("\t " + tasks.get(idx).toString());
    }

    public static void chat() {
        loadDataFromFile();
        String input;
        do {
            input = new Scanner(System.in).nextLine();
            System.out.println(line);
            if (input.equals("list")) {
                listItems();
            } else if (input.contains("unmark")) {
                unmarkItem(input);
            } else if (input.contains("mark")) {
                markItem(input);
            } else {
                try {
                    handleInput(input);
                } catch (UnknownInputException e){
                    System.out.println("\t I've not seen this input before. Please tell me something else I can help you with.");
                }
            }
            System.out.println(line);
        } while (!input.equals("bye"));
    }
}
