public class RoleyPoleyException extends Exception {

    protected String errorMessage;
    public RoleyPoleyException(String errorMessage) {
        switch (errorMessage) {
        case "markError","unmarkError", "deleteError":
            System.out.println("\tTask cannot be found. Enter command 'list' to view task list.");
            break;
        case "toDoError":
            System.out.println("\tInvalid entry. Please enter input in the following format:" +
                    "\n\ttodo <Task Description>");
            break;
        case "deadlineError":
            System.out.println("\tInvalid entry. Please enter input in the following format:" +
                    "\n\tdeadline <Task Description> /by <Due Date>");
            break;
        case "eventError":
            System.out.println("\tInvalid entry. Please enter input in the following format:" +
                    "\n\tevent <Task Description> /from <Start Time> /to <End Time");
            break;
        default:
            System.out.println("\tInvalid entry. Please enter input according to the commands below:" +
                    "\n\t1. Tasks to be done with no time restriction. \n\ttodo <Task Description> ");
            System.out.println("\n\t2. Tasks to be done by a certain time. \n\tdeadline <Task Description>" +
                    " /by <Due Date>");
            System.out.println("\n\t3. Tasks to be done within a time frame. \n\tevent <Task Description>" +
                    " /from <Start Time> /to <End Time>");
            System.out.println("\n\t4. List all tasks in current task list. \n\tEnter 'list' in the command prompt");
            System.out.println("\n\t5. Exit from Chatbot. \n\tEnter 'bye' in the command prompt ");
        }
    }
}
