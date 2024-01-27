import java.util.Scanner;

public class Joe {
    public static final String H_LINE = "____________________________________________________________\n";
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println(H_LINE + "HI I'M JOE\n" + "WHAT CAN I DO FOR YOU\n" + H_LINE);

        TaskManager taskManager = new TaskManager();
        boolean hasExitInput = false;
        while (!hasExitInput) {
            String input = in.nextLine();
            switch (input) {
            case "bye":
                hasExitInput = true;
                break;
            case "list":
                taskManager.listTasks();
                break;
            default:
                taskManager.addTask(input);
                System.out.println(H_LINE + "ADDED: " + input + '\n' + H_LINE);
                break;
            }
        }
        
        System.out.println(H_LINE + "GOODBYE. PLEASE COME BACK AGAIN :)\n" + H_LINE);
    }
}
