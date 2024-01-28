import java.util.Scanner;

public class Binks {
    public static void greetUser(){
        createLineSpacing();
        System.out.println("Hello! I'm Binks.");
        System.out.println("What can I do for you?");
        createLineSpacing();
    }

    public static void createLineSpacing(){
        System.out.println("____________________________________________________________");
    }
    public static void exitChatbot(){
        createLineSpacing();
        System.out.println("Bye. Hope to see you again soon!");
        createLineSpacing();
    }
    public static void main(String[] arg){
        greetUser();
        List list = new List();
        while(true){
            String line;
            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            if (line.equalsIgnoreCase("list")){
                list.getList();
            }
            else if (line.equalsIgnoreCase("bye")) {
                exitChatbot();
                break;
            }
            else {
                list.addItem(line);
            }
        }
    }
}
