package kapwa;

import exception.KapwaException;

public class Kapwa{
    private static final Ui ui = new Ui();
    private static final TaskManager taskManager = new TaskManager();

    public static void main(String[] args) {
        ui.showWelcome();
        String inputLine;

        while (true) {
            inputLine = ui.readCommand();
            try {
                ui.showLine();
                if ("bye".equals(inputLine)) {
                    break;
                } else if ("list".equals(inputLine)) {
                    taskManager.displayTaskList();
                } else if (inputLine.startsWith("mark ")) {
                    int taskNumber = Integer.parseInt(inputLine.replaceAll("\\D+", ""));
                    taskManager.markTask(taskNumber, true);
                } else if (inputLine.startsWith("unmark ")) {
                    int taskNumber = Integer.parseInt(inputLine.replaceAll("\\D+", ""));
                    taskManager.markTask(taskNumber, false);
                } else if (inputLine.startsWith("delete ")) {
                    int taskNumber = Integer.parseInt(inputLine.replaceAll("\\D+", ""));
                    taskManager.deleteTask(taskNumber);
                } else {
                    taskManager.addTask(inputLine);
                }
            } catch (KapwaException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }

        ui.showGoodByeMessage();
        ui.closeScanner();
    }
}
