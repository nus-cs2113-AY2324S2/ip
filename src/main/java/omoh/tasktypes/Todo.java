package omoh.tasktypes;
import omoh.Omoh;

import omoh.customexceptions.EmptyTodoException;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
        this.type = "T";
    }

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

    public static void printTodoMessage (Task description) {
        Omoh.printHorizontalLine();
        System.out.println("Got it. I've added this task:");
        System.out.println("[T][ ] " + description.description);
        System.out.println("Now you have " + Task.totalTasks + " tasks in the list.");
        Omoh.printHorizontalLine();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
