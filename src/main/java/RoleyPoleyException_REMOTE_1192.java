public class RoleyPoleyException extends Exception {

    protected String errorMessage;
    public RoleyPoleyException(String errorMessage) {
        switch (errorMessage) {
        case "markError","unmarkError":
            System.out.println("Task cannot be found. Enter command 'list' to view task list.");
            break;
        case "toDoError":
            System.out.println("Invalid entry. Please enter input in the following format:" +
                    "\ntodo <Task Description>");
            break;
        case "deadlineError":
            System.out.println("Invalid entry. Please enter input in the following format:" +
                    "\ndeadline <Task Description> /by <Due Date>");
            break;
        case "eventError":
            System.out.println("Invalid entry. Please enter input in the following format:" +
                    "\nevent <Task Description> /from <Start Time> /to <End Time");
            break;
        default:
            System.out.println("Invalid entry. Please enter input according to the commands below:" +
                    "\n1. Tasks to be done with no time restriction. \n\ttodo <Task Description> ");
            System.out.println("\n2. Tasks to be done by a certain time. \n\tdeadline <Task Description>" +
                    " /by <Due Date>");
            System.out.println("\n3. Tasks to be done within a time frame. \n\tevent <Task Description>" +
                            " /from <Start Time> /to <End Time>");
            System.out.println("\n4. List all tasks in current task list. \n\tEnter 'list' in the command prompt");
            System.out.println("\n5. Exit from Chatbot. \n\tEnter 'bye' in the command prompt ");
        }
    }
}
