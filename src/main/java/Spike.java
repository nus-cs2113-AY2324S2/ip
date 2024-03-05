import Exceptions.TaskNotFoundException;
import Exceptions.ArgumentNotFoundException;
import Exceptions.CommandNotFoundException;
import Tasks.Task;


import java.io.IOException;
import java.util.Scanner;

public class Spike {
    private static Ui ui;
    private static TaskList Tasks;

    public static void main(String[] args) {
        ui = new Ui();

        ui.displayWelcomeMsg();
        Scanner in = new Scanner(System.in);

        while (true) {
            try {
                startChatbot(in);
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


    private static void startChatbot(Scanner in)
            throws CommandNotFoundException, ArgumentNotFoundException, TaskNotFoundException, IOException {
        Tasks = new TaskList();
        outerLoop:
        while (true) {
            String input = in.nextLine();
            switch (input.split(" ")[0]) {
            case "list":
                Tasks.printList();
                break;
            case "find":
                Tasks.printMatchingList(input);
                break;
            case "mark":
                Tasks.processMark(input);
                break;
            case "unmark":
                Tasks.processUnmark(input);
                break;
            case "todo":
                Tasks.processTodo(input);
                break;
            case "deadline":
                Tasks.processDeadline(input);
                break;
            case "event":
                Tasks.processEvent(input);
                break;
            case "delete":
                Tasks.processDelete(input);
                break;
            case "bye":
                break outerLoop;
            default:
                throw new CommandNotFoundException();
            }
            Tasks.updateTaskList();
        }
    }
}
