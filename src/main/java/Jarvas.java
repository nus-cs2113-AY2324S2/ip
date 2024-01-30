import java.util.Scanner;
public class Jarvas {
    public static String partitionLine = "____________________________________________________________\n";
    public static void initialiseBot(){
        String logo = " _____                                  \n" +
                "(___  )                                 \n" +
                "    | |   _ _  _ __  _   _    _ _   ___ \n" +
                " _  | | /'_` )( '__)( ) ( ) /'_` )/',__)\n" +
                "( )_| |( (_| || |   | \\_/ |( (_| |\\__, \\\n" +
                "`\\___/'`\\__,_)(_)   `\\___/'`\\__,_)(____/";
        System.out.println("Hello from\n" + logo);
        String greet = partitionLine
                + " Hello! I'm Jarvas\n"
                + " What can I do for you?\n"
                + partitionLine;
        System.out.println(greet);

    }
    public static void terminateBot(){
        String exit = partitionLine
                + " Bye! Hope to see you again soon!\n"
                + partitionLine;
        System.out.println(exit);
    }
    public static void runTime(){
        Scanner in = new Scanner(System.in);
        String userInput = in.nextLine();

        while (!userInput.equals("bye")) {
            echoInput(userInput);
            userInput = in.nextLine();
        }
    }

    public static void echoInput(String userInput){
        String echoOutput = partitionLine
                + userInput
                + "\n"
                + partitionLine;
        System.out.print(echoOutput);
    }

    public static void main(String[] args) {
        initialiseBot();
        runTime();
        terminateBot();
    }
}