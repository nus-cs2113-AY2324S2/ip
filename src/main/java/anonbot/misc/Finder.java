package anonbot.misc;

import anonbot.exception.EmptyArgumentException;
import anonbot.task.TaskManager;

/**
 * Provides searching functionality to identify matching tasks.
 */
public class Finder {
    /**
     * Finds all available tasks that contains the specified keyword or keyphrase.
     *
     * @param command The command to be processed. Currently supports only the "find" command.
     * @param keyphrase The keyword or keyphrase to search through the task.
     * @throws EmptyArgumentException If the keyword is not specified (empty).
     */
    public static void findTaskUsingKeyphrase(String command, String keyphrase) throws EmptyArgumentException {
        if (keyphrase.isEmpty()) {
            throw new EmptyArgumentException(command);
        }
        TaskManager.printTasksUsingKeyphrase(keyphrase);
    }
}
