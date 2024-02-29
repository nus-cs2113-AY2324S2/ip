package gary.parser;

import gary.task.Task;
import gary.command.Command;
import gary.command.ListCommand;
import gary.command.MarkCommand;
import gary.command.UnmarkCommand;
import gary.command.AddCommand;
import gary.command.DeleteCommand;
import gary.command.ExitCommand;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Parser class deals with making sense of user commands, such that the programme can
 * process the command and output accordingly.
 */
public class Parser {
    /**
     * Takes in users command in a loop until the users choose to exit the programme.
     * Users' input is processed according to what command is given.
     *
     * @param line user input to the terminal.
     * @param file txt file to store the tasks.
     * @param in terminal scanner to take in user input.
     * @param todos array list that stores and manages the task while programme is running.
     */
    public static void runCommand(String line, File file, Scanner in, ArrayList<Task> todos) {
        String[] lineWords;
        String command;
        Command userCommand;

        while (!(line.toUpperCase().contains("BYE"))) {
            lineWords = line.split(" ");
            command = lineWords[0];
            if (line.equalsIgnoreCase("LIST")) {
                userCommand = new ListCommand(todos);
            } else if (command.equalsIgnoreCase("MARK")) {
                userCommand = new MarkCommand(file, todos, lineWords);
            } else if (command.equalsIgnoreCase("UNMARK")) {
                userCommand = new UnmarkCommand(file, todos, lineWords);
            } else if (command.equalsIgnoreCase("DELETE")) {
                userCommand = new DeleteCommand(file, todos, lineWords);
            } else {
                userCommand = new AddCommand(line, file, todos, command);
            }
            userCommand.handleCommand();
            line = in.nextLine();
        }

        userCommand = new ExitCommand();
        userCommand.handleCommand();
    }
}
