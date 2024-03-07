package bobby;

import java.util.ArrayList;

public class Ui {
    public void showWelcomeMessage() {
        System.out.println("Hello I'm Bobby\n" + "What can I do for you?");
    }

    public void showByeMessage() {
        System.out.println("See you again soon!");
    }

    public void showSavingError() {
        System.out.println("Error saving changes.");
    }

    public void showInvalidCommandError() {
        System.out.println("Sorry, I didn't quite understand that.\nPlease enter a valid command.");
    }

    public void showMarkMessage(ArrayList<Task> list, int entry) {
        System.out.println("Marked as done");
        System.out.println(entry + "." + list.get(entry - 1));
    }

    public void showUnmarkMessage(ArrayList<Task> list, int entry) {
        System.out.println("Unmarked");
        System.out.println(entry + "." + list.get(entry - 1));
    }

    public void showList(ArrayList<Task> tasks) {
        for (int i = 0; i < tasks.size(); i += 1) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
    }

    public void showInvalidTodoMessage() {
        System.out.println("Please enter a valid todo in this format:");
        System.out.println("todo Attend CS2113 tutorial");
    }

    public void showValidTodoMessage(ArrayList<Task> tasks) {
        System.out.println("Okay, added:\n" + tasks.get(tasks.size() - 1));
        System.out.println("Now you have " + tasks.size() + " task(s) in the list.");
    }

    public void showInvalidDeadlineMessage() {
        System.out.println("Please enter a valid deadline task in this format:");
        System.out.println("deadline Return Book /by Sunday");
    }

    public void showValidDeadlineMessage(ArrayList<Task> tasks) {
        System.out.println("Okay, added:\n" + tasks.get(tasks.size() - 1));
        System.out.println("Now you have " + tasks.size() + " task(s) in the list.");
    }

    public void showInvalidEventMessage() {
        System.out.println("Please enter a valid event in this format:");
        System.out.println("event Project Meeting /from Mon 2pm /to 4pm");
    }

    public void showValidEventMessage(ArrayList<Task> tasks) {
        System.out.println("Okay, added:\n" + tasks.get(tasks.size() - 1));
        System.out.println("Now you have " + tasks.size() + " task(s) in the list.");
    }

    public void showDeleteMessage(ArrayList<Task> tasks, int entry) {
        System.out.println("Noted, I've removed this task:\n" + tasks.get(entry - 1));
        System.out.println("Now you have " + (tasks.size() - 1) + " task(s) in the list.");
    }

    public void showLoadingError() {
        System.out.println("Error loading saved tasks, file not found");
    }

    public void showTextFileError() {
        System.out.println("Something went wrong :(");
    }

    public void showNoMatchMessage() {
        System.out.println("No matching tasks found.");
    }

    public void showMatchMessage() {
        System.out.println("Here are the matching tasks in your list:");
    }
}
