import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = 
        "   J     A    SSSS    OOO   N   N \n"
        + "   J    A A   S      O   O  NN  N \n"
        + "   J   AAAAA   SSS   O   O  N N N \n"
        + "J  J  A     A     S  O   O  N  NN \n"
        + " JJJ  A     A  SSSS   OOO   N   N \n";
        System.out.println("Hello from Shaivan\r\n"
        + " Eyy wassup I'm Jason\r\n" 
        + " What can I do for you?\r\n"
        + logo);
      

        Scanner scanner = new Scanner(System.in);
        String input;
        String[] list = new String[100];
        int num = 0;

        while (true) {
            input = scanner.nextLine();


            if (input.equalsIgnoreCase("bye")) { // User wants to exit
                System.out.println("Bye. See ya laterr!\r\n"
                        + "____________________________________________________________\r\n"
                        + "\n" + logo);
                break;
            }



            if (input.equalsIgnoreCase("list")) {

                for (int i = 0; i < num; i++) {
                    System.out.println(i+1 + ":" + list[i]);
                }
            } else {
                System.out.println("added: " + input);
                list[num] = input;
                num++;

            }



            System.out.println("____________________________________________________________");
        }

        scanner.close();
    }
}