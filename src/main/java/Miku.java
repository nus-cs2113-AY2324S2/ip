import java.io.FileNotFoundException;

public class Miku {

    public static void main(String[] args) {
        try {
            TaskList.setTaskList(Storage.loadData());
        } catch (FileNotFoundException e) {
            TaskList.setTaskList();
        }

        Ui.greetings();
        Parser.decideAction();
        Ui.goodbye();
    }
}