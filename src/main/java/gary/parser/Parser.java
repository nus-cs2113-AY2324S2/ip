package gary.parser;

import gary.command.Command;
import gary.command.AddCommand;
import gary.command.DeleteCommand;
import gary.command.ExitCommand;
import gary.command.FindCommand;
import gary.command.ListCommand;
import gary.command.MarkCommand;
import gary.command.UnmarkCommand;

import gary.task.Task;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Parser {
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
            } else if (command.equalsIgnoreCase("FIND")) {
                userCommand = new FindCommand(line, todos);
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
