public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        greet();
        exit();
    }

    private static void greet(){
        System.out.println("Hi, I am here. Greets from Ruby.");
        System.out.println("What can I do for you?");
    }

    private static void exit(){
        System.out.println("Bye. Always feels good work with you.");
    }
}
