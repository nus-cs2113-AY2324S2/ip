package Yoj.parser;

import Yoj.taskList.List;
import Yoj.exception.InvalidCommandException;
import Yoj.exception.YojException;
import Yoj.storage.Storage;
import Yoj.ui.Ui;

import java.io.IOException;

import static Yoj.taskList.List.tasks;

public class Parser {
    public static void manageUserInput(String userInput) throws YojException, InvalidCommandException, IOException {
        if (userInput.equals("list")) {
            Ui.printList();
        } else if(userInput.matches("mark \\d+")) {
            String[] taskIndex = userInput.split(" ");
            int index = Integer.parseInt(taskIndex[1] );
            tasks.get(index - 1).markDone();
            Ui.markDoneMessage(index);
        } else if(userInput.matches("unmark \\d+")) {
            String[] taskIndex = userInput.split(" ");
            int index = Integer.parseInt(taskIndex[1]);
            tasks.get(index - 1).markUndone();
            Ui.markUndoneMessage(index);
        } else if(userInput.startsWith("todo") || userInput.startsWith("deadline") || userInput.startsWith("event")) {
            List.addTask(userInput);
        } else if(userInput.startsWith("delete")) {
            List.deleteTask(userInput);
        } else if(userInput.startsWith("find")) {
            String description = userInput.substring("find ".length()).trim();
            List.findTask(description);
        } else if(userInput.equals("bye")){
            Ui.byeMessage();
            Storage.save(tasks);
        } else throw new InvalidCommandException("command not recognised :<");
    }
}
