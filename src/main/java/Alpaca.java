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
                String tempLine;

                while (!line.equals("bye")) {

                        if (line.equals("list")) {
                                System.out.print(HORIZONTAL_LINE);
                                System.out.println("Here are the tasks in your list:");
                                for (int i = 0; i < listCount; i++) {
                                        System.out.println((i + 1) + "." + list[i]);
                                }
                                System.out.print(HORIZONTAL_LINE);
                        }
                        else if (line.length() >= 4 && line.substring(0, 4).equals("mark")) {
                                tempLine = line.substring(5);
                                int index = Integer.parseInt(tempLine) - 1;
                                list[index] = list[index].replace("[ ]", "[X]");
                                System.out.print(HORIZONTAL_LINE);
                                System.out.println("Nice! I've marked this task as done:");
                                System.out.println(list[index]);
                                System.out.print(HORIZONTAL_LINE);
                        }
                        else if (line.length() >= 6 && line.substring(0, 6).equals("unmark")) {

                                tempLine = line.substring(7);
                                int index = Integer.parseInt(tempLine) - 1;
                                list[index] = list[index].replace("[X]", "[ ]");
                                System.out.print(HORIZONTAL_LINE);
                                System.out.println("OK, I've marked this task as not done yet:");
                                System.out.println(list[index]);
                                System.out.print(HORIZONTAL_LINE);
                        }
                        else {
                                System.out.print(HORIZONTAL_LINE);
                                System.out.print("added: ");
                                System.out.println(line);
                                System.out.print(HORIZONTAL_LINE);
                                tempLine = "[ ] " + line;
                                list[listCount] = tempLine;
                                listCount++;
                        }
                        line = in.nextLine();
                }

                System.out.print(HORIZONTAL_LINE);
                System.out.print(EXIT + HORIZONTAL_LINE);
        }
}