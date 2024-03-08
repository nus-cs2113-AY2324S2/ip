
public class Eln {

    public static void main(String[] args) {
        UI.greeting();
        Storage.loadFile();
        Parser.taskManager(); // taskManager ends when user inputs "bye"
        UI.farewell();
    }
}
