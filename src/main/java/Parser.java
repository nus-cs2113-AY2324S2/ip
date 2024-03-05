public class Parser {

    public static Command parse(String fullCommand) throws MavisException {
        String[] splitInput = fullCommand.split(" ", 2);
        String command = splitInput[0];
        CommandType type = CommandType.fromString(command);
        if (type == null) {
            throw new MavisException();
        }
        String[] args = splitInput.length > 1 ? splitInput[1].split("\\s+(?=/)", -1) : new String[0];
        return new Command(type, args);
    }
}
