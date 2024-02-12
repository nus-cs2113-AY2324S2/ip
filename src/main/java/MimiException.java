public class MimiException {

    final static String INCORRECT_DEADLINE_FORMAT = "deadline format is invalid. " +
                                                    "Proper syntax: deadline [instruction] /by [deadline]";

    final static String INSUFFICIENT_DEADLINE_PARAMETERS = "deadline parameters is incomplete. " +
                                                            "Proper syntax: deadline [instruction] /by [deadline]";
    final static String INSUFFICIENT_INPUT_FOR_TODO = "Insufficient Input for a To-Do Instruction. Proper Syntax: todo [task]";


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
