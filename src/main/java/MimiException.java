public class MimiException {

    final static String INCORRECT_DEADLINE_FORMAT = "deadline format is invalid. " +
                                                    "Proper syntax: deadline [instruction] /by [deadline]";

    final static String INCORRECT_INSTRUCTION = "unknown instruction. " +
                                                "Type of instructions available [deadline/todo/event/list/bye]";


    final static String INSUFFICIENT_DEADLINE_PARAMETERS = "deadline parameters is incomplete. " +
                                                            "Proper syntax: deadline [instruction] /by [deadline]";
    final static String INSUFFICIENT_TODO_PARAMETERS = "todo parameters is incomplete. " + "Proper syntax: todo [task]";


    public static class InsufficientParameters extends Exception {
        public InsufficientParameters(String message){
            super("\u001B[31mError: " + message +  "\u001B[0m");
        }
    }

    public static class IncorrectFormat extends Exception {
        public IncorrectFormat(String message){
            super("\u001B[31mError: " + message + "\u001B[0m");
        }
    }
}
