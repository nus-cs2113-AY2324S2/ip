import java.util.Scanner;

public class Todolist {
    private static final int MAX_LIST_LENGTH = 100;
    static String[] todolist = new String[MAX_LIST_LENGTH];
    static int ListCount= 0;
    static Scanner in = new Scanner(System.in);

    public static void AddToList(){
        String Task= in.nextLine();
        todolist[ListCount] = Task;
        ListCount++;
        return;
    }



    static void HandleList(){
        PrintList();
        String command ="";

        while(!command.equals("quit")){
            command = in.nextLine();
            switch (command){
            case"add":
                AddToList();
                System.out.println("Task added");
                PrintList();
                break;
            case"quit":
                System.out.println("Task change complete");
                break;
            default:
                System.out.println("Sorry, I can not understand this command, enter \"help\" for help");
                break;
            }

        }

    }

    public static void PrintList(){
        int i=1;
        System.out.print("---------------------------------------------\n");
        System.out.println("Here are your current tasks in your list:");
        for (String s :todolist ){
            if(s == null){
                continue;
            }
            System.out.printf("%d: "+s+"\n",i);
            i++;
        }
        System.out.print("---------------------------------------------\n");
    }

}
