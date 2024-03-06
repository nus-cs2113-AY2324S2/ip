import helperFunctions.TaskList;
import helperFunctions.Ui;

public class RecrBad { //TODO more OOP
    private TaskList tasks;
    public RecrBad(String FILE_PATH) {
        tasks = new TaskList();
        Ui.sayHi();
        Ui.readInput(tasks, FILE_PATH);
    }

    /**
     * Input
     * [any text]           to addToList,
     * list                 to displayList,
     * mark/unmark [index]  to mark item
     */
    public static void main(String[] args) {
        String FILE_PATH = "saveFile.txt";
        new RecrBad(FILE_PATH);
    }
}

