package roleypoley.exception;

public class RoleyPoleyFileException extends Exception {
    public RoleyPoleyFileException(String errorMessage) {
        switch (errorMessage) {
        case "DeadLineFormatError":
            System.out.println("\tFormat for DeadLine tasks in file is incorrect, please edit the file according to the " +
                    "format below");
            System.out.println("""
                    \n\t2. For tasks to be done by a certain time.\s
                    \tD | <markIsDone, 0 for Undone, 1 for Done> | <Task Description> (by: <Due Date>)""");
        case "EventFormatError":
            System.out.println("\tFormat for Event tasks in file is incorrect, please edit the file according to the " +
                    "format below");
            System.out.println("""
                    \n\t3. For tasks to be doe within a time frame.\s
                    \tE | <markIsDone, 0 for Undone, 1 for Done> | <Task Description> (from: <Start Time> to: <End Time>)""");
        default:
            System.out.println("""
                    \n\tFile is incompatible with ChatBot. Ensure format follows the example below:
                    \t1. Tasks to be done with no time restriction.\s
                    \ttodo <Task Description>\s""");
            System.out.println("""
                    \n\t2. Tasks to be done by a certain time.\s
                    \tdeadline <Task Description> /by <Due Date>""");
            System.out.println("""
                    \n\t3. Tasks to be done within a time frame.\s
                    \tevent <Task Description> /from <Start Time> /to <End Time>""");
        }
    }
}
