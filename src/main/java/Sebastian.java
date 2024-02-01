import java.util.Scanner;
public class Sebastian {
    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);
        String logo = " ____       _               _   _ \n" +
                "/ ___|  ___| |__   __ _ ___| |_(_) __ _ _ __ \n" +
                "\\___ \\ / _ \\ '_ \\ / _` / __| __| |/ _` | '_ \\ \n" +
                " ___) |  __/ |_) | (_| \\__ \\ |_| | (_| | | | | \n" +
                "|____/ \\___|_.__/ \\__,_|___/\\__|_|\\__,_|_| |_| \n";

        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Sebastian, your humble butler\n" +
                "What can I do for you?");

        //List adding functionality
        TaskManager taskManager = new TaskManager();
        String userInput = myScanner.nextLine();
        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                taskManager.showListContents();
                userInput = myScanner.nextLine();
            } else if (userInput.contains("mark")) {
                taskManager.changeTaskStatus(userInput);
                userInput = myScanner.nextLine();
            } else {
                System.out.println("added: " + userInput);
                taskManager.addListContents(userInput);
                userInput = myScanner.nextLine();
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
