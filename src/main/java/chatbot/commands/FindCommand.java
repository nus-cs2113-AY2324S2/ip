package chatbot.commands;

import chatbot.ChatbotException;
import chatbot.tasks.TaskList;
import chatbot.ui.UI;

/**
 * Represents a command to find a task/s.
 */
public class FindCommand implements Command{
    private final TaskList taskList;
    private final String keyWords;

    /**
     * Constructs a FindCommand object.
     *
     * @param taskList The list of tasks.
     * @param keyWords The keywords to look for.
     */
    public FindCommand(TaskList taskList, String keyWords) throws ChatbotException {
        if (keyWords.isEmpty()) {
            throw new ChatbotException("What should I look for? \n" +
                    "------------------------------");
        }
        this.taskList = taskList;
        this.keyWords = keyWords;
    }

    /**
     * Executes the command to find a task/s.
     *
     * @param isReading If the chatbot is reading a file, during which it will not display confirmation text.
     */
    @Override
    public void execute(boolean isReading) {
        UI.printFindReply();
        taskList.find(keyWords);
        UI.printSeparator();
    }
}
