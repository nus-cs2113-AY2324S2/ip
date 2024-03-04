import Exceptions.TaskNotFoundException;
import Tasks.Task;
import Tasks.Todo;
import Exceptions.ArgumentNotFoundException;
import Exceptions.CommandNotFoundException;


import java.util.ArrayList;
import java.io.IOException;
import java.util.Scanner;

public class Spike {
    private static Ui ui;

    public static void main(String[] args) throws IOException {
        ui = new Ui();

        ui.displayWelcomeMsg();
        ArrayList<Task> inputList = DataHandler.readFileContents(DataHandler.FILE_PATH);
        Scanner in = new Scanner(System.in);

        while (true) {
            try {
                startChatbot(in, inputList);
                ui.displayByeMsg();
                break;
            } catch (CommandNotFoundException e) {
                System.out.println("Command Not Found! Please Try Again");
            } catch (ArgumentNotFoundException e) {
                System.out.println("Argument Not Found! Please Try Again");
            } catch (TaskNotFoundException e) {
                System.out.println("Task Not Found! Please Try Again");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


    private static void startChatbot(Scanner in, ArrayList<Task> inputList)
            throws CommandNotFoundException, ArgumentNotFoundException, TaskNotFoundException, IOException {
        outerLoop:
        while (true) {
            String input = in.nextLine();
            switch (input.split(" ")[0]) {
            case "list":
                ui.displayList(inputList);
                break;
            case "mark":
                int indexMark = Parser.getIndexMark(inputList, input);
                inputList.get(indexMark).setDone(true);
                ui.displayMarkMsg(indexMark, inputList);
                break;
            case "unmark":
                int indexUnmark = Parser.getIndexUnmark(inputList, input);
                inputList.get(indexUnmark).setDone(false);
                ui.displayUnmarkMsg(indexUnmark, inputList);
                break;
            case "todo":
                Task newTodo = new Todo(Parser.processTodo(input));
                inputList.add(newTodo);
                ui.displayAcknowledgement(newTodo, inputList.size());
                break;
            case "deadline":
                Task newDeadline = new Todo(Parser.processDeadline(input));
                inputList.add(newDeadline);
                ui.displayAcknowledgement(newDeadline, inputList.size());
                break;
            case "event":
                Task newEvent = new Todo(Parser.processEvent(input));
                inputList.add(newEvent);
                ui.displayAcknowledgement(newEvent, inputList.size());
                break;
            case "delete":
                int indexDelete = Parser.getIndexDelete(input);
                ui.displayDeleteMsg(inputList.get(indexDelete), inputList.size());
                inputList.remove(indexDelete);
                break;
            case "bye":
                break outerLoop;
            default:
                throw new CommandNotFoundException();
            }
            DataHandler.tasksToFile(inputList);
        }
    }
}
