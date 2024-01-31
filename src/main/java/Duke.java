import java.util.Scanner;

public class Duke {

    public static void addList(){

    }
    public static void main(String[] args) {
        System.out.println("____________________________________________________________\n" +
                "Hello! I'm Duck\n" +
                "What can I do for you?");

        String[] texts = new String[100]; //stores user inputs into array called texts
        String userInput;
        int index = 0; //index of where the userInput is stored in texts
        do {
            Scanner in = new Scanner(System.in);
            userInput = in.nextLine();
            if (userInput.equals("list")) {
                for (int i = 0; i < index; i++) {
                    System.out.println(i+1 + ". " + texts[i]);
                }
                System.out.println("____________________________________________________________\n");
            } else if (!userInput.equals("bye")){
                texts[index] = userInput;
                index++;
                System.out.println("____________________________________________________________");
                System.out.println("added: " + userInput);
                System.out.println("____________________________________________________________\n");
            }

        } while (!userInput.equals("bye"));

        System.out.println("____________________________________________________________\n" +
                "Bye. Hope to see you again soon!\n" +
                "____________________________________________________________");
    }
}
