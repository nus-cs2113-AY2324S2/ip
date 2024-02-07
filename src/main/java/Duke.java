import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";*/

        //System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm G.one");
        System.out.println("--------------------------------------");
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        while (flag){
            System.out.print("Whats up? ");
            String userInput = scanner.nextLine();
            if (userInput.equalsIgnoreCase("bye")){
                flag = false;
            }
            else{
                System.out.println("Well...." + userInput);
            }
        }
        System.out.println("Goodbye!");
        scanner.close();
    }
}
