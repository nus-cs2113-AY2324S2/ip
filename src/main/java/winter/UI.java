package winter;

import java.io.IOException;
import java.util.Scanner;


import winter.checkedexceptions.Exceptions;
import winter.task.Task;

public class UI {
    private static final UI ui = new UI();
    public UI()
    {
        showWelcome();
        sayHi();
    }

    protected void showWelcome() {
        String logo = "  __      __.__        __                \n" +
                "/  \\    /  \\__| _____/  |_  ___________ \n" +
                "\\   \\/\\/   /  |/    \\   __\\/ __ \\_  __ \\\n" +
                " \\        /|  |   |  \\  | \\  ___/|  | \\/\n" +
                "  \\__/\\  / |__|___|  /__|  \\___  >__|   \n" +
                "       \\/          \\/          \\/    ";
        System.out.println("Hello from\n" + logo);
    }

    // Method for greeting message
    protected void sayHi() {
        String greet = "Hello! I'm Winter!\nWhat can I do for you?";
        showLine();
        System.out.println(greet);
        showLine();
    }

    // Method for farewell message
    public void sayBye() {
        String farewell = "Farewell. Hope to see you again soon!";
        showLine();
        System.out.println(farewell);
        showLine();
    }

    protected void showLoadingError() {
        String errorMessage = "Oh dear! I've been rummaging but I can't find any saved files!";
        showLine();
        System.out.println(errorMessage);
        showLine();
    }

    public void showLine() {
        String line = "-----------------------------------\n";
        System.out.print(line);
    }

    public void showIndent() {
        String indent = "   ";
        System.out.print(indent);
    }

    protected String acceptInput () throws IOException {
        Scanner input = new Scanner(System.in);
        String inputString;
        inputString = input.nextLine();

        return inputString;
    }

    public void showTaskAddedConfirm(TaskList tasks, Task newTask) {
        showLine();
        showIndent();
        System.out.println("Awesome! I've added this task!");
        System.out.println(newTask);
        showIndent();
        System.out.println("Now, you have " + (tasks.getCurrentTaskIndex() + 1) + " tasks in your list.");
        showLine();
    }

    public void showTaskRemovedConfirm(TaskList tasks, int removedTaskNumber) {
        showLine();
        showIndent();
        System.out.println("No problemo, I've removed this task: ");

        System.out.println(tasks.getTask(removedTaskNumber));
        showIndent();
        System.out.println("Now, you have " + tasks.getCurrentTaskIndex() + " tasks in your list.");
        showLine();
    }

    public void showTaskMarked(Task markedTask) {
        showLine();
        showIndent();
        System.out.println("Woohoo! I've marked this task as done:");
        showIndent();
        System.out.println(markedTask.getDoneCheckbox() + markedTask.getTaskName());
        showLine();
    }


    public static void handleCheckedExceptions(Exceptions exception) {
        switch (exception) {
        case INVALIDTODO:
            System.out.println("Oh no! You did not enter a ToDo task after the todo command!");
            System.out.println("The correct format should be 'todo (task)'");
            ui.showLine();
            break;
        case INVALIDDEADLINE:
            System.out.println("Oh no! You did not enter a Deadline task / specify the deadline correctly " +
                    "after the deadline command!");
            System.out.println("The correct format should be 'deadline (task) by/ (deadline)'");
            ui.showLine();
            break;
        case INVALIDEVENT:
            System.out.println("Oh no! You did not enter a Event task / specify the event correctly " +
                    "after the event command!");
            System.out.println("The correct format should be 'event (task) /from (start time) /to (end time)'");
            ui.showLine();
            break;
        case EMPTYCOMMAND:
            System.out.println("Oh no! You did not enter anything! What can one do with nothing? I wonder...");
            System.out.println("Valid commands are todo, deadline, event\nThanks!");
            ui.showLine();
            break;
        case INVALIDCOMMAND:
            System.out.println("Thank you for your input but you did not enter any command! :(");
            System.out.println("Valid commands are todo, deadline, event\nThanks!");
            ui.showLine();
            break;
        case INVALIDDELETE:
            System.out.println("Haishhh, I don't know what to remove! Please specify accordingly! :(");
            System.out.println("The correct format should be 'delete (task number)'\nThanks!");
            ui.showLine();
            break;
        }
    }

    protected void showError(String errormessage) {
        System.out.println("Oh no! We have an error!\n" + errormessage);
    }
}
