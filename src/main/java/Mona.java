public class Mona {

    public static void greet() {
        for (int i = 0; i < 59; i++) {
            System.out.print("_");
        }
        System.out.println("_");

        System.out.println("Hello! I'm Mona");
        System.out.println("What can I do for you?");

        for (int i = 0; i < 59; i++) {
            System.out.print("_");
        }
        System.out.println("_");
    }

    public static void exit() {
        for (int i = 0; i < 59; i++) {
            System.out.print("_");
        }
        System.out.println("_");

        System.out.println("Bye. Hope to see you again soon!");

        for (int i = 0; i < 59; i++) {
            System.out.print("_");
        }
        System.out.println("_");
    }
    public static void main(String[] args) {
        String logo = "___  ___                  \n"
                + "|  \\/  |                  \n"
                + "| .  . | ___  _ __   __ _ \n"
                + "| |\\/| |/ _ \\| '_ \\ / _` |\n"
                + "| |  | | (_) | | | | (_| |\n"
                + "\\_|  |_/\\___/|_| |_|\\__,_|\n";
        System.out.println("Hello from\n" + logo);

        greet();

        exit();
    }
}
