import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String chatBot = "Hello! I'm TONY\n"
                + "What can I do for you?\n"
                + "_________________________";
        System.out.println(chatBot);
        String[] userCommands = new String[100];
        int count = 0;
        while(true){
            Scanner userInput = new Scanner(System.in);
            String task = userInput.nextLine();
            if(task.equals("bye")){
                System.out.println(
                        "_________________________\n"
                        + "Bye. Hope to see you again soon!\n"
                        + "_________________________\n");
                return;
            } else if(task.equals("list")){
                System.out.println("_________________________");
                System.out.println("\tYour List:");
                for(int i = 0; i < count; i++){
                    System.out.println("\t" + (i+1) + ". "
                            + userCommands[i]);
                }
                System.out.println("_________________________");
            }else{
                userCommands[count] = task;
                System.out.println(
                        "_________________________\n"
                        + "\tadded: " + userCommands[count]
                        + "\n"
                        + "_________________________\n");
                count++;
            }
        }

    }
}
