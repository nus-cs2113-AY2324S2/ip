public class PrintText {
    public final static String LINEBREAK = "____________________________" +
            "________________________________";

    /**
     * Print a line of text.
     *
     * @param text The input text to be printed.
     */
    public static void print(String text) {
        System.out.println(text);
    }

    /**
     * Print a line of text along with one line break above and another below.
     *
     * @param text The input text to be printed.
     */
    public static void printWithLinebreak(String text) {
        print(LINEBREAK);
        print(text);
        print(LINEBREAK + "\n");
    }
}
