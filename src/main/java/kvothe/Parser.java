package kvothe;

import kvothe.exception.WrongArgumentsException;
import kvothe.command.*;
import kvothe.task.Deadline;
import kvothe.task.Event;

public class Parser {


    public static Command parse(String line) throws WrongArgumentsException {
        String[] words = line.split(" ");
        String command = words[0];

        String index="";
        String keyword="";

        try{
            index = words[1];
            keyword = words[1];
        }catch (ArrayIndexOutOfBoundsException e){
            //Do nothing
        }

        switch (command) {
            case "bye":
                return new ExitCommand();
            case "list":
                return new ListCommand();
            case "mark":
                return new MarkCommand(new String[]{index});
            case "unmark":
                return new UnmarkCommand(new String[]{index});
            case "delete":
                return new DeleteCommand(new String[]{index});
            case "find":
                return new FindCommand(new String[]{keyword});
            case "todo":
                return new AddCommand("todo", parseTodoArgs(line));
            case "deadline":
                return new AddCommand("deadline", parseTaskArgs(line, Deadline.args));
            case "event":
                return new AddCommand("event", parseTaskArgs(line, Event.args));
            default:
                throw new WrongArgumentsException("Invalid command");
        }
    }

    /**
     * Parses the line and returns the arguments to create a new task of type DEADLINE.
     * @param line the line to parse
     * @return the values for the arguments.
     * @throws WrongArgumentsException if the arguments in the input are invalid
     */
    private static String[] parseTaskArgs(String line, String[] validArgs) throws WrongArgumentsException {

        String args[] = line.split(" ");
        String newArgs[] = new String[validArgs.length + 1];
        String aux = "";

        int i;
        int indexOfNewArgs = 0;
        int indexOfValidArgs = 0;
        int lenOfArgGiven = 0;

        for (i = 1; i < args.length; i++) {
            String word = args[i];
            if (word.equals(validArgs[Math.min(indexOfValidArgs, validArgs.length - 1)])) {
                if (lenOfArgGiven == 0) {
                    String name = indexOfValidArgs > 0 ? validArgs[indexOfValidArgs - 1] : "description";
                    throw new WrongArgumentsException("No value for " + name);
                }

                newArgs[indexOfNewArgs++] = aux.trim();
                aux = "";
                indexOfValidArgs++;
                lenOfArgGiven = 0;
            } else if (word.startsWith("/")) {
                //We are forcing to receive the arguments in one order.
                //TODO: consider to allow the user to input the arguments in any order
                throw new WrongArgumentsException("Invalid argument " + word +
                        ". Expected " + validArgs[indexOfValidArgs]);
            } else {
                aux += word + " ";
                lenOfArgGiven++;
            }
        }

        newArgs[indexOfNewArgs] = aux.trim();

        if (lenOfArgGiven == 0) {
            String name = indexOfValidArgs > 0 ? validArgs[indexOfValidArgs - 1] : "description";
            throw new WrongArgumentsException("No value for " + name);
        }

        if (indexOfValidArgs != validArgs.length) {
            String message = "Missing arguments. Expected ";
            for (String arg : validArgs) {
                message += arg + " ";
            }
            throw new WrongArgumentsException(message);
        }

        return newArgs;
    }

    /**
     * Parses the line and returns the arguments to create a new task of type TODO.
     * @param line the line to parse
     * @return the values for the arguments.
     * @throws WrongArgumentsException if the arguments in the input are invalid
     */
    private static String[] parseTodoArgs(String line) throws WrongArgumentsException {

        String args[] = line.split(" ");
        String newArgs[] = new String[1];
        String aux = "";

        for (int i = 1; i < args.length; i++) {
            String word = args[i];
            if (word.startsWith("/")) {
                throw new WrongArgumentsException("kvothe.task.Todo does not accept arguments.");
            } else {
                aux += word + " ";
            }
        }

        if (aux.isEmpty()) {
            throw new WrongArgumentsException("No value for description." );
        }

        newArgs[0] = aux.trim();

        return newArgs;
    }
}
