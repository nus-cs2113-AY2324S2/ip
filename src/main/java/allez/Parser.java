package allez;
public class Parser {
    static String[] parseEventInput(String line) throws MissingDetailsException {
        String[] checkFrom = line.split(" /from ", 2);
        String[] checkTo = checkFrom[1].split(" /to ", 2);
        String[] lineSegment = new String[3];
        lineSegment[0] = checkFrom[0].substring(5).trim();
        lineSegment[1] = checkTo[0].trim();
        lineSegment[2] = checkTo[1].trim();
        if(lineSegment[0].isBlank() || lineSegment[1].isBlank() || lineSegment[2].isBlank()) {
            throw new MissingDetailsException();
        }
        return lineSegment;
    }

    static String[] parseDeadlineInput(String line) throws MissingDetailsException {
        String[] lineSegment = line.substring(9).split(" /by ", 2);
        lineSegment[0] = lineSegment[0].trim();
        lineSegment[1] = lineSegment[1].trim();
        if (lineSegment[0].isBlank() || lineSegment[1].isBlank()) {
            throw new MissingDetailsException();
        }
        return lineSegment;
    }

    public static String parseFind(String line) {
        return line.substring(4).trim();
    }

    public String parseCommand(String currentInput) {
        return currentInput.split(" ", 2)[0];
    }

    static int parseMarkTask(String line) {
        return Integer.parseInt(line.substring(4).trim()) -1;
    }

    static int parseDelete(String line) {
        return Integer.parseInt(line.substring(6).trim()) -1;
    }



}
