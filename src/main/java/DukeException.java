import task.Task;

import java.util.ArrayList;

public class DukeException extends Exception {

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

    public static void checkDescription (String line) throws DukeException {
        String[] userInput = line.split(" ");
        if (userInput.length == 1) {
            System.out.println("OOPS!!! No task description");
            throw new DukeException();
        }
    }

    public static void checkKeyword (String line) throws DukeException {
        String[] userInput = line.split(" ");
        if (userInput.length == 1) {
            System.out.println("OOPS!!! No keyword included");
            throw new DukeException();
        }
    }
}
