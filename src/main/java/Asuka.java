import java.util.Scanner;

public class Asuka {
    public static void main(String[] args) {

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Asuka\nWhat can I do for you?");
        System.out.println("____________________________________________________________");
        Scanner myObj = new Scanner(System.in);
        String command = myObj.nextLine();
        String[] list = new String[100];
        int count = 0;
        while (!command.equals("bye")){
            System.out.println("____________________________________________________________");
            if (!command.equals("list")){
                System.out.println("added: " + command);
                list[count] = command;
                count++;
            }
            else{
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < count; i++){
                    System.out.println(i+1 + ". " + list[i]);
                }
            }
            System.out.println("____________________________________________________________");
            command = myObj.nextLine();
        };
        myObj.close();
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}
