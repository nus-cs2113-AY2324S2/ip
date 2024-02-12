public class MimiException {

<<<<<<< Updated upstream:src/main/java/MimichatException.java
    // For input format exceptions
    public static class InvalidInputFormatException extends Exception {
        public InvalidInputFormatException(){
            super();
        }
=======
    final static String INCORRECT_DEADLINE_FORMAT = "deadline format is invalid. " +
                                                    "Proper syntax: deadline [instruction] /by [deadline]";

    final static String INSUFFICIENT_DEADLINE_PARAMETERS = "deadline parameters is incomplete. " +
                                                            "Proper syntax: deadline [instruction] /by [deadline]";
    final static String INSUFFICIENT_INPUT_FOR_TODO = "Insufficient Input for a To-Do Instruction. Proper Syntax: todo [task]";

>>>>>>> Stashed changes:src/main/java/MimiException.java

    public static class InsufficientParameters extends Exception {
        public InsufficientParameters(String message, Throwable cause){
            super(message, cause);
        }
        public InsufficientParameters(String message){
            super(message);
        }
    }

    public static class IncorrectFormat extends Exception {
        public IncorrectFormat(String message, Throwable cause){
            super(message, cause);
        }
        public IncorrectFormat(String message){
            super(message);
        }
    }
}
