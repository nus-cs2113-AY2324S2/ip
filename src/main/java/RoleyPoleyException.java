public class RoleyPoleyException extends Exception {
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
        case "FileContentError":
            System.out.println("\tFormat in file is incorrect, please edit the file according to the format below");
            System.out.println("""
                    \n\t1. For tasks to be done with no time restriction.\s
                    \tT | <markIsDone, 0 for Undone, 1 for Done> | <Task Description>""");
            System.out.println("""
                    \n\t2. For tasks to be done by a certain time.\s
                    \tD | <markIsDone, 0 for Undone, 1 for Done> | <Task Description> (by: <Due Date>)""");
            System.out.println("""
                    \n\t3. For tasks to be doe within a time frame.\s
                    \tE | <markIsDone, 0 for Undone, 1 for Done> | <Task Description> (from: <Start Time> to: <End Time>)""");
        default:
            System.out.println("""
                    \n\tInvalid entry. Please enter input according to the commands below:
                    \t1. Tasks to be done with no time restriction.\s
                    \ttodo <Task Description>\s""");
            System.out.println("""
                    \n\t2. Tasks to be done by a certain time.\s
                    \tdeadline <Task Description> /by <Due Date>""");
            System.out.println("""
                    \n\t3. Tasks to be done within a time frame.\s
                    \tevent <Task Description> /from <Start Time> /to <End Time>""");
            System.out.println("\n\t4. List all tasks in current task list. \n\tEnter 'list' in the command prompt");
            System.out.println("\n\t5. Exit from Chatbot. \n\tEnter 'bye' in the command prompt ");
        }
    }
}
