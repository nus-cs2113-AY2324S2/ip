package UI;

import Sinep.Sinep;
import TaskList.Deadline;
import TaskList.Event;
import TaskList.Task;
import TaskList.Todo;

public class CommandUI {
    public static void printGreeting() {
        final String greeting = "Hello! I'm Sinep, your personal chatbot!\n"
                + "What can I do for you today?";
        System.out.println(BackboneUI.line + BackboneUI.newLine + greeting + BackboneUI.newLine + BackboneUI.line);
    }

    public static void printBye() {
        final String bye = "Bye. Hope to see you again soon!";
        System.out.println(BackboneUI.line + BackboneUI.newLine + bye + BackboneUI.newLine + BackboneUI.line);

    }

    public static void printList() {
        System.out.println(BackboneUI.line);
        System.out.println("Here are the current tasks in your list:");
        if (Sinep.taskList.isEmpty()) {
            System.out.println("Great job! You have no tasks!");
        } else {
            for (int i = 0; i < Sinep.taskList.size(); i++) {
                System.out.println((i + 1) + "." + Sinep.taskList.get(i));
            }
        }
        System.out.println(BackboneUI.line);
    }


    /**
     * Prints a message indicating a Todo task has been added.
     *
     * @param newTodo The Todo task that has been added.
     */
    public static void todoAddMessage(Todo newTodo) {
        System.out.println(BackboneUI.line + BackboneUI.newLine + "Added to task list:" + BackboneUI.newLine + newTodo);
        System.out.println("Now you have " + Sinep.taskList.size() + " tasks in the list." + BackboneUI.newLine + BackboneUI.line);
    }

    /**
     * Prints a message indicating a Deadline task has been added, along with any warning messages.
     *
     * @param message The Deadline task that has been added.
     */
    public static void commandDeadlineMessage(Deadline message) {
        String warningMessage = message.getWarningMessage();
        String realMessage = message.toString();
        System.out.println(BackboneUI.line + BackboneUI.newLine +
                (warningMessage != null ? warningMessage + BackboneUI.newLine : "") +
                "Added to task list:" + BackboneUI.newLine + realMessage);
        System.out.println("Now you have " + Sinep.taskList.size() + " tasks in the list." + BackboneUI.newLine + BackboneUI.line);
    }

    /**
     * Prints a message indicating an Event task has been added, along with any warning messages.
     *
     * @param message The Event task that has been added.
     */
    public static void commandEventMessage(Event message) {
        String warningMessage = message.getWarningMessage();
        String realMessage = message.toString();
        System.out.println(BackboneUI.line + BackboneUI.newLine +
                (warningMessage != null ? warningMessage + BackboneUI.newLine : "") +
                "Added to task list:" + BackboneUI.newLine + realMessage);
        System.out.println("Now you have " + Sinep.taskList.size() + " tasks in the list." + BackboneUI.newLine + BackboneUI.line);
    }

    /**
     * Prints a message indicating a task has been marked as not done.
     *
     * @param taskIndex    The index of the task that has been unmarked.
     * @param markingTask  The task that has been unmarked.
     */
    public static void printUnmark(int taskIndex, Task markingTask) {
        System.out.println(BackboneUI.line + BackboneUI.newLine + "Got it! handleTask.Task " + (taskIndex + 1) + " marked as undone:");
        System.out.println(markingTask.getStatusIcon() + " " + markingTask.description + BackboneUI.newLine + BackboneUI.line);
    }

    /**
     * Prints a message indicating a task has been marked as done.
     *
     * @param taskIndex    The index of the task that has been marked.
     * @param markingTask  The task that has been marked.
     */
    public static void printMark(int taskIndex, Task markingTask) {
        System.out.println(BackboneUI.line + BackboneUI.newLine + "Got it! handleTask.Task " + (taskIndex + 1) + " marked as done:");
        System.out.println(markingTask.getStatusIcon() + " " + markingTask.description + BackboneUI.newLine + BackboneUI.line);
    }

    /**
     * Prints a message indicating a task has been deleted.
     *
     * @param taskIndex The index of the task that has been deleted.
     */
    public static void printDelete(int taskIndex) {
        System.out.println(BackboneUI.line);
        System.out.println("Noted. I have removed this task:");
        Task removedTask = Sinep.taskList.remove(taskIndex);
        System.out.println(removedTask);
        System.out.println("Now you have " + Sinep.taskList.size() + " tasks left.");
        System.out.println(BackboneUI.line);
    }
}
