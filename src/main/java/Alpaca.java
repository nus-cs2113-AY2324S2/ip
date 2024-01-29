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
        private static final String EXIT = "Bye. Hope to see you again soon, baa-baa-baa\n";

        public static void main(String[] args) {
                System.out.print(LOGO);
                System.out.print(HORIZONTAL_LINE + GREET + HORIZONTAL_LINE);

                String line;
                Scanner in = new Scanner(System.in);
                line = in.nextLine();

                while (!line.equals("bye")) {
                        System.out.print(HORIZONTAL_LINE);
                        System.out.println(line);
                        System.out.print(HORIZONTAL_LINE);
                        line = in.nextLine();
                }

                System.out.print(HORIZONTAL_LINE);
                System.out.print(EXIT + HORIZONTAL_LINE);
        }
}