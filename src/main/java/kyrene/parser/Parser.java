package kyrene.parser;

import kyrene.command.Command;
import kyrene.command.Commands;

public class Parser {

    public static Command parse(String sentence) throws NumberFormatException, IndexOutOfBoundsException {
        String[] commands = sentence.split(" ");
        String command = commands[0];
        int taskNumber;

        switch(command) {
            case "bye":
                return new Command(Commands.BYE);
            case "list":
                return new Command(Commands.LIST);
            case "mark":
                taskNumber = Integer.parseInt(commands[1]);
                return new Command(Commands.MARK,taskNumber);
            case "unmark":
                taskNumber = Integer.parseInt(commands[1]);
                return new Command(Commands.UNMARK,taskNumber);
            case "delete":
                taskNumber = Integer.parseInt(commands[1]);
                return new Command(Commands.DELETE,taskNumber);
            default:
                return new Command(Commands.ADD,sentence);
        }
    }


}
