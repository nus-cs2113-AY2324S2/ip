package natsu.command;

import natsu.exception.InvalidCommandException;
import natsu.util.TaskList;

import static natsu.command.CommandConstants.FIND_COMMAND_LENGTH;
import static natsu.util.Ui.printLine;

public class FindCommand {
    public FindCommand(String userInput) throws InvalidCommandException {
        String keyword = userInput.substring(FIND_COMMAND_LENGTH).trim();
        if (keyword.trim().isEmpty()) {
            throw new InvalidCommandException("The description of a find command cannot be empty.");
        } else {
            printLine();
            System.out.println("     Here are the matching tasks in your list:");
            int matchCount = 0; // To keep track of the number of matching tasks
            for (int i = 0; i < TaskList.list.size(); i++) {
                if (TaskList.list.get(i).toString().contains(keyword)) {
                    System.out.println("     " + (matchCount + 1) + "." + TaskList.list.get(i).toString());
                    matchCount++;
                }
            }
            if (matchCount == 0) {
                System.out.println("     No tasks match your search keyword.");
            }
            printLine();
        }
    }
}