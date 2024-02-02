import java.util.Scanner;

public class Laika {
    public static void main(String[] args) {
        String line;
        Scanner in = new Scanner(System.in);
        String[] taskList = new String[100];
        int count = 0;
        boolean continueConvo = true;
        String logo = " ^..^      /\n"
                + " /_/\\_____/\n"
                + "    /\\   /\\\n"
                + "   /  \\ /  \\\n";
        System.out.println("Laika: Hi! My name is Laika!\n\n" + logo + "Laika: How can I help you?");
        while(continueConvo){
            System.out.print("User: ");
            line = in.nextLine();
            switch(line){
                case "list":
                    System.out.println("Task List:");
                    for (int i = 0; i<count;i++){
                        System.out.println((i + 1) + ". " + taskList[i]);
                    }
                    break;
                case "bye":
                    continueConvo = false;
                    break;
                default:
                    taskList[count] = line;
                    count++;
                    System.out.println("Laika: Task \"" + line + "\" has been added!" );

            }
        }

        System.out.println("Laika: Bye! Have a nice day!");
    }
}
