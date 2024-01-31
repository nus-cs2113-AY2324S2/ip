import java.util.Scanner;

public class Casper {
    private static final String separator = "\t_____________________________________________";
    private static String[] taskList = new String[100];
    private static int noOfTasks = 0;
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
        wrapEchoMessage("\t Boo! I'm Casper!\n\t What can I do for you?");
    }
    private static void handleQueries(){
        Scanner inputScanner = new Scanner(System.in);
        String userInput;
        while(true){
            userInput = inputScanner.nextLine();
            if(userInput.equals("bye")){
                break;
            }
            if(userInput.equals("list")){
                echoTaskList();
            }else{
                taskList[noOfTasks] = userInput;
                noOfTasks++;
                wrapEchoMessage("\t added: "+userInput);
            }
        }
    }
    private static void echoTaskList(){
        System.out.println(separator);
        for(int i=1;i<=noOfTasks;i++){
            System.out.println("\t "+i+". "+taskList[i-1]);
        }
        System.out.println(separator);
    }
    public static void main(String[] args) {
        echoGreetings();
        handleQueries();
        wrapEchoMessage("\t Alright, see you around!");
    }
}
