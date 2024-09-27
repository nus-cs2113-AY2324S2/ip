import helperFunctions.InvalidParamsException;
import helperFunctions.Storage;
import helperFunctions.TaskList;
import helperFunctions.Ui;

public class RecrBad {
    private TaskList tasks;

    /**
     * Constructor to start main program: sayHi() on UI, open and process FILE_NAME,
     * create FILE_NAME if does not exist, and asks User for input
     * 
     * @param FILE_NAME to store tasks in
     */
    public RecrBad(String FILE_NAME) {
        tasks = new TaskList();
        Ui.sayHi();

        try {
            Storage.readFile(FILE_NAME, tasks);
        } catch (InvalidParamsException e) {
            Ui.showLoadingError(e.getMessage());
            return;
        }
        Ui.readInput(tasks, FILE_NAME);
    }

    /**
     * Main programme
     *
     * @param args default arguments
     */
    public static void main(String[] args) {
        String FILE_NAME = "saveFile.txt";
        new RecrBad(FILE_NAME);
    }
}

