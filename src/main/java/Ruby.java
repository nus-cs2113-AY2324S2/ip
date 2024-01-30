import java.util.ArrayList;
import java.util.Scanner;

public class Ruby {
    private static ArrayList<Task> taskList= new ArrayList<Task>();
    private static int taskNo = 0;

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
        String words;
        Scanner in = new Scanner(System.in);
        words = in.nextLine();
        String[] userInputs = words.split(" ");
        String order=userInputs[0];

        while (!order.toLowerCase().equals("bye")){
            switch (order.toLowerCase()){
            case "list":
                System.out.println("    " + "--------------");
                System.out.println("    " + "Here are the tasks in your list:");
                for (Task task:taskList){
                    task.printTask();
                }
                System.out.println("    " + "--------------");
                break;
            case "mark":
                int n = Integer.parseInt(userInputs[1])-1;
                if (n >= taskNo){
                    print("Sorry, task unfound.");
                }else{
                    taskList.get(n).markedTask();
                }
                break;
            case "unmark":
                int m = Integer.parseInt(userInputs[1])-1;
                if (m >= taskNo){
                    print("Sorry, task unfound.");
                }else{
                    taskList.get(m).unmarkedTask();
                }
                break;
            default:
                taskNo++;
                taskList.add(new Task(words,taskNo));
                print("added: " + words);
            }

            words = in.nextLine();
            userInputs = words.split(" ");
            order=userInputs[0];
        }
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
