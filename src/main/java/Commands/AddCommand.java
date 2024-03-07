package Commands;

import Events.*;
import HikoUi.Ui;
import Storage.Store;

public class AddCommand extends Command {

    private Store store; // Add a reference to the Store


    /**
     * Constructs an AddCommand with the specified command word, parameter, and Store reference.
     * The command word determines the type of task to be added.
     *
     * @param commandWord The command word indicating the type of task to add ("todo", "deadline", "event").
     * @param parameter The parameter(s) provided for creating the task, such as the description or dates.
     * @param store The Store object used for persisting changes to the task list.
     */
    public AddCommand(String commandWord, String parameter, Store store) {
        super(commandWord, parameter);
        this.store = store; // Initialize the Store reference
    }


    /**
     * Executes the command by adding a new task of the specified type to the task list.
     * The specific task type and its details are determined by the command word and parameters.
     * After adding the task, changes are saved to persistent storage.
     *
     * @param tasks The TaskList to which the new task will be added.
     * @param ui The Ui instance used for interacting with the user and displaying messages.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) {
        switch (commandWord) {
        case "todo":
            handleAddTodo(tasks, ui);
            break;
        case "deadline":
            handleAddDeadline(tasks, ui);
            break;
        case "event":
            handleAddEvent(tasks, ui);
            break;
        default:
            ui.defaultmsg(); // Show default message for unknown command
            break;
        }
        store.saveTasks(); // Save tasks after adding
    }



    /**
     * Handles adding a new todo task.
     *
     * @param tasks The TaskList to which the new todo will be added.
     * @param ui The Ui instance for user interaction.
     */
    private void handleAddTodo(TaskList tasks, Ui ui) {
        if (parameter.isEmpty()) {
            ui.defaultmsg(); // Updated to use UI's default message method for consistency
        } else {
            Task newToDo = new ToDo(parameter);
            tasks.addTask(newToDo);
            ui.showAddTask(newToDo);
        }
    }


    /**
     * Handles adding a new deadline task.
     *
     * @param tasks The TaskList to which the new deadline will be added.
     * @param ui The Ui instance for user interaction.
     */
    private void handleAddDeadline(TaskList tasks, Ui ui) {
        try {
            Task newDeadline = new Deadline(parameter);
            tasks.addTask(newDeadline);
            ui.showAddTask(newDeadline);
        } catch (Exception e) {
            ui.defaultmsg(); // Use UI's default message for input errors
        }
    }


    /**
     * Handles adding a new event task.
     *
     * @param tasks The TaskList to which the new event will be added.
     * @param ui The Ui instance for user interaction.
     */
    private void handleAddEvent(TaskList tasks, Ui ui) {
        try {
            Task newEvent = new Event(parameter);
            tasks.addTask(newEvent);
            ui.showAddTask(newEvent);
        } catch (Exception e) {
            ui.defaultmsg(); // Use UI's default message for input errors
        }
    }

    /**
     * Indicates whether executing this command should terminate the application.
     *
     * @return false always, as adding a task does not terminate the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
