package kobot.command;

import kobot.ui.Ui;
import kobot.task.TaskList;

public class Parser {
    private static final String EXIT_COMMAND = "exit";
    private static final String BYE_COMMAND = "bye";
    private static final String LIST_COMMAND = "list";
    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";
    private static final String MARK_COMMAND = "mark";
    private static final String UNMARK_COMMAND = "unmark";
    private static final String DELETE_COMMAND = "delete";
    private static final Boolean IS_MARK_COMMAND = true;
    
    private static final int MAX_COMMAND_SPLIT_LIMIT = 2;
    private static final int COMMAND_INDEX = 0;
    private static final int ARGUMENT_INDEX = 1;
    
    private Boolean isExit;
    private String command;
    private String arguments;

    public Parser() {
        this.isExit = false;
    }

    public void exit() {
        this.isExit = true;
    }

    public Boolean isExit() {
        return this.isExit;
    }

    /**
     * 
     * Splits user input into command and arguments.
     *
     * @param input User input.
     */
    public void parseCommand(String input) {
        String[] parts = input.split(" ", MAX_COMMAND_SPLIT_LIMIT);
        this.command = parts[COMMAND_INDEX].toLowerCase();
        if (parts.length > 1) {
            this.arguments = parts[ARGUMENT_INDEX];
        } else {
            this.arguments = "";
        }
    }
    
    /**
     * Prepares the relevant command object.
     *
     * @param taskList List of current tasks.
     *                 
     * @return Returns the command to be executed.
     */
    public Command prepareCommand(TaskList taskList) {
        switch (this.command) {
        case EXIT_COMMAND:
            // Fallthrough
        case BYE_COMMAND:
            exit();
            return null;
        case LIST_COMMAND:
            return new ListCommand(taskList);
        case TODO_COMMAND:
            return new ToDoCommand(taskList, this.arguments);
        case DEADLINE_COMMAND:
            return new DeadlineCommand(taskList, this.arguments);
        case EVENT_COMMAND:
            return new EventCommand(taskList, this.arguments);
        case MARK_COMMAND:
            return new MarkUnmarkCommand(IS_MARK_COMMAND, taskList, this.arguments);
        case UNMARK_COMMAND:
            return new MarkUnmarkCommand(!IS_MARK_COMMAND, taskList, this.arguments);
        case DELETE_COMMAND:
            return new DeleteCommand(taskList, this.arguments);
        default:
            Ui.printInvalidCommandErrorMessage();
            return null;
        }
    }
}
