import java.io.FileNotFoundException;

public class Miku {


    /**
     * Runs the chatbot
     */
    public static void run() {
        try {
            TaskList.setTaskList(Storage.loadData());
        } catch (FileNotFoundException e) {
            TaskList.setTaskList();
        }

        Ui.greetings();
        Parser.decideAction();
        Ui.goodbye();
    }

    public static void main(String[] args) {
       run();
    }
}