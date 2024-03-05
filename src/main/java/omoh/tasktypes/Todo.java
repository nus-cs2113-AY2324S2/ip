package omoh.tasktypes;
import omoh.Omoh;

import omoh.customexceptions.EmptyTodoException;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
        this.type = "T";
    }

    /**
     * Adds a todo task based on the input string.
     *
     * @param input The input string containing the todo task description.
     * @throws EmptyTodoException if the description of the todo task is empty.
     */
    public static void addTodo (String input) throws EmptyTodoException {
        //extracts task portion from input, after the "todo" keyword
        String description = input.substring("todo".length()).trim();
        if (description.isEmpty()) {
            throw new EmptyTodoException();
        }
        Task.tasks.add(new Todo(description));
        Task.totalTasks++;
        printTodoMessage(Task.tasks.get(Task.totalTasks - 1));
    }

    /**
     * Prints a message indicating that a todo task has been added.
     *
     * @param description The todo task that has been added.
     */
    public static void printTodoMessage (Task description) {
        Omoh.printHorizontalLine();
        System.out.println("Got it. I've added this task:");
        System.out.println("[T][ ] " + description.description);
        System.out.println("Now you have " + Task.totalTasks + " tasks in the list.");
        Omoh.printHorizontalLine();
    }

    /**
     * Returns a string representation of the Todo task.
     *
     * @return A string representing the Todo task, including its task status and description.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
