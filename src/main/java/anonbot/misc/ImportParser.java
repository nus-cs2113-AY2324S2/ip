package anonbot.misc;


import anonbot.exception.ImportDataException;

public class ImportParser {

    public static String[] convertToParsableTask(String rawData) throws ImportDataException {
        // Format: { "command", "is_task_done", "task_number", "arguments"}
        String[] splitStrings = rawData.split(" ", 4);
        if (splitStrings.length != 4) {
            throw new ImportDataException("Task Information is corrupt");
        }
        return splitStrings;
    }
}
