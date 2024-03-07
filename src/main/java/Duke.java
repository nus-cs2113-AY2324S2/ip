import customexceptions.IncompletePromptException;
import customexceptions.UnknownPromptException;
import interactions.Parser;
import interactions.Storage;
import interactions.Ui;
import interactions.commands.Command;
import tasks.TaskList;
import interactions.commands.AddCommand;

import java.io.IOException;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " _____ ______   ________  ________      ___    ___\n" +
        "|\\   _ \\  _   \\|\\   __  \\|\\   __  \\    |\\  \\  /  /|\n" +
        "\\ \\  \\\\\\__\\ \\  \\ \\  \\ \\  \\ \\  \\_\\ /_   \\ \\  \\/  / /\n" + 
        " \\ \\  \\\\|__| \\  \\ \\  \\ \\  \\ \\   __  \\   \\ \\    / /\n" + 
        "  \\ \\  \\    \\ \\  \\ \\  \\_\\  \\ \\  \\_\\  \\   \\/   / /\n" + 
        "   \\ \\__\\    \\ \\__\\ \\_______\\ \\_______\\__/   / /\n" + 
        "    \\|__|     \\|__|\\|_______|\\|_______|\\____/ /\n" +
        "                                      \\|____|/\n";
        System.out.print("Hello from\n" + logo);
        Ui ui = new Ui();
        TaskList list = new TaskList();
        Storage storageHandler = new Storage();
        ui.greet();
        String line;
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.print("YOU: ");
            line = in.nextLine();
            System.out.print(ui.getName() + ": ");
            if (line.equals("bye")) {
                break;
            }
            if (line.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                list.printList();
            }
            //ui.interact(ui, list, line);
            try {
                Parser parser = new Parser(ui, list);
                Command c = parser.parse(line);
                if (c != null) {
                    c.execute(list, storageHandler);
                }
                //parser.handleCommand(line);
            } catch (IncompletePromptException e) {
                if (!line.equals("list")) {
                    System.out.println("Sorry, your sentence appears to be incomplete. Could you complete your sentence?");
                }
            } catch (UnknownPromptException e) {
                System.out.println("Sorry, I do not understand what you're saying.");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Sorry, the number you inputted exceeded the file size.");
            }

            try {
                storageHandler.saveToFile("./data/list.txt", list);
            } catch (IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
        }
        ui.exit();
    }
}
