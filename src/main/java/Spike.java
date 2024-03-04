import Exceptions.TaskNotFoundException;
import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.Todo;
import Exceptions.ArgumentNotFoundException;
import Exceptions.CommandNotFoundException;


import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Spike {
    public static void main(String[] args) throws IOException {
        Ui.displayWelcomeMsg();
        ArrayList<Task> inputList = DataHandler.readFileContents(DataHandler.FILE_PATH);
        Scanner in = new Scanner(System.in);

        while (true) {
            try {
                startChatbot(in, inputList);
                Ui.displayByeMsg();
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
                Ui.displayList(inputList);
                break;
            case "mark":
                int indexMark = parser.getIndexMark(inputList, input);
                inputList.get(indexMark).setDone(true);
                Ui.displayMarkMsg(indexMark, inputList);
                break;
            case "unmark":
                int indexUnmark = parser.getIndexUnmark(inputList, input);
                inputList.get(indexUnmark).setDone(false);
                Ui.displayUnmarkMsg(indexUnmark, inputList);
                break;
            case "todo":
                Task newTodo = new Todo(parser.processTodo(input));
                inputList.add(newTodo);
                Ui.displayAcknowledgement(newTodo, inputList.size());
                break;
            case "deadline":
                Task newDeadline = new Todo(parser.processDeadline(input));
                inputList.add(newDeadline);
                Ui.displayAcknowledgement(newDeadline, inputList.size());
                break;
            case "event":
                Task newEvent = new Todo(parser.processEvent(input));
                inputList.add(newEvent);
                Ui.displayAcknowledgement(newEvent, inputList.size());
                break;
            case "delete":
                int indexDelete = parser.getIndexDelete(input);
                Ui.displayDeleteMsg(inputList.get(indexDelete), inputList.size());
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
