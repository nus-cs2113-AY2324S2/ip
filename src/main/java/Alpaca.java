import java.util.Scanner;

public class Alpaca {
        private static final String LOGO = "           _      _____        _____          \n" +
                "     /\\   | |    |  __ \\ /\\   / ____|   /\\    \n" +
                "    /  \\  | |    | |__) /  \\ | |       /  \\   \n" +
                "   / /\\ \\ | |    |  ___/ /\\ \\| |      / /\\ \\  \n" +
                "  / ____ \\| |____| |  / ____ \\ |____ / ____ \\ \n" +
                " /_/    \\_\\______|_| /_/    \\_\\_____/_/    \\_\\\n" +
                "                                              \n" +
                "                                              ";

        private static final String HORIZONTAL_LINE =  "_____________" +
                "_______________________________________________\n";

        private static final String GREET = "Baa-baa-baa, I'm Alpaca!\n"
                + "How can I assist you today?\n";
        private static final String EXIT = "Bye. Hope to see you again soon, baa-baa-baa!\n";

        public static void main(String[] args) {
                System.out.print(LOGO);
                System.out.print(HORIZONTAL_LINE + GREET + HORIZONTAL_LINE);

                String[] list = new String[100];
                int listCount = 0;

                String line;
                Scanner in = new Scanner(System.in);
                line = in.nextLine();

                while (!line.equals("bye")) {
                        switch (line) {
                        case "list":
                                System.out.print(HORIZONTAL_LINE);
                                for (int i = 0; i < listCount; i++) {
                                        System.out.println((i + 1) + ". " + list[i]);
                                }
                                System.out.print(HORIZONTAL_LINE);
                                break;
                        default:
                                System.out.print(HORIZONTAL_LINE);
                                System.out.print("added: ");
                                System.out.println(line);
                                System.out.print(HORIZONTAL_LINE);
                                list[listCount] = line;
                        }
                        listCount++;
                        line = in.nextLine();
                }

                System.out.print(HORIZONTAL_LINE);
                System.out.print(EXIT + HORIZONTAL_LINE);
        }
}