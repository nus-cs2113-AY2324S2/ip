package command;

import templates.TaskList;

public class HelpCommand extends BaseCommand {

    public HelpCommand() {
        super(false, "");
    }

    @Override
    public String execute(TaskList taskList) {
        return "Available Commands:\n" +
                "1. bye - Exits the application.\n" +
                "2. list - Lists all tasks.\n" +
                "3. done <task number> - Marks a task as done.\n" +
                "4. todo <description> - Adds a todo task.\n" +
                "5. deadline <description> /by <datetime> - Adds a deadline task. Datetime format: YYYY-MM-DD.\n" +
                "6. event <description> /from <datetime> /to <datetime> - Adds an event task. Datetime format: YYYY-MM-DD.\n"
                +
                "7. delete <task number> - Deletes a task.\n" +
                "8. find <keyword> - Finds tasks containing the given keyword.\n" +
                "9. undo - Undoes the most recent command that modified data.\n" +
                "10. help - Displays this help message.\n" +
                "Note: <task number> corresponds to the number shown next to the task in the list command. <description> is the text describing the task. <datetime> must be in the specified format for deadline and event tasks.";

    }
}