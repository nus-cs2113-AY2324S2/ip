/**
 * Chandler is a task manager that allows users to add, delete, and view tasks.
 * A <code>Chandler</code> object represents the task manager.
 */
public class Chandler {

    private TaskList taskList;
    private Storage storageManager;
    private Ui ui;
    private Parser inputParser;
    public static final String LINE_DIVIDER = "----------------------------------------------------";

    /**
     * Constructor for Chandler
     *
     * @param filePath The file path to the file from which to load the task list
     */
    public Chandler(String filePath) {
        taskList = new TaskList();
        storageManager = new Storage();
        inputParser = new Parser();
        ui = new Ui();

        try {
            storageManager.loadTaskListFromFile(taskList, filePath);
        } catch (ChandlerException e) {
            System.out.println("ChandlerException: " + e.getMessage());
            taskList = new TaskList();
        }
    }

    /**
     * Run the programme
     */
    public void run() {
        ui.printStartingMessage();
        String input = ui.getInput();
        System.out.println(LINE_DIVIDER);

        while(!input.equals("bye")) {
            try{
                inputParser.parseInput(input, taskList);
                System.out.println(LINE_DIVIDER);
            } catch (ChandlerException e) {
                System.out.println("ChandlerException: " + e.getMessage());
                System.out.println(LINE_DIVIDER);
            }
            input = ui.getInput();
            System.out.println(LINE_DIVIDER);
        }
        try {
            storageManager.saveTaskListToFile(taskList);
        } catch (ChandlerException e) {
            System.out.println("ChandlerException: " + e.getMessage());
        }
        ui.printEndingMessage();
    }

    /**
     * Main method
     *
     * @param args The command line arguments
     */
    public static void main(String[] args) {
        new Chandler("./data/chandler.txt").run();
    }
}
