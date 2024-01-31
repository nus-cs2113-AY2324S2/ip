import java.util.Scanner;

public class Casper {
    private static final String separator = "\t_____________________________________________";
    private static final Task[] taskList = new Task[100];
    private static int noOfTasks = 0;
    private static void wrapEchoMessage(String message){
        System.out.println(separator);
        System.out.println("\t "+message);
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
        wrapEchoMessage("Boo! I'm Casper!\n\t What can I do for you?");
    }
    private static void handleQueries(){
        Scanner inputScanner = new Scanner(System.in);
        while(true){
            String userInput = inputScanner.nextLine();
            if(userInput.equals("bye")){
                break;
            }
            if(userInput.equals("list")){
                echoTaskList();
            }else if(userInput.startsWith("mark")){
                int targetTaskNumber = markInputValidation(userInput);
                if(targetTaskNumber != -1){
                    taskList[targetTaskNumber-1].markTask();
                }
            }else if(userInput.startsWith("unmark")) {
                int targetTaskNumber = markInputValidation(userInput);
                if(targetTaskNumber != -1){
                    taskList[targetTaskNumber-1].unMarkTask();
                }
            }else{
                Task newTask = new Task(userInput);
                taskList[noOfTasks] = newTask;
                noOfTasks++;
                wrapEchoMessage("added: "+userInput);
            }
        }
    }
    private static void echoTaskList(){
        System.out.println(separator);
        if(noOfTasks==0){
            System.out.println("\t You have no tasks this time around.");
        }else{
            System.out.println("\t Here are the tasks in your list:");
            for(int i=1;i<=noOfTasks;i++){
                System.out.println("\t "+i+".["+taskList[i-1].getStatusIcon()+"] "+taskList[i-1].description);
            }
        }
        System.out.println(separator);
    }
    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
    private static int markInputValidation(String userInput){
        String[] userInputSplit =  userInput.split(" ");
        if(userInputSplit.length==2&&isNumeric(userInputSplit[1])){
            int targetTaskNumber = Integer.parseInt(userInputSplit[1]);
            if(targetTaskNumber<=noOfTasks&&targetTaskNumber>0){
                return targetTaskNumber;
            }else{
                wrapEchoMessage("That task does not exist!");
                return -1;
            }
        }else{
            wrapEchoMessage("Invalid input!");
            return -1;
        }
    }
    public static void main(String[] args) {
        echoGreetings();
        handleQueries();
        wrapEchoMessage("Alright, see you around!");
    }

}
