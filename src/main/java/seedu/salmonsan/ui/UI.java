package seedu.salmonsan.ui;

public class UI {
    final String WELCOME = "Salmon-San desu! \nYoroshikuonegaishimasu (^.^)/ \n type `help` to see the list of command";
    final String QUERY = "How can I assist you today?";
    final String DIVIDER = "--------------------------------";
    public UI() {}

    public void welcome() {
        System.out.println(WELCOME);
    }

    public void askQuery() {
        System.out.println(QUERY);
    }

    public void printDivider() {
        System.out.println(DIVIDER);
    }

    public void printHelp() {
        System.out.println("- `todo TASK`: to add a todo task" + System.lineSeparator() +
                           "- `event TASK /from START_TIME /to END_TIME`: to add an event task" + System.lineSeparator() +
                           "- `deadline TASK /by END_TIME`: to add a deadline task" + System.lineSeparator() +
                           "- `list`: to show all tasks in list" + System.lineSeparator() +
                           "- `mark TASK_INDEX`: to mark task as done" + System.lineSeparator() +
                           "- `unmark TASK_INDEX`: to unmark task as not done" + System.lineSeparator() +
                           "- `delete TASK_INDEX`: to delete task" + System.lineSeparator() +
                           "- `find KEYWORD`: to find tasks with specified keyword" + System.lineSeparator() +
                           "- `bye`: to exit the programme");
    }
}
