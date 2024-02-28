public class Ui {
    private static final String greeting = "Hello clown I am JingXiang";
    private static final String inputMessage =
            """
            ------------stop clowning and type sth------------
            Any dates must be in dd/mm/yyyy hh:mm:ss. Be precise!
            sample task inputs
            todo <description>
            deadline <description> /by <dd/mm/yyyy hh:mm:ss>
            event <description> /from <dd/mm/yyyy hh:mm:ss> /to <dd/mm/yyyy hh:mm:ss>
            --------------------------------------------------
            """;
    private static final String leaveMessage = "Good day clown";
    Ui() {
        System.out.println(greeting);
    }

    void printInputMessage() {
        System.out.println(inputMessage);
    }

    void printLeaveMessage() {
        System.out.println(leaveMessage);
    }



}
