import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = 
        "   J     A    SSSS    OOO   N   N \n"
        + "   J    A A   S      O   O  NN  N \n"
        + "   J   AAAAA   SSS   O   O  N N N \n"
        + "J  J  A     A     S  O   O  N  NN \n"
        + " JJJ  A     A  SSSS   OOO   N   N \n" +
                "                 \n";
        System.out.println(logo + " Eyy wassup I'm Jason\r\n"

        + " What can I do for you?\r\n"
        );
      

        Scanner scanner = new Scanner(System.in);
        String input;
        Task[] list = new Task[100];
        int num = 0;


        while (true) {
            input = scanner.nextLine();


            if (input.equalsIgnoreCase("bye")) { // User wants to exit
                System.out.println("Bye. See ya laterr!\r\n"
                        + "____________________________________________________________\r\n"
                        + "\n" + logo);
                break;
            }

            //split the input
            String[] parts = input.split(" ");
            //Check whether input contains "mark" or "unmark"
            if (parts.length > 1 && parts[0].equalsIgnoreCase("mark")) {
                int taskNumber = Integer.parseInt(parts[1]) - 1;
                list[taskNumber].markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(list[taskNumber]);
            } else if (parts.length > 1 && parts[0].equalsIgnoreCase("unmark")) {
                int taskNumber = Integer.parseInt(parts[1]) - 1;
                list[taskNumber].markAsNotDone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(list[taskNumber]);
            }

            else if (input.equalsIgnoreCase("list")) {

                for (int i = 0; i < num; i++) {
                    System.out.println(i+1 + ":" + list[i]);
                }
            } else {
                System.out.println("added: " + input);
                list[num] = new Task(input);
                num++;

            }


            System.out.println("____________________________________________________________");
        }

        scanner.close();
    }
}