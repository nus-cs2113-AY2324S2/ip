import java.io.IOException;

public class Parser {
    protected static void readCommandWithoutPrints(String input) {
        String[] commands = new String[2];
        commands = input.split(" ", 2);

        switch(commands[0]) {
        case "help":
            Printer.printCommandList();
            return;
        case "bye":
            System.out.println("Bye. Hope to see you again soon!");
            System.exit(0);
        case "delete":
            TaskHandler.removeFromListWithoutPrints(Integer.parseInt(commands[1]) - 1);
            return;
        case "list":
            Printer.printList();
            return;
        case "mark":
            try {
                TaskHandler.taskList.get(Integer.parseInt(commands[1]) - 1).markAsDoneWithoutPrints();
            }
            catch (NullPointerException e) {
            }
            catch (ArrayIndexOutOfBoundsException e) {
                Printer.printMarkCommand();
            }
            return;
        case "unmark":
            try {
                TaskHandler.taskList.get(Integer.parseInt(commands[1]) - 1).markAsUndoneWithoutPrints();
            }
            catch (NullPointerException e) {
                System.out.println(commands[1] + " is not in your list!");
            }
            catch (ArrayIndexOutOfBoundsException e) {
                Printer.printUnmarkCommand();
            }
            return;
        }
        try {
            TaskHandler.addToListWithoutPrints(commands);
        }
        catch(InvalidCommandException e) {
        }
        catch(ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e) {
            System.out.println("Type [help] to learn how to add tasks");
        }
    }

    protected static void readCommand(String input, JigaChat jigaChat) {
        String[] commands = new String[2];
        commands = input.split(" ", 2);

        switch(commands[0]) {
        case "help":
            Printer.printCommandList();
            return;
        case "bye":
            System.out.println("Bye. Hope to see you again soon!");
            System.exit(0);
        case "delete":
            try {
                JigaChat.previousData.appendToFile(input + "\n");
                TaskHandler.removeFromList(Integer.parseInt(commands[1]) - 1);
            }
            catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                Printer.printDeleteCommand();
            }
            catch (IOException e) {
            }
            return;
        case "list":
            Printer.printList();
            return;
        case "mark":
            try {
                TaskHandler.taskList.get(Integer.parseInt(commands[1]) - 1).markAsDone();
                System.out.println("JigaChat has marked task " + (Integer.parseInt(commands[1])) + " as done!");
                JigaChat.previousData.appendToFile(input + "\n");
            }
            catch (NullPointerException e) {
                System.out.println(commands[1] + " is not in your list!");
            }
            catch (ArrayIndexOutOfBoundsException e) {
                Printer.printMarkCommand();
            }
            catch (IOException e) {
            }
            return;
        case "unmark":
            try {
                TaskHandler.taskList.get(Integer.parseInt(commands[1]) - 1).markAsUndone();
                System.out.println("JigaChat has marked task " + (Integer.parseInt(commands[1])) + " as not done!");
                JigaChat.previousData.appendToFile(input + "\n");
            }
            catch (NullPointerException e) {
                System.out.println(commands[1] + " is not in your list!");
            }
            catch (ArrayIndexOutOfBoundsException e) {
                Printer.printUnmarkCommand();
            }
            catch (IOException e) {
            }
            return;
        case "find":
            try {
                TaskHandler.findInTaskList(commands[1]);
            }
            catch (ArrayIndexOutOfBoundsException e) {
                Printer.printFindCommand();
            }
            return;
        }
        try {
            TaskHandler.addToList(commands);
            JigaChat.previousData.appendToFile(input + "\n");
        }
        catch(InvalidCommandException e) {
            System.out.println("Command " + input + " is not recognised!");
        }
        catch(ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e) {
            System.out.println("Type [help] to learn how to add tasks");
        }
        catch(IOException e) {
        }
    }
}
