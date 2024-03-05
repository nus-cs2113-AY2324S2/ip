package allez;
public class Allez {
    private static Storage storage;
    private static TaskList tasks;
    private static Ui ui;
    private static Parser parser;
    private static Command c;

    public enum TaskType {
        TODO, DEADLINE, EVENT
    }

//    public static void printList(ArrayList<Task> tasks){
//        int count = 0;
//
//        if(tasks.isEmpty()) {
//            System.out.println("List is currently empty");
//            return;
//        }
//
//        System.out.println("Here are the tasks in your list:");
//        for (Task task : tasks) {
//            System.out.println("\t" + (count+1) + ". " + task.toString());
//            count+=1;
//        }
//
//    }
    public static void main(String[] args) {
        startBot();
        runCommandsUntilExit();
        exit();
    }

    private static void exit() {
        storage.writeSave(tasks.getTasks());
        Ui.printExit();
    }

    private static void runCommandsUntilExit() {
        boolean hasEnded = false;
        while(!hasEnded) {
            String currentInput = ui.getUserCommand();
            String command =  parser.parseCommand(currentInput);
            hasEnded = executeCommands(command, currentInput);
        }
    }

    private static void startBot() {
        ui = new Ui();
        Ui.printGreeting();
        storage = new Storage("./data/data.txt");
        tasks = new TaskList(storage);
        parser = new Parser();
        c = new Command(tasks);
    }

    private static boolean executeCommands(String command, String line) {
        Ui.printHyphens();

        switch(command){
        case "bye":
            return true;
        case "mark":
            c.markTask(line);
            break;
        case "todo":
            c.createTask(line, TaskType.TODO);
            break;
        case "deadline":
            c.createTask(line, TaskType.DEADLINE);
            break;
        case "event":
            c.createTask(line, TaskType.EVENT);
            break;
        case "list":
            c.printList(tasks.getTasks());
            break;
        case "delete":
            c.deleteTask(line);
            break;
        default:
            System.out.println("Invalid command. Please try again.");
            break;
        }

        return false;
    }

}
