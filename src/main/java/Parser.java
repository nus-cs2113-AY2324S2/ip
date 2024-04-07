import java.util.Arrays;

/**
 * Deals with making sense of the user command.
 */
public class Parser {

    /**
     * Parses user input for mark, unmark, and delete commands.
     *
     * @param arr the input array
     * @param numTasks the total number of tasks
     * @return the index of the task to mark, unmark, or delete
     * @throws IllegalArgumentException if the input is invalid
     */
    public static int markDeleteParse(String[] arr, int numTasks) throws IllegalArgumentException {
        if(arr.length <= 1 || arr.length > 2) {
            throw new IllegalArgumentException();
        }
        int markNum;
        try {
            markNum = Integer.parseInt(arr[1]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
        if (markNum >= 1 && markNum <= numTasks) {
            return markNum-1; //returns the index for marking and deleting
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Parses user input for todo command
     *
     * @param arr the input array
     * @return the Todo object
     * @throws IllegalArgumentException if the input is invalid
     */
    public static Todo todoParse(String[] arr) throws IllegalArgumentException {
        if (arr.length <= 1) {
            throw new IllegalArgumentException();
        } else {
            String desc = String.join(" ", Arrays.copyOfRange(arr, 1, arr.length));
            return new Todo(desc);
        }
    }

    /**
     * Parses user input for deadline command
     *
     * @param arr the input array
     * @return the Deadline object
     * @throws IllegalArgumentException if the input is invalid
     */
    public static Deadline deadlineParse(String[] arr) throws IllegalArgumentException {
        if (arr.length <= 1) {
            throw new IllegalArgumentException();
        }
        String fullInput = String.join(" ", arr);
        if(!fullInput.contains("/by")) {
            throw new IllegalArgumentException();
        }
        String desc = fullInput.substring(9);
        String[] descArr = desc.split(" /by ");
        return new Deadline(descArr[0], descArr[1]);
    }

    /**
     * Parses user input for event command
     *
     * @param arr the input array
     * @return the Event object
     * @throws IllegalArgumentException if the input is invalid
     */
    public static Event eventParse(String[] arr) throws IllegalArgumentException {
        if (arr.length <= 1) {
            throw new IllegalArgumentException();
        }
        String fullInput = String.join(" ", arr);
        String desc = fullInput.substring(6);
        String[] descArr = desc.split(" /");
        if (descArr.length != 3) {
            throw new IllegalArgumentException();
        }
        if((!descArr[1].contains("from")) || (!descArr[2].contains("to"))) {
            throw new IllegalArgumentException();
        }
        String from = descArr[1].substring(5);
        String to = descArr[2].substring(3);
        return new Event(descArr[0], from, to);
    }

    /**
     * Parses user input for find command
     *
     * @param arr the input array
     * @return the String that will be searched
     * @throws IllegalArgumentException if the input is invalid
     */
    public static String findParse(String[] arr) throws IllegalArgumentException {
        if (arr.length <= 1) {
            throw new IllegalArgumentException();
        }
        String desc = String.join(" ", Arrays.copyOfRange(arr, 1, arr.length));
        return desc;
    }


}
