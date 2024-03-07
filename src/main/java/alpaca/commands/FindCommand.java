package alpaca.commands;

import alpaca.tasks.TaskList;
import alpaca.ui.Ui;


public class FindCommand extends AlpacaCommand{
    private String keyword;
    public FindCommand(String keyword, TaskList tasks) {
        super(tasks);
        this.keyword = keyword;
    }

    @Override
    public void execute(){
        Ui.printMatchingTaskList(keyword, tasks);
    }
}