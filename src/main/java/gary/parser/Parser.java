package gary.parser;

import gary.task.Task;
import gary.command.Command;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Parser {
    public static void runCommandUntilExit(String line, File file, Scanner in, ArrayList<Task> todos) {
        String[] lineWords;
        String command;
        int todosCount = todos.size();
        while (!(line.toUpperCase().contains("BYE"))) {
            lineWords = line.split(" ");
            command = lineWords[0];
            if (line.equalsIgnoreCase("LIST")) {
                Command.runList(todos, todosCount);
            } else if (command.equalsIgnoreCase("MARK")) {
                Command.runMark(file, todos, lineWords, todosCount);
            } else if (command.equalsIgnoreCase("UNMARK")) {
                Command.runUnmark(file, todos, lineWords, todosCount);
            } else if (command.equalsIgnoreCase("DELETE")) {
                Command.runDelete(file, todos, lineWords, todosCount);
            } else {
                Command.runAdd(line, file, todos, command, todosCount);
            }
            todosCount = todos.size();
            line = in.nextLine();
        }
    }
}
