public class inputParser {
    protected String[] splitInput = new String[3];

    public inputParser(String userInput, String commandType) throws AragornException {
        String[] splitDeadline;
        String[] splitEvent;

        switch (commandType) {
            case "MARK":
                this.splitInput[0] = String.valueOf(Integer.parseInt(userInput.substring(5).trim()) - 1);
                this.splitInput[1] = null;
                this.splitInput[2] = null;
                break;

            case "UNMARK":
                this.splitInput[0] = String.valueOf(Integer.parseInt(userInput.substring(7).trim()) - 1);
                this.splitInput[1] = null;
                this.splitInput[2] = null;
                break;

            case "TODO":
                try {
                    this.splitInput[0] = userInput.substring(4);
                    if (this.splitInput[0].trim().isEmpty()){
                        throw new AragornException("Task description is empty!");
                    }
                    this.splitInput[1] = null;
                    this.splitInput[2] = null;
                } catch (AragornException e) {
                    System.out.println(e.getMessage());
                }
                break;

            case "DEADLINE":
                try {
                    splitDeadline = userInput.split("/by", 2);
                    this.splitInput[0] = splitDeadline[0].substring(8).trim();
                    if (this.splitInput[0].trim().isEmpty()) {
                        throw new AragornException("Task description is empty!");
                    }
                    this.splitInput[1] = splitDeadline[1].trim();
                    if (this.splitInput[1].trim().isEmpty()) {
                        throw new AragornException("Deadline condition is empty!");
                    }
                    this.splitInput[2] = null;
                } catch (AragornException e) {
                    System.out.println(e.getMessage());
                } catch (NullPointerException e) {
                    break;
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Invalid format!");
                }
                break;

            case "EVENT":
                splitDeadline = userInput.split(" /from ", 2);
                splitEvent = splitDeadline[1].split(" /to ", 2);
                this.splitInput[0] = splitDeadline[0].substring(6);
                this.splitInput[1] = splitEvent[0];
                this.splitInput[2] = splitEvent[1];
                break;
        }
    }

    public static String commandIdentifier(String userInput) {
        String commandType;

        if (userInput.equals("bye")) {
            commandType = "BYE";
        } else if (userInput.equals("list")) {
            commandType = "LIST";
        } else if (userInput.startsWith("unmark")) {
            commandType = "UNMARK";
        } else if (userInput.startsWith("mark")) {
            commandType = "MARK";
        } else if (userInput.startsWith("todo")) {
            commandType = "TODO";
        } else if (userInput.startsWith("deadline")) {
            commandType = "DEADLINE";
        } else if (userInput.startsWith("event ")) {
            commandType = "EVENT";
        } else if (userInput.equals("/help")) {
            commandType = "HELP";
        } else {
            commandType = "INVALID";
        }
        return commandType;
    }
}
