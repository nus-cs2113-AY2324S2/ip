public class PrintText {
    public final static String LINEBREAK = "____________________________" +
            "________________________________";
    public static void print(String text) {
        System.out.println(text);
    }

    public static void printWithLinebreak(String text) {
        print(LINEBREAK);
        print(text);
        print(LINEBREAK + "\n");
    }
}
