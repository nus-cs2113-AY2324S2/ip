package roleypoley.exception;

/**
 * Handle exception regarding reading and storing data from text file
 * Displays the appropriate format if there is an error
 */
public class RoleyPoleyFileException extends Exception {
    public RoleyPoleyFileException(String errorMessage) {
        switch (errorMessage) {
        case "DeadLineFormatError":
            System.out.println("\tFormat for DeadLine tasks in file is incorrect, please edit the file according to the " +
                    "format below");
            System.out.println("\n\t2. For tasks to be done by a certain time. \n" +
                               "\tD | <markIsDone, 0 for Undone, 1 for Done> | <Task Description> (by: <Due Date>)");
            break;
        case "EventFormatError":
            System.out.println("\tFormat for Event tasks in file is incorrect, please edit the file according to the " +
                    "format below");
            System.out.println("\n\t3. For tasks to be doe within a time frame. \n" +
                               "\tE | <markIsDone, 0 for Undone, 1 for Done> | <Task Description> (from: <Start Time> to: <End Time>)");
            break;
        default:
            System.out.println("\n\tFile is incompatible with ChatBot. Ensure format follows the example below:\n" +
                               "\t1. Tasks to be done with no time restriction. \n" +
                               "\ttodo <Task Description> ");
            System.out.println("\n\t2. Tasks to be done by a certain time. \n" +
                               "\tdeadline <Task Description> /by <Due Date>");
            System.out.println("\n\t3. Tasks to be done within a time frame. \n" +
                               "\tevent <Task Description> /from <Start Time> /to <End Time>");
            break;
        }
    }
}
