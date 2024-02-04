//import java.lang.reflect.Array;
//
//public class SyntaxChecker {
//    public String command;
//    public String commandName;
//    public static String[] commandList = {"mark", "unmark", "list", "bye"};
//    public static String[] argumentTokens;
//    public SyntaxChecker(String command) {
//        this.command = command;
//    }
//    public boolean runChecks() {
//        return this.isValidFirstWord() && this.isValidArguments();
//    }
//
//    public boolean isValidFirstWord() {
//        for (String s : commandList) {
//            if (this.command.startsWith(s)) {
//                commandName = s;
//                return true;
//            }
//        }
//        System.out.println("Error: command not found. Try again.");
//        return false;
//    }
//    public boolean isValidArguments() {
//
//    }
//}
