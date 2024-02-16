package burger.list;

import java.util.ArrayList;

import burger.BurgerException;
import burger.task.Deadline;
import burger.task.Event;
import burger.task.Task;
import burger.task.Todo;

import static burger.Burger.printLine;
import static burger.BurgerException.*;

public class List {

    private final ArrayList<Task> taskList;

    public int totalTasks;
    public List() {
        taskList = new ArrayList<>();
        totalTasks = 0;
    }
    /**
     * Returns boolean value base on whether the input matches one of the commands.
     *
     *
     * @param textArray user input in array type.
     * @return true if it's a valid command and false otherwise.
     * @throws ArrayIndexOutOfBoundsException when the description after the command is empty.
     */
    public boolean isValidCommand(String[] textArray) {
        boolean isValid = true;
        try {
            String command = textArray[0];
            switch (command) {
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
        } catch (ArrayIndexOutOfBoundsException e) {
            printEmptyDescription();
            return isValid;
        }

    }

    /**
     * Returns the task at the specified index.
     * If the index is out of bounds, null is returned.
     *
     * @param idx index of task.
     * @return task.
     * @throws IndexOutOfBoundsException idx is out of bounds.
     */
    public Task getTask(int idx) {
        try {
            return this.taskList.get(idx);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }
    /**
     * Adds a new todo to the list.
     *
     * @param todo the todo input by the user.
     */
    public void addTodo(String[] todo) {
        StringBuilder todoText = new StringBuilder(todo[1]);
        for (int i = 2; i < todo.length; i++) {
            todoText.append(' ').append(todo[i]);
        }
        Task newTodo = new Todo(todoText.toString());
        addTask(newTodo);
    }

    /**
     * Adds a new event to the list.
     *
     * @param event the event input by the user.
     * @throws ArrayIndexOutOfBoundsException if deadline format is not followed.
     */
    public void addEvent(String[] event) {
        try {
            StringBuilder eventText = new StringBuilder(event[1]);
            int i = 2;
            while (!event[i].startsWith("/from")) {
                eventText.append(' ').append(event[i]);
                i++;
            }
            eventText.append(' ').append("(from: ");
            i++;
            while (!event[i].startsWith("/to")) {
                eventText.append(event[i]).append(' ');
                i++;
            }
            eventText.append("to: ");
            i++;
            while (i < event.length - 1) {
                eventText.append(event[i]).append(' ');
                i++;
            }
            eventText.append(event[i]).append(')');
            Task newEvent = new Event(eventText.toString());
            addTask(newEvent);
        } catch (ArrayIndexOutOfBoundsException e) {
            printInvalidEventInputMessage();
        }
    }

    /**
     * Adds a new deadline to the list.
     *
     * @param deadline the deadline input by the user.
     * @throws ArrayIndexOutOfBoundsException if deadline format is not followed.
     */
    public void addDeadline(String[] deadline) {
        try {
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
            printInvalidDeadlineInputMessage();
        }
    }

    /**
     * Adds task to the list and prints out a message.
     *
     * @param task Task that is being added.
     */
    public void addTask(Task task) {
        this.taskList.add(task);
        printLine();
        System.out.println("Got it. I've added this task:");
        this.taskList.get(totalTasks).printTask();
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
     * @throws BurgerException if index input is out of the range of list of tasks.
     */
    public void getMark(int idx, String command) {
        try {
            Task currTask = getTask(idx);
            if (currTask == null) {
                throw new BurgerException();
            }
            if (command.equals("mark")) {
                System.out.println("Nice! I've marked this task as done:");
                currTask.markDone();
            } else {
                System.out.println("OK, I've marked this task as not done yet:");
                currTask.unmarkDone();
            }
            printTaskWithLine(idx);
        } catch (BurgerException e) {
            printOutOfIndexMessage();
        }

    }

    /**
     * Prints the task with a horizontal line.
     *
     * @param idx Index of the task.
     */
    public void printTaskWithLine(int idx) {
        this.taskList.get(idx).printTask();
        System.out.println();
        printLine();
    }

    /**
     * Prints task list.
     */
    public void printTaskList() {
        printLine();
        System.out.println("Here are the tasks in your list:");
        if (taskList.isEmpty()) {
            System.out.println("Don't be lazy... start doing something");
        }
        for (int i = 0; i < taskList.size(); i++) {
            System.out.print(i+1 + ". ");
            taskList.get(i).printTask();
            System.out.println();
        }
        printLine();
    }
}
