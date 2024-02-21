import java.nio.InvalidMarkException;
import java.util.ArrayList;

public class MarkTask {
    public static int findIndexToMark(String[] userInputWords, ArrayList<Task> tasks) throws
            DukeExceptions.InvalidItemException, ArrayIndexOutOfBoundsException {
        try {
            int testIndex = Integer.parseInt(userInputWords[1]);
        } catch(Exception e) {
            DukeExceptions.throwInvalidItemException();
        }
        int indexFound = Integer.parseInt(userInputWords[1]);
        if (indexFound > tasks.size() || indexFound < 1) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return indexFound;
    }

    public static void mark(String[] userInputWords, ArrayList<Task> tasks) {
        int indexToMark = -1;
        String isMarked = userInputWords[0];
        try {
            indexToMark = findIndexToMark(userInputWords, tasks);
        } catch(ArrayIndexOutOfBoundsException e) {
            PrintText.printWithLinebreak("Index out of range");
            return;
        } catch(DukeExceptions.InvalidItemException e) {
            PrintText.printWithLinebreak("Please enter a valid item to mark/unmark.");
            return;
        }

        int indexInList = indexToMark - 1;
        if (isMarked.equals("mark")) {
            tasks.get(indexInList).isDone = true;
            String statusMark = "[" + tasks.get(indexInList).getStatusIcon() + "] ";
            PrintText.printWithLinebreak("Nice! I've marked this task as done:\n" +
                    statusMark + tasks.get(indexInList).description);
        } else if (isMarked.equals("unmark")) {
            tasks.get(indexInList).isDone = false;
            String statusMark = "[" + tasks.get(indexInList).getStatusIcon() + "] ";
            PrintText.printWithLinebreak("OK, I've marked this task as not done yet:\n" +
                    statusMark + tasks.get(indexInList).description);
        } else {
            PrintText.printWithLinebreak("unknown instruction");
        }
    }
}
