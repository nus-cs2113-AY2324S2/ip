public class Ui {

    private static final String LINE_SEPARATOR = "____________________________________________________________";

    public static void welcomeMessage() {
        System.out.println(LINE_SEPARATOR + "\n" +
                "Hello! I'm Duck\n" +
                "What can I do for you?\n" +
                "  _____  _    _  _____ _  __\n" +
                " |  __ \\| |  | |/ ____| |/ /\n" +
                " | |  | | |  | | |    | ' / \n" +
                " | |  | | |  | | |    |  <  \n" +
                " | |__| | |__| | |____| . \\ \n" +
                " |_____/ \\____/ \\_____|_|\\_\\");
    }

    public static boolean exitMessage() {
        boolean isFinished;
        System.out.println(LINE_SEPARATOR + "\n" + "Bye. Hope to see you again soon!\n" + LINE_SEPARATOR);
        isFinished = true;
        return isFinished;
    }

}
