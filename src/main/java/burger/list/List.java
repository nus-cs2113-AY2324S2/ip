package burger.list;

import java.util.ArrayList;
import java.util.IllegalFormatException;

import burger.BurgerException;
import burger.task.Deadline;
import burger.task.Event;
import burger.task.Task;
import burger.task.Todo;

import static burger.Burger.printLine;

public class List {

    private final ArrayList<Task> todoList;

    public int totalTasks;
    public List() {
        todoList = new ArrayList<>();
        totalTasks = 0;
    }
    /**
     * Returns boolean value base on whether the input matches one of the commands.
     *
     * @param textArray user input in array type.
     * @return true if it's a valid command and false otherwise.
     */
    public boolean isValidCommand(String[] textArray) {
        boolean isValid = true;
        String command = textArray[0];
        switch (command) { // assume inputs for commands are correct
        case "mark":
            // fallthrough
        case "unmark":
            int idx = Integer.parseInt(textArray[1]) - 1;
            getMark(idx, command);
            break;
        case "todo":
            addTodo(textArray);
            break;
        case "deadline":
            addDeadline(textArray);
            break;
        case "event":
            addEvent(textArray);
            break;
        default:
            isValid = false;
            break;
        }
        return isValid;
    }

    /**
     * Returns the task at the specified index.
     * If the index is out of bounds, null is returned.
     *
     * @param idx index of task.
     * @return task.
     * @throws ArrayIndexOutOfBoundsException idx is out of bounds.
     */
    public Task getTask(int idx) {
        try {
            return this.todoList.get(idx);
        } catch (ArrayIndexOutOfBoundsException e) {
            printLine();
            System.out.println("UwU? Do you know how to count?");
            printLine();
            return null;
        }
    }
    /**
     * Adds a new todo to the list.
     *
     * @param todo the todo input by the user.
     */
    public void addTodo(String[] todo) {
        try {
            if (todo.length == 1) {
                throw new BurgerException();
            }
            StringBuilder todoText = new StringBuilder(todo[1]);
            for (int i = 2; i < todo.length; i++) {
                todoText.append(' ').append(todo[i]);
            }
            Task newTodo = new Todo(todoText.toString());
            addTask(newTodo);
        } catch (BurgerException e) {
            printEmptyDescription();
        }
    }

    /**
     * Adds a new event to the list.
     *
     * @param event the event input by the user.
     * @throws ArrayIndexOutOfBoundsException if text is empty after the "event" command.
     * @throws IllegalFormatException if event format is not followed.
     */
    public void addEvent(String[] event) {
        try {
            if (event.length == 1) {
                throw new BurgerException();
            }
            StringBuilder eventText = new StringBuilder(event[1]);
            int i = 2;
            while (!event[i].startsWith("/")) {
                eventText.append(' ').append(event[i]);
                i++;
            }
            eventText.append(' ').append("(from: ");
            i++;
            while (!event[i].startsWith("/")) {
                eventText.append(' ').append(event[i]);
                i++;
            }
            i++;
            eventText.append(' ').append("to: ").append(event[i]).append(')');
            Task newEvent = new Event(eventText.toString());
            addTask(newEvent);
        } catch (ArrayIndexOutOfBoundsException e) {
            printInvalidInputMessage();
        } catch (BurgerException e) {
            printEmptyDescription();
        }
    }

    /**
     * Adds a new deadline to the list.
     *
     * @param deadline the deadline input by the user.
     * @throws ArrayIndexOutOfBoundsException if text is empty after the "deadline" command.
     * @throws IllegalFormatException if deadline format is not followed.
     */
    public void addDeadline(String[] deadline) {
        try {
            if (deadline.length == 1) {
                throw new BurgerException();
            }
            StringBuilder deadlineText = new StringBuilder(deadline[1]);
            int i = 2;
            while (!deadline[i].startsWith("/")) {
                deadlineText.append(' ').append(deadline[i]);
                i++;
            }
            deadlineText.append(' ').append("(by: ");
            i++;
            while (i < deadline.length - 1) {
                deadlineText.append(deadline[i]).append(' ');
                i++;
            }
            deadlineText.append(deadline[i]).append(')');
            Task newDeadline = new Deadline(deadlineText.toString());
            addTask(newDeadline);
        } catch (ArrayIndexOutOfBoundsException e) {
            printInvalidInputMessage();
        } catch (BurgerException e) {
            printEmptyDescription();
        }
    }

    private static void printInvalidInputMessage() {
        printLine();
        System.out.println("Oi, the input is wrong!");
        printLine();
    }

    private void printEmptyDescription() {
        printLine();
        System.out.println("UwU~ Where is your description?");
        printLine();
    }

    /**
     * Adds task to the list and prints out a message.
     *
     * @param task Task that is being added.
     */
    public void addTask(Task task) {
        this.todoList.add(task);
        printLine();
        System.out.println("Got it. I've added this task:");
        this.todoList.get(totalTasks).printTask();
        this.totalTasks++;
        System.out.println();
        System.out.println("Now you have " + totalTasks + " tasks in the list.");
        printLine();
    }
    /**
     * Returns the mark from a task in the list.
     *
     * @param idx the index of the task.
     * @param command Command to "mark" or "unmark" the task.
     */
    public void getMark(int idx, String command) {
        Task currTask = getTask(idx);
        if (command.equals("mark")) {
            System.out.println("Nice! I've marked this task as done:");
            currTask.markDone();
        } else {
            System.out.println("OK, I've marked this task as not done yet:");
            currTask.unmarkDone();
            printTaskWithLine(idx);
        }
        printTaskWithLine(idx);
    }
    /**
     * Prints the task with a horizontal line.
     *
     * @param idx Index of the task.
     */
    public void printTaskWithLine(int idx) {
        this.todoList.get(idx).printTask();
        System.out.println();
        printLine();
    }

    public void printTodoList() {
        printLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < todoList.size(); i++) {
            System.out.print(i+1 + ". ");
            todoList.get(i).printTask();
            System.out.println();
        }
        printLine();
    }
}
