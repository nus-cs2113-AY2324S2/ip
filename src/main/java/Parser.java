import Exceptions.DeadlineLackInputsException;
import Exceptions.EventLackInputsException;
import Exceptions.TodoLackInputsException;

public class Parser {
    protected String userInput;
    
    public Parser () {}
    
    // no error checking for processing task information has been implemented (e.g. deadline but no /by)
    public String[] processTaskInformation(String userInput) throws TodoLackInputsException, DeadlineLackInputsException, EventLackInputsException {
        userInput = userInput.toLowerCase();
        String[] wordArray = userInput.split(" ");
        String[] output = new String[4];
        if (wordArray[0].equals("todo")) {
            output[0] = "todo";
            output[1] = userInput.substring(4);
            if (output[1].trim().isEmpty()) {
                throw new TodoLackInputsException();
            }
            return output;
        } else if (wordArray[0].equals("deadline")) {
            output[0] = "deadline";
            int byIndex = userInput.indexOf("/by");
            output[1] = userInput.substring(8, byIndex);
            output[2] = userInput.substring(byIndex + 4);
            if (output[1].trim().isEmpty() || output[2].trim().isEmpty()) {
                throw new DeadlineLackInputsException();
            }
            return output;
        } else if (wordArray[0].equals("event")) {
            output[0] = "event";
            int fromIndex = userInput.indexOf("/from");
            output[1] = userInput.substring(5, fromIndex);
            int toIndex = userInput.indexOf("/to");
            output[2] = userInput.substring(fromIndex + 6, toIndex);
            output[3] = userInput.substring(toIndex + 4);
            if (output[1].trim().isEmpty() || output[2].trim().isEmpty() || output[3].trim().isEmpty()) {
                throw new EventLackInputsException();
            }
            return output;
        }
        else {
            output[0] = "error";
            return output;
        }
    }

}
