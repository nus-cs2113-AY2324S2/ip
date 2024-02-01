import java.util.Scanner;

public class Zoro {
    private static final int MAX_TASKS = 100;
    private static final String TAB_SPACE = "    ";
    private static final String LINE = TAB_SPACE + "_____________________________________________________________";

    public static void main(String[] args) {
        String name = "Zoro";

        System.out.println(LINE);
        System.out.println(TAB_SPACE + "Hello I'm "+ name);
        System.out.println(TAB_SPACE + "What can I do for you?");
        System.out.println(LINE);

        String[] taskLists = new String[MAX_TASKS];
        int index = 0;
        Scanner in = new Scanner(System.in);

        while(true){
            String input;
            input = in.nextLine();
            if(input.equalsIgnoreCase("bye")){
                break;
            }

            if(input.equalsIgnoreCase("list" )) {
                int count = 0;
                System.out.println(LINE);
                System.out.println(TAB_SPACE + "Here are the tasks in your list:");
                while(count < MAX_TASKS && taskLists[count] != null){
                    System.out.println(TAB_SPACE + (count + 1) +". " + taskLists[count]);
                    count++;
                }
                System.out.println(LINE);
                continue;
            }
            if(index<MAX_TASKS) {

                taskLists[index++] = input;
                System.out.println(LINE);
                System.out.println(TAB_SPACE + "added: "+input);
                System.out.println(LINE);
            } else {
                System.out.println(LINE);
                System.out.println(TAB_SPACE + "Reached maximum limit for adding tasks");
                System.out.println(LINE);
            }

        }
        in.close();

        System.out.println(LINE);
        System.out.println(TAB_SPACE + "Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }
}
