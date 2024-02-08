public class Duke {
    private String name;

    public Duke(String name) {
        this.name = name;
    }

    public void greet() {
        System.out.println("=========================");
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");
        System.out.println("=========================");
    }

    public void exit() {
        System.out.println("=========================");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("=========================");
    }
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        Duke chatbot = new Duke("aoliba");
        chatbot.greet();
        chatbot.exit();
    }
}
