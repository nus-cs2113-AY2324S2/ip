public class Adam {
    private static final String HORIZONTAL_LINE = "_____________________________"
            + "_______________________________\n";

    public static void main(String[] args) {
        String logo = "              _                 \n"
                + "     /\\      | |                \n"
                + "    /  \\   __| | __ _ _ __ ___  \n"
                + "   / /\\ \\ / _` |/ _` | '_ ` _ \\ \n"
                + "  / ____ \\ (_| | (_| | | | | | |\n"
                + " /_/    \\_\\__,_|\\__,_|_| |_| |_|\n";

        String greeting = HORIZONTAL_LINE
                + "Hello! I'm Adam\n"
                + "What can I do for you?\n"
                + HORIZONTAL_LINE;

        System.out.print("Hello from\n" + logo + greeting);
        System.out.print("Bye. Hope to see you again soon!\n" + HORIZONTAL_LINE);
    }
}
