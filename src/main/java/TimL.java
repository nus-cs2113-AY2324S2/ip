import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.lang.String;

public class TimL {
    public static void respondToCommand(String text, List<String> list){
        if (text.equals("list")){
            printToDoList(list);
        }else {
            addToList(text, list);
        }
    }
    public static void printToDoList(List<String> list){
        int i = 1;
        System.out.println("    ____________________________________________________________\n");
        for (String lists : list){
            System.out.println("    " + i + ": " + lists + "\n");
            i++;
        }
        System.out.println("    ____________________________________________________________\n");
    }


    public static void addToList(String item, List<String> list){
        list.add(item);
        System.out.println("    ____________________________________________________________\n" +
                "    added: " + item + "\n" +
                "    ____________________________________________________________\n");
    }

    public static void main(String[] args) {
        List<String> todolist = new ArrayList<>(  100);
        System.out.println("    ____________________________________________________________\n" +
                "     Hello! I'm TimL\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________\n");
        String response;
        Scanner in = new Scanner(System.in);
        response = in.nextLine();
        while (!response.equals("bye")){
            respondToCommand(response, todolist);
            in = new Scanner(System.in);
            response = in.nextLine();
        }
        System.out.println("    ____________________________________________________________\n" +
                "     Bye. Hope to see you again soon!\n" +
                "    ____________________________________________________________\n");
    }
}
