public class Helpy {
    public static void greetUser() {
        String horizontalLine = "______________________";
        String name = "░▒█░▒█░▒█▀▀▀░▒█░░░░▒█▀▀█░▒█░░▒█\n" +
                "░▒█▀▀█░▒█▀▀▀░▒█░░░░▒█▄▄█░▒▀▄▄▄▀\n" +
                "░▒█░▒█░▒█▄▄▄░▒█▄▄█░▒█░░░░░░▒█░░\n";
        System.out.println(horizontalLine
                + "\nGreetings, I am\n" + name);
        System.out.println("How can I help you?\n" + horizontalLine);
    }
    public static void goodbyeUser() {
        String horizontalLine = "______________________\n";
        System.out.println("Goodbye, see you next time!");
        System.out.print(horizontalLine);
    }
    public static void main(String[] args) {
        greetUser();
        goodbyeUser();
    }
}
