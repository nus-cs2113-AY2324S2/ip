import java.nio.InvalidMarkException;

public class MarkTask {
    public static int findIndexToMark(String[] userInputWords, Task[] tasks) throws
            DukeExceptions.InvalidMarkException, ArrayIndexOutOfBoundsException {
        try {
            int testIndex = Integer.parseInt(userInputWords[1]);
        } catch(Exception e) {
            DukeExceptions.throwInvalidMarkException();
        }
        int indexFound = Integer.parseInt(userInputWords[1]);
        if (indexFound > tasks.length || indexFound < 1) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return indexFound;
    }

    public static void mark(String[] userInputWords, Task[] tasks) {
        int indexToMark = -1;
        String isMarked = userInputWords[0];
        try {
            indexToMark = findIndexToMark(userInputWords, tasks);
        } catch(ArrayIndexOutOfBoundsException e) {
            PrintText.printWithLinebreak("Index out of range");
            return;
        } catch(DukeExceptions.InvalidMarkException e) {
            PrintText.printWithLinebreak("Please enter a valid item to mark/unmark.");
            return;
        }

        int indexInList = indexToMark - 1;
        if (isMarked.equals("mark")) {
            tasks[indexInList].isDone = true;
            String statusMark = "[" + tasks[indexInList].getStatusIcon() + "] ";
            PrintText.printWithLinebreak("Nice! I've marked this task as done:\n" +
                    statusMark + tasks[indexInList].description);
        } else if (isMarked.equals("unmark")) {
            tasks[indexInList].isDone = false;
            String statusMark = "[" + tasks[indexInList].getStatusIcon() + "] ";
            PrintText.printWithLinebreak("OK, I've marked this task as not done yet:\n" +
                    statusMark + tasks[indexInList].description);
        } else {
            PrintText.printWithLinebreak("unknown instruction");
        }
    }
}
