public class Parser {
    private static Deadline parseDeadline(String command){
        String[] commandWords = command.split("/");

        Deadline newDeadline;
        String taskName = commandWords[0].substring(9);
        String by = commandWords[1].substring(3);
        newDeadline = new Deadline(taskName, by);
        return newDeadline;
    }

    private static Event parseEvent(String command){
        String[] commandWords = command.split("/");

        Event newEvent;
        String eventName = commandWords[0].substring(6);
        String from = commandWords[1].substring(5);
        String to = commandWords[2].substring(3);
        newEvent = new Event(eventName, from, to);
        return newEvent;
    }

    private static Todo parseTodo(String command){
        String[] commandWords = command.split(" ");
        return new Todo(commandWords[1]);
    }
    public static Task parseCommand(String command){
        String[] commandWords = command.split(" ");
        if (commandWords[0].equals("deadline")){
            return parseDeadline(command);
        } else if (commandWords[0].equals("event")){
            return parseEvent(command);
        }
        return parseTodo(command);
    }
}
