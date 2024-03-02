package chatbot.commands;

import chatbot.ChatbotException;
import chatbot.tasks.TaskList;
import chatbot.ui.UI;

import java.util.Scanner;

public class FindCommand implements Command{
    private final TaskList taskList;
    private final String keyWords;
    public FindCommand(TaskList taskList, String keyWords) throws ChatbotException {
        if (keyWords.isEmpty()) {
            throw new ChatbotException("What should I look for? \n" +
                    "------------------------------");
        }
        this.taskList = taskList;
        this.keyWords = keyWords;
    }

    @Override
    public void execute(boolean isReading) throws ChatbotException {
        UI.printFindReply();
        taskList.find(keyWords);
        UI.printSeparator();
    }
}
