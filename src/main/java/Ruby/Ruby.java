package Ruby; /**
 * Main class for the chatbot named Ruby.
 * Ruby assists users in recording and organizing their daily tasks.
 * It interacts with users through the command line, processing commands to manage tasks.
 */
import Ui.Ui;
import Storage.Storage;
import Parser.Parser;

public class Ruby {
    private static final Storage s1 = new Storage();
    private static final TaskList t1 = new TaskList();
    private static final Ui u1 = new Ui();
    public static final Parser p1 = new Parser();

    public static void main(String[] args) {
        u1.printGreetMessage();
        run();
        u1.printByeMessage();
    }

    /**
     * Main processing loop for user commands.
     * Continuously captures and processes user input until the "bye" command is issued.
     */
    private static void run(){
        t1.setTaskList(s1.loadFile());
        String[] userInputs = p1.inputParser(u1.readUserInput());

        while (p1.isTerminateCommand()) {
            switch (userInputs[0].toLowerCase()){
            case "list":
                t1.showTaskList();
                break;
            case "mark":
                p1.parseMarkTask(t1);
                break;
            case "unmark":
                p1.parseUnmarkTask(t1);
                break;
            case "delete":
                p1.parseDeleteTask(t1);
                break;
            case "todo":
            case "deadline":
            case "event":
                p1.parseAddTask(t1);
                break;
            case "find":
                p1.parseFindTask(t1);
                break;
            default:
                p1.parseDefault();
                break;
            }
            s1.saveToFile(t1.getTaskList());
            userInputs = p1.inputParser(u1.readUserInput());
        }
    }
}
