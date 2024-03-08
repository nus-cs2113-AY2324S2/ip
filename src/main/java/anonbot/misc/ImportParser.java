package anonbot.misc;


import anonbot.exception.ImportDataException;

/**
 * Provides import functionality to covert saved data into workable tasks.
 */
public class ImportParser {
    /**
     * Breaks up the string entry representing the task into its respective components.
     * It is of the format { "command", "is_task_done", "task_number", "arguments"} to make it easier for conversion
     * into the corresponding task later on.
     *
     * @param rawData The string representation in the save file that represents the task.
     * @return A String array containing the command, status of task, task number, and arguments
     * @throws ImportDataException If the split up array does not have four components, likely due to corrupt data.
     */
    public static String[] convertToParsableTask(String rawData) throws ImportDataException {
        // Format: { "command", "is_task_done", "task_number", "arguments"}
        String[] splitStrings = rawData.split(" ", 4);
        if (splitStrings.length != 4) {
            throw new ImportDataException("Task Information is corrupt");
        }
        return splitStrings;
    }
}
