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

        private static final String GREET = "Hello! I'm Alpaca\n"
                + "What can I do for you?\n";
        private static final String EXIT = "Bye. Hope to see you again soon!\n";

        public static void main(String[] args) {
                System.out.print(LOGO);
                System.out.print(HORIZONTAL_LINE + GREET + HORIZONTAL_LINE);
                System.out.print(EXIT + HORIZONTAL_LINE);
        }
}