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
            catch(NullPointerException e) {
            }
            catch(ArrayIndexOutOfBoundsException e) {
                Printer.printMarkCommand();
            }
            return;
        case "unmark":
            try {
                TaskHandler.taskList.get(Integer.parseInt(commands[1]) - 1).markAsUndoneWithoutPrints();
            }
            catch(NullPointerException e) {
                System.out.println(commands[1] + " is not in your list!");
            }
            catch(ArrayIndexOutOfBoundsException e) {
                Printer.printUnmarkCommand();
            }
            return;
        }
        try {
            TaskHandler.addToListWithoutPrints(commands);
        }
        catch(InvalidCommandException e) {
        }
        catch(ArrayIndexOutOfBoundsException e) {
            System.out.println("Type [help] to learn how to add tasks");
        }
        catch(StringIndexOutOfBoundsException e) {
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
                jigaChat.previousData.appendToFile(input + "\n");
                TaskHandler.removeFromList(Integer.parseInt(commands[1]) - 1);
            }
            catch(NumberFormatException e) {
                Printer.printDeleteCommand();
            }
            catch(ArrayIndexOutOfBoundsException e) {
                Printer.printDeleteCommand();
            }
            catch(IOException e){
            }
            return;
        case "list":
            Printer.printList();
            return;
        case "mark":
            try {
                TaskHandler.taskList.get(Integer.parseInt(commands[1]) - 1).markAsDone();
                System.out.println("JigaChat has marked task " + (Integer.parseInt(commands[1])) + " as done!");
                jigaChat.previousData.appendToFile(input + "\n");
            }
            catch(NullPointerException e) {
                System.out.println(commands[1] + " is not in your list!");
            }
            catch(ArrayIndexOutOfBoundsException e) {
                Printer.printMarkCommand();
            }
            catch(IOException e) {
            }
            return;
        case "unmark":
            try {
                TaskHandler.taskList.get(Integer.parseInt(commands[1]) - 1).markAsUndone();
                System.out.println("JigaChat has marked task " + (Integer.parseInt(commands[1])) + " as not done!");
                jigaChat.previousData.appendToFile(input + "\n");
            }
            catch(NullPointerException e) {
                System.out.println(commands[1] + " is not in your list!");
            }
            catch(ArrayIndexOutOfBoundsException e) {
                Printer.printUnmarkCommand();
            }
            catch(IOException e) {
            }
            return;
        }
        try {
            TaskHandler.addToList(commands);
            jigaChat.previousData.appendToFile(input + "\n");
        }
        catch(InvalidCommandException e) {
            System.out.println("Command " + input + " is not recognised!");
        }
        catch(ArrayIndexOutOfBoundsException e) {
            System.out.println("Type [help] to learn how to add tasks");
        }
        catch(StringIndexOutOfBoundsException e) {
            System.out.println("Type [help] to learn how to add tasks");
        }
        catch(IOException e) {
        }
    }
}
