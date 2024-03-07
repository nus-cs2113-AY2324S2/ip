import drosstasks.DrossList;
import utilityclasses.*;

public class Dross {
    private static DrossList drossTaskList = new DrossList();
    private FileIO io;
    private Ui ui;
    private Parser parse;

    //Sets up objects, loads data from file and prints welcome msg
    private void start(){
        this.ui = new Ui();
        this.io = new FileIO();
        io.loadTasksFromFile(drossTaskList);
        ui.printWelcomeMessage();

    }

    //Prints goodbye message and exits
    private void exit(){
        ui.printGoodbyeMessage();
        System.exit(0);
    }

    //Starts receiving user input till bye command is given
    private void initiateReceiving(){
        parse = new Parser();
        parse.readParseExecuteUserInput(drossTaskList);
    }

    //Runs the program till termination
    public void run(){
        start();
        initiateReceiving();
        exit();
    }

    public static void main(String[] args) {
        new Dross().run();
    }
}
