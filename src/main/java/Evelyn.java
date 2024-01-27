import java.util.Scanner;
public class Evelyn {

    public static String[] tasks;
    public static int indexOfTask = 0;
    public static void printList(String[] args){
        int index = 1;
        for (String task : tasks){
            if(task == null){
                printLine();
                break;
            }
            else {
                System.out.println(index + ". " + task);
                index++;
            }
        }
    }
    public  static void echo(){
        String line;
        System.out.println("type your command: ");
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        printLine();
        if(line.equals("bye")){
            return;
        }
        else if(line.equals("list")){
            printList(tasks);
            echo();
        }
        else {
            tasks[indexOfTask] = line;
            indexOfTask++;
            System.out.println("added: " + line);
            printLine();
            echo();
        }
    }
    public static void printLine(){
        System.out.print("____________________________________________________________\n");
    }
    public static void greeting(){
        printLine();
        System.out.println("Hello! I'm Evelyn");
        System.out.println("What can I do for you?");
        printLine();
    }
    public static void end(){
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }
    public static void main(String[] args) {
        greeting();
        tasks = new String[100];
        echo();
        end();
    }

}
