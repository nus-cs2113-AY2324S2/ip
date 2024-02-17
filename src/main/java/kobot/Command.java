package kobot;

import kobot.storage.Storage;
import kobot.task.TaskList;

public class Command {
    private Boolean isExit;
    private String command;
    private String arguments;
    private String description;
    private String by;
    private String from;
    private String to;

    public Command() {
        this.isExit = false;
    }

    public void exit() {
        this.isExit = true;
    }

    public Boolean getIsExit() {
        return this.isExit;
    }

    /**
     * Splits user input into command and arguments.
     *
     * @param input User input.
     */
    public void parseCommand(String input) {
        String[] parts = input.split(" ", 2);
        this.command = parts[0].toUpperCase();
        if (parts.length > 1) {
            this.arguments = parts[1];
        } else {
            this.arguments = "";
        }
    }

    /**
     * Parses arguments (description) for ToDo.
     *
     * @throws ArrayIndexOutOfBoundsException If arguments are not found.
     */
    public void parseToDoArguments() throws NullPointerException, ArrayIndexOutOfBoundsException {
        this.description = this.arguments.trim();

        if (this.description.isEmpty()) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Parses arguments (description, by) for Deadline.
     *
     * @throws ArrayIndexOutOfBoundsException If arguments are not found.
     */
    public void parseDeadlineArguments() throws NullPointerException, ArrayIndexOutOfBoundsException {
        String[] argList = this.arguments.split("/by");
        this.description = argList[0].trim();
        this.by = argList[1].trim();

        if (this.description.isEmpty() || this.by.isEmpty()) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Parses arguments (description, from, to) for Event.
     *
     * @throws ArrayIndexOutOfBoundsException If arguments are not found.
     */
    public void parseEventArguments() throws NullPointerException, ArrayIndexOutOfBoundsException {
        String[] argList = this.arguments.split("/from");
        this.description = argList[0].trim();

        String[] dateList = argList[1].split("/to");
        this.from = dateList[0].trim();
        this.to = dateList[1].trim();

        if (this.description.isEmpty() || this.from.isEmpty() || this.to.isEmpty()) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Executes the user command.
     *
     * @param taskList List of current tasks.
     */
    public void executeCommand(TaskList taskList) {
        switch (this.command) {
        case "EXIT":
            // Fallthrough
        case "BYE":
            exit();
            break;

        case "LIST":
            taskList.printTaskList();
            break;

        case "TODO":
            try {
                parseToDoArguments();
                taskList.addToDo(this.description);
                Storage.updateFile(taskList);
            } catch (NullPointerException | IndexOutOfBoundsException exception) {
                Ui.printMissingArgumentErrorMessage();
                Ui.printToDoCommandUsage();
            } catch (IllegalArgumentException exception) {
                Ui.printEmptyArgumentErrorMessage();
                Ui.printToDoCommandUsage();
            }
            break;

        case "DEADLINE":
            try {
                parseDeadlineArguments();
                taskList.addDeadline(this.description, this.by);
                Storage.updateFile(taskList);
            } catch (NullPointerException | IndexOutOfBoundsException exception) {
                Ui.printMissingArgumentErrorMessage();
                Ui.printDeadlineCommandUsage();
            } catch (IllegalArgumentException exception) {
                Ui.printEmptyArgumentErrorMessage();
                Ui.printDeadlineCommandUsage();
            }
            break;

        case "EVENT":
            try {
                parseEventArguments();
                taskList.addEvent(this.description, this.from, this.to);
                Storage.updateFile(taskList);
            } catch (NullPointerException| IndexOutOfBoundsException exception) {
                Ui.printMissingArgumentErrorMessage();
                Ui.printEventCommandUsage();
            } catch (IllegalArgumentException exception) {
                Ui.printEmptyArgumentErrorMessage();
                Ui.printEventCommandUsage();
            }
            break;

        case "MARK":
            try {
                taskList.markTask(Integer.parseInt(this.arguments) - 1);
                Storage.updateFile(taskList);
            } catch (NumberFormatException exception ) {
                Ui.printMarkCommandUsage();
            } catch (IndexOutOfBoundsException exception) {
                Ui.printIndexOutOfBoundsMessage("mark");
            }
            break;

        case "UNMARK":
            try {
                taskList.unmarkTask(Integer.parseInt(this.arguments) - 1);
                Storage.updateFile(taskList);
            } catch (NumberFormatException exception ) {
                Ui.printUnmarkCommandUsage();
            } catch (IndexOutOfBoundsException exception) {
                Ui.printIndexOutOfBoundsMessage("unmark");
            }
            break;
            
        case "DELETE":
            try {
                taskList.deleteTask(Integer.parseInt(this.arguments) - 1);
                Storage.updateFile(taskList);
            } catch (NumberFormatException exception) {
                Ui.printDeleteCommandUsage();
            } catch (IndexOutOfBoundsException exception) {
                Ui.printIndexOutOfBoundsMessage("delete");
            }
            break;

        default:
            System.out.println("Invalid command. Please try again.");
            break;
        }
    }
}
