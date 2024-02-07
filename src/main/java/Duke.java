import java.util.Locale;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String name = "Duck";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________\n" + "Hello! I'm " + name
                + "\n" + "What can I do for you?\n" + "\n"
                + "____________________________________________________________\n");
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        userInput = userInput.toLowerCase();
        List<String> userInputSaver = new ArrayList<>();
        List<String> statusOfInput = new ArrayList<>();
        while(!userInput.contains("bye")){

            System.out.println("____________________________________________________________\n");
            int indexOfMark;
            if (userInput.contains("list")){
                for (int i = 0; i < userInputSaver.size(); i++) {
                    int inputIndex = userInputSaver.indexOf(userInputSaver.get(i))+1;
                    System.out.println(inputIndex + "." + " [" + statusOfInput.get(i) + "] " + userInputSaver.get(i)+ '\n');
                }

            } else if (userInput.contains("mark")) {
                if (userInput.contains("unmark")){

                    indexOfMark = Integer.parseInt(userInput.split(" ")[1]) - 1;
                    statusOfInput.set(indexOfMark, " ");
                    System.out.println("OK, I've marked this task as not done yet:\n");
                    System.out.println("[ ] " + userInputSaver.get(indexOfMark) + "\n");

                }else{
                    indexOfMark = Integer.parseInt(userInput.split(" ")[1]) - 1;
                    statusOfInput.set(indexOfMark, "X");
                    System.out.println("Nice! I've marked this task as done:\n");
                    System.out.println("[X] " + userInputSaver.get(indexOfMark) + "\n");
                }
            }else{
                userInputSaver.add(userInput);
                statusOfInput.add(" ");
                System.out.println("added: " + userInput );
            }

            System.out.println("____________________________________________________________\n");
            userInput = scanner.nextLine();
            userInput = userInput.toLowerCase();
        }
        System.out.println("Bye. Hope to see you again soon!\n"
                + "\n" + "____________________________________________________________");

        }
}
