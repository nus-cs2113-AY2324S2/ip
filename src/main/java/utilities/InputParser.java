package utilities;

import exceptions.AragornException;

public class InputParser {
    private String[] splitInput = new String[3];

    public InputParser(String userInput, String commandType) throws AragornException {
        String[] splitDeadline;
        String[] splitEvent;
        String LINE =  "    __________________________________________________________\n";
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

            case "DELETE":
                this.splitInput[0] = String.valueOf(Integer.parseInt(userInput.substring(7).trim()) - 1);
                this.splitInput[1] = null;
                this.splitInput[2] = null;
                break;

            case "TODO":
                try {
                    this.splitInput[0] = userInput.substring(4);
                    if (this.splitInput[0].trim().isEmpty()){
                        throw new AragornException(LINE + "    Task description is empty!\n" + LINE);
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
                        throw new AragornException(LINE + "    Task description is empty!\n" + LINE);
                    }
                    this.splitInput[1] = splitDeadline[1].trim();
                    if (this.splitInput[1].trim().isEmpty()) {
                        throw new AragornException(LINE + "    Deadline condition is empty!\n" + LINE);
                    }
                    this.splitInput[2] = null;
                } catch (AragornException e) {
                    System.out.println(e.getMessage());
                } catch (NullPointerException e) {
                    break;
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println(LINE + "    Invalid format!\n" + LINE);
                }
                break;

            case "EVENT":
                try {
                    splitDeadline = userInput.split("/from", 2);

                    this.splitInput[0] = splitDeadline[0].substring(5).trim();
                    if (this.splitInput[0].trim().isEmpty()) {
                        throw new AragornException(LINE + "    Task description is empty!\n" + LINE);
                    }
                    splitEvent = splitDeadline[1].split("/to", 2);
                    this.splitInput[1] = splitEvent[0].trim();
                    if (this.splitInput[1].trim().isEmpty()) {
                        throw new AragornException(LINE + "    Start condition is empty!\n" + LINE);
                    }
                    this.splitInput[2] = splitEvent[1].trim();
                    if (this.splitInput[2].trim().isEmpty()) {
                        throw new AragornException(LINE + "    End condition is empty!\n" + LINE);
                    }
                } catch (AragornException e) {
                    System.out.println(e.getMessage());
                }  catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println(LINE + "    Invalid format!\n" + LINE);
                } catch (NullPointerException e) {
                    break;
                }
                break;
        }
    }

    public String[] getSplitInput() {
        return splitInput;
    }

    public static String commandIdentifier(String userInput) {
        String commandType;

        if (userInput.trim().equals("bye")) {
            commandType = "BYE";
        } else if (userInput.trim().equals("list")) {
            commandType = "LIST";
        } else if (userInput.trim().startsWith("unmark")) {
            commandType = "UNMARK";
        } else if (userInput.trim().startsWith("mark")) {
            commandType = "MARK";
        } else if (userInput.trim().startsWith("delete")) {
            commandType = "DELETE";
        } else if (userInput.trim().startsWith("todo")) {
            commandType = "TODO";
        } else if (userInput.trim().startsWith("deadline")) {
            commandType = "DEADLINE";
        } else if (userInput.trim().startsWith("event")) {
            commandType = "EVENT";
        } else if (userInput.trim().equals("/help")) {
            commandType = "HELP";
        } else {
            commandType = "INVALID";
        }
        return commandType;
    }
}
