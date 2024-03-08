package interactions;

import java.util.Arrays;
import customexceptions.IncompletePromptException;
import customexceptions.UnknownPromptException;
import interactions.commands.*;
import tasks.TaskList;

import static interactions.Ui.INSTRUCTIONS;

/**
 * Parses the inputted prompt which is a String and assigns the appropriate actions and commands.
 */
public class Parser {
    Ui ui;
    TaskList list;
    public Parser(Ui ui, TaskList list) {
        this.ui = ui;
        this.list = list;
    }

    /**
     * Returns {@code Command} after parsing the appropriate first word and description.
     *
     * @param line Inputted user prompt to parse.
     * @return Command corresponding to parsed input line.
     * @throws IncompletePromptException If there is missing description or information from input.
     * @throws UnknownPromptException If user command does not match any of the commands, and is not a typo.
     */
    public Command parse(String line) throws IncompletePromptException, UnknownPromptException {
        Command command;
        String[] words = line.split(" ");
        String firstWord = words[0];
        String taskDescription;
        boolean isTypo = ui.isTypo(firstWord);
        if (words.length == 1) {
            if (Arrays.binarySearch(INSTRUCTIONS, firstWord) >= 0) {
                throw new IncompletePromptException(true);
            }
            if (!isTypo) {
                throw new UnknownPromptException();
            }
        }
        if (line.length() > firstWord.length()) {
            taskDescription = line.substring(firstWord.length() + 1);
        } else {
            throw new IncompletePromptException(true);
        }

        switch (firstWord) {
        case "todo":
        case "deadline":
        case "event":
            command = new AddCommand();
            break;
        case "delete":
            command = new DeleteCommand();
            break;
        case "mark":
        case "unmark":
            command = new MarkCommand();
            break;
        case "echo":
            command = new EchoCommand();
            break;
        case "rename":
            command = new RenameCommand();
            break;
        case "find":
            command = new FindCommand();
            break;
        default:
            return null;
        }
        command.setFirstWord(firstWord);
        command.setLine(line);
        command.setTaskDescription(taskDescription);
        return command;
    }
}