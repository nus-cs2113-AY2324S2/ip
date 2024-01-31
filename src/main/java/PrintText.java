public class PrintText {
    public final static String HORIZON = "____________________________" +
            "________________________________";
    public static void print(String text) {
        System.out.println(text);
    }

    public static void printWithHorizon(String text) {
        print(HORIZON);
        print(text);
        print(HORIZON + "\n");
    }
}
