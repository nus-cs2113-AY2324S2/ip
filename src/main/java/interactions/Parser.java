package interactions;

import java.util.Arrays;

import customexceptions.IncompletePromptException;
import customexceptions.UnknownPromptException;

public class Parser {
    Ui ui;
    TaskList list;
    public Parser(Ui ui, TaskList list) {
        this.ui = ui;
        this.list = list;
    }
    public void handleCommand(String line) throws IncompletePromptException, UnknownPromptException {
        String[] words = line.split(" ");
        String keyword = words[0];
        String[] botInstructions = Ui.INSTRUCTIONS;
        boolean isTypo = ui.isTypo(keyword);
        if (words.length == 1) {
            if (Arrays.binarySearch(botInstructions, keyword) >= 0) {
                throw new IncompletePromptException();
            }
            if (!isTypo) {
                throw new UnknownPromptException();
            }
        } else {
            switch (keyword) {
            case "echo":
                ui.echo(line);
                break;
            case "rename":
                ui.rename(line);
                break;
            case "mark":
                list.mark(line, true);
                break;
            case "list":
                list.printList();
                break;
            case "unmark":
                list.mark(line, false);
                break;
            case "todo":
                list.addNewTask(line, "todo");
                break;
            case "deadline":
                list.addNewTask(line, "deadline");
                break;
            case "event":
                list.addNewTask(line, "event");
                break;
            case "delete":
                list.deleteTask(ui.parseIndex(line, keyword));
                break;
            default:
                if (!isTypo) {
                    throw new UnknownPromptException();
                }
            }
        }
    }
}
