import task.Task;

import java.util.ArrayList;

public class DukeException extends Exception {

    /**
     * Exception for mark and unmark functions
     * Check if task index is out of bounds
     *
     * @param userInput Text inputted by use in the bot
     * @param tasks list of tasks
     * @return returns the index of current task
     * @throws DukeException if index is out of bounds
     * @throws ArrayIndexOutOfBoundsException if no index is specified
     */
    public static int getTaskIndex(String userInput, ArrayList<Task> tasks) throws DukeException {
        try {
            int taskIndex = Integer.parseInt(userInput) - 1;
            if (taskIndex < 0 || taskIndex >= tasks.size()) {
                System.out.println("OOPS!!! Please enter a valid task number.");
                throw new DukeException();
            }
            return taskIndex;

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("OOPS!!! Invalid input.");
            throw new DukeException();
        }
    }

    /**
     * Exception for todo, deadline and event functions
     * Check if user input is in correct format
     *
     * @param line line of text inputted by use in the bot
     * @throws DukeException if user input is in the wrong format
     */
    public static void checkDescription (String line) throws DukeException {
        String[] userInput = line.split(" ");
        if (userInput.length == 1) {
            System.out.println("OOPS!!! No task description");
            throw new DukeException();
        }
    }

    /**
     * Exception for find function
     * Check if keyword for find is included
     *
     * @param line line of text inputted by use in the bot
     * @throws DukeException if no keyword is included
     */
    public static void checkKeyword (String line) throws DukeException {
        String[] userInput = line.split(" ");
        if (userInput.length == 1) {
            System.out.println("OOPS!!! No keyword included");
            throw new DukeException();
        }
    }
}
