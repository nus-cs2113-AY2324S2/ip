public class Jarvas {
    public static void initialiseBot(){
        String logo = " _____                                  \n" +
                "(___  )                                 \n" +
                "    | |   _ _  _ __  _   _    _ _   ___ \n" +
                " _  | | /'_` )( '__)( ) ( ) /'_` )/',__)\n" +
                "( )_| |( (_| || |   | \\_/ |( (_| |\\__, \\\n" +
                "`\\___/'`\\__,_)(_)   `\\___/'`\\__,_)(____/";
        System.out.println("Hello from\n" + logo);
        String greet = "____________________________________________________________\n"
                + " Hello! I'm Jarvas\n"
                + " What can I do for you?\n"
                + "____________________________________________________________\n";
        System.out.println(greet);

    }
    public static void terminateBot(){
        String exit = "____________________________________________________________\n"
                + " Bye! Hope to see you again soon!\n"
                + "____________________________________________________________";
        System.out.println(exit);
    }

    public static void main(String[] args) {
        initialiseBot();
        terminateBot();
    }
}