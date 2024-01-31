import java.util.Scanner;

public class Casper {
    private static final String separator = "\t_____________________________________________";
    private static void wrapEchoMessage(String message){
        System.out.println(separator);
        System.out.println(message);
        System.out.println(separator);
    }
    private static void echoGreetings(){
        String logo = "\t   ___ __ _ ___ _ __   ___ _ __\n"
                + "\t  / __/ _` / __| '_ \\ / _ \\ '__|\n"
                + "\t | (_| (_| \\__ \\ |_) |  __/ |   \n"
                + "\t  \\___\\__,_|___/ .__/ \\___|_|   \n"
                + "\t               | |              \n"
                + "\t               |_|               ";
        System.out.println("\tStarting\n" + logo);
        wrapEchoMessage("\tBoo! I'm Casper!\n\tWhat can I do for you?");
    }
    private static void handleQueries(){
        Scanner inputScanner = new Scanner(System.in);
        String userInput;
        boolean isRunning = true;
        while(isRunning){
            userInput = inputScanner.nextLine();
            if(!userInput.equals("bye")){
                wrapEchoMessage("\t" + userInput);
            }
            isRunning = !userInput.equals("bye");
        }
    }
    public static void main(String[] args) {
        echoGreetings();
        handleQueries();
        wrapEchoMessage("\tAlright, see you around!");
    }
}
