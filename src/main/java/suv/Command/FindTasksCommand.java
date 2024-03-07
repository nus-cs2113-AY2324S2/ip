package suv.Command;

import suv.Helpers.Ui;

public class FindTasksCommand {
    final static String LINE = "____________________________________________________________\n";

    public FindTasksCommand(String input) throws SuvException {
        if(input.trim().length() > 4){
            String word = input.split(" ")[1];
            Ui.printMatchingTasks(word);
        } else {
            throw new SuvException(LINE +"Oh no! You are missing the keyword you want to search for!\n" + LINE);
        }

    }
}

