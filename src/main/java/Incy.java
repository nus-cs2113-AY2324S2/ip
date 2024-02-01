import java.util.Scanner;

public class Incy {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;
        String[] tasks = new String[100];
        int taskCount = 0;
        String logo = " ____  _  _  ___  _  _ \n"
                    + "(_  _)( \\( )/ __)( \\/ )\n"
                    + " _)(_  )  (( (__  \\  / \n"
                    + "(____)(_)\\_)\\___) (__) \n";
        
        System.out.println("____________________________________________________________\n"
                            + "Oi bruv! I'm\n" + logo
                            + "Wotcha need from me today?\n"
                            + "____________________________________________________________"
        );
        
        while (true) {
            input = scanner.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                break;
            } else if (input.equalsIgnoreCase("list")) {
                System.out.println("____________________________________________________________");
                if (taskCount == 0) {
                    System.out.println("Blimey, your list is empty, innit?");
                } else {
                    for (int i = 0; i < taskCount; i++) {
                        System.out.println((i + 1) + ". " + tasks[i]);
                    }
                }
                System.out.println("____________________________________________________________");
            } else {
                if (taskCount >= tasks.length) {
                    System.out.println("____________________________________________________________\n"
                                        + "Cor blimey! The list is full to the brim. Can't add more, sorry!"
                                        + "\n____________________________________________________________"
                    );
                } else {
                    tasks[taskCount] = input;
                    taskCount++;
                    System.out.println("____________________________________________________________\n"
                                        + "Sorted! Added: " + input
                                        + "\n____________________________________________________________"
                    );
                }
            }
        }
        
        System.out.println("____________________________________________________________\n"
                            + "Cya later mate!"
                            + "\n____________________________________________________________"
        );
        scanner.close();
    }
}