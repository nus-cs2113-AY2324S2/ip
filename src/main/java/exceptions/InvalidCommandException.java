package exceptions;

import utils.constants;

// Custom exception for handling invalid task input
public class InvalidCommandException extends Exception {
    public InvalidCommandException() {
        System.out.println(constants.BREAKLINE);
        System.out.println("""
                The command is not correct.
                Here is a list of correct command:
                list - to see the task list
                todo something - to create a todo task
                event something - to create an event task
                deadline something - to create a deadline task
                mark n - to mark the nth task as done
                unmark n - to undo the nth task
                bye - to exit the chat with Asuka
                 """);
        System.out.println(constants.BREAKLINE);
        System.out.println("Now try typing something:");
    }
}
