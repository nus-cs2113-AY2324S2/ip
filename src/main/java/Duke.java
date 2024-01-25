
public class Duke {
    public static void main(String[] args) {
        printHorizontalLine();
        System.out.println("Hello! I'm Omoh");
        System.out.println("What can I do for you?");
        printHorizontalLine();
        System.out.println("Bye. Hope to see you again soon!");
        printHorizontalLine();
        /*
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
         */
    }

    //Method that prints horizontal line using "_" char
    private static void printHorizontalLine() {
        for (int i = 0 ; i < 50 ; i++){
            System.out.print("_");
        }
        System.out.println("");
        return;
    }
}
