import java.util.Scanner;

public class Suv {
    public static void main(String[] args) {
        String name = "Suv";
        Scanner in = new Scanner(System.in);
        String[] tasks = new String[100];
        int taskIndex = 0;

        System.out.println("____________________________________________________________\n" +
                " Hello! I'm " + name + "\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n");

        while(true) {
            String input = in.nextLine();
            if(input.equals("bye")){
                System.out.println( "____________________________________________________________\n" +
                        " Bye. Hope to see you again soon!\n" +
                        "____________________________________________________________\n");
                break;
            }else if (input.equals("list")){
                System.out.println("____________________________________________________________");
                for(int i = 0; i < taskIndex; i++){
                    int index = i + 1;
                    System.out.println(" " + index + ". " + tasks[i]);
                }
                System.out.println("____________________________________________________________");
            } else {
                tasks[taskIndex++] = input;
                System.out.println("____________________________________________________________\n" +
                        " added: " + input +
                        "\n____________________________________________________________\n");
            }
        }
    }
}