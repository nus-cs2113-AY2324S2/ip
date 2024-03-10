public class Parser {

    private Ui ui;
    private Storage storage;
    private static final int DELSTART = 7;
    private static final int UNMARKSTART = 7;
    private static final int MARKSTART = 5;
    private static final int FINDSTART = 5;

    public Parser(Ui ui_, Storage storage_) {
        this.ui = ui_;
        this.storage = storage_;
    }

    public void parse(String prompt) {
        if (prompt.equals("bye")) {
            ui.terminate();
            storage.save();
            Ui.printThis("Bye. Hope to see you again soon! Mr. Tickles will miss you.");
        } else if (prompt.equals("list")) {
            TaskList.printList("Here are the tasks in your list:", TaskList.list);

        } else if (prompt.equals("mark") || prompt.equals("unmark") || prompt.equals("delete")) {
            Ui.printThis("Please specify which task number this action refers to.");
        } else if (prompt.startsWith("delete")) {
            TaskList.modifyTask(TaskList.action.DELETE, prompt.substring(DELSTART));
        } else if (prompt.startsWith("unmark")) {
            TaskList.modifyTask(TaskList.action.UNMARK, prompt.substring(UNMARKSTART));
        } else if (prompt.startsWith("mark")) {
            TaskList.modifyTask(TaskList.action.MARK, prompt.substring(MARKSTART));
        } else if (prompt.startsWith("find")) {
            TaskList.find(prompt.substring(FINDSTART));
        } else {
            TaskList.addToList(prompt);
        }
    }
}
