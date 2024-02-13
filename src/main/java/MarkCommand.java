public class MarkCommand {

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public static void execute(String command, TaskList taskList, boolean markAsDone) {
        String[] parts = command.split(" ");
        if (parts.length < 2 || !isNumeric(parts[1])) {
            System.out.println("Please provide a valid task number.");
        }
        int taskNumber = Integer.parseInt(parts[1]);

        if (markAsDone) {
            taskList.markTaskAsDone(taskNumber);
        } else {
            taskList.markTaskAsNotDone(taskNumber);
        }
    }
}
