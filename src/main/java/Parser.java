/**
 * Parser a class to convert user commands into understandable command
 */
public class Parser {
    /**
     * getArray is a function to convert command into 2 arrays
     * @param line command
     * @return array[0] and array[1], description and detail
     */
    public String[] getArray(String line) {
        return line.split(" ");
    }
}
