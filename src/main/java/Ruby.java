import java.util.Scanner;

public class Ruby {
    private static final Scanner in  = new Scanner(System.in);
    private static TaskList t1 = new TaskList();
    private static String userInput;


    public static void main(String[] args) {
        greet();
        processor();
        exit();
    }

    private static void greet(){
        print(new String[]{"Hi, I am here. Greets from Ruby.", "What can I do for you?"});
    }

    private static void exit(){
        print("Bye. Always feels good work with you.");
    }

    private static void processor(){
        String [] userInputs = inputCatcher();

        while (checkout(userInputs)) {
            switch (userInputs[0].toLowerCase()){
            case "list":
                t1.showTaskList();
                break;
            case "mark":
                if ((userInputs.length != 2) || (!userInputs[1].matches("\\d+"))){
                    print("Incorrect order.");
                    break;
                }
                t1.markTask(Integer.parseInt(userInputs[1])-1);
                break;
            case "unmark":
                if ((userInputs.length != 2) || (!userInputs[1].matches("\\d+"))){
                    print("Incorrect order.");
                    break;
                }
                t1.unmarkTask(Integer.parseInt(userInputs[1])-1);
                break;
            default:
                t1.addTask(userInput);
            }
            userInputs = inputCatcher();
        }
    }

    private static String[] inputCatcher(){
        userInput = in.nextLine();
        return userInput.split(" ");
    }

    private static boolean checkout(String[] userInputs){
        return !userInputs[0].equalsIgnoreCase("bye");
    }

    private static void print(String thingToPrint){
        System.out.println("    " + "--------------");
        System.out.println("    " + thingToPrint);
        System.out.println("    " + "--------------");
    }

    private static void print(String[] thingsToPrint){
        System.out.println("    " + "--------------");
        for (String thing: thingsToPrint){
            System.out.println("    " + thing);
        }
        System.out.println("    " + "--------------");
    }
}
