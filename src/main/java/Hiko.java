import java.util.Scanner;
public class Hiko {
    static String break_line = "----------------------------------------";
    public static void main(String[] args) {
        String logo =


        "  \n" +
                " __ __  ____  __  _   ___  \n" +
                "|  |  ||    ||  |/ ] /   \\ \n" +
                "|  |  | |  | |  ' / |     |\n" +
                "|  _  | |  | |    \\ |  O  |\n" +
                "|  |  | |  | |     \\|     |\n" +
                "|  |  | |  | |  .  ||     |\n" +
                "|__|__||____||__|\\_| \\___/ \n" +
                "                           \n";
        System.out.println("Hello from \n" + logo);
        Scanner scanner = new Scanner(System.in);
        String input = " ";

        while(!input.contains("bye"))
        {
            System.out.println("What can I do for you?");
            input = scanner.nextLine();

            //deal with the exception of whenever user inputs bye
            //skip the repeat
            if(input.equalsIgnoreCase("bye")){
                scanner.close();
                break;

            }


            Echo echo = new Echo();
            System.out.println(echo.repeat(input));
            System.out.println(break_line);
        }

        scanner.close();
        System.out.println("Kkkkkk thanks bye " );

    }
}
