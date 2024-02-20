package format;

public class Formatter {
    public void printDividingLine() {
        System.out.println("\t__________________________________________________");
    }

    public void printFunctionality() {
        System.out.println("\tI have the following features:");
        System.out.println("\t\t1. Echo and store three types of tasks: 'todo', 'deadline' and 'event'.");
        System.out.println("\t\t2. Type \"todo\" + \"taskContent\" to record a 'todo' task.");
        System.out.println("\t\t3. Type \"deadline\" + \"taskContent\" + \"/by\" + \"dates/times\" to" +
                " record a 'deadline' task.");
        System.out.println("\t\t4. Type \"event\" + \"taskContent\" + \"/from\" + \"dates/times\" + " +
                "\"/to\" + \"dates/times\" to record a 'event' task.");
        System.out.println("\t\t5. Type \"list\" to see what I have recorded.");
        System.out.println("\t\t6. Type \"mark\" + \"number\" to mark tasks as done.");
        System.out.println("\t\t7. Type \"unmark\" + \"number\" to mark tasks as not done.");
        System.out.println("\t\t8. Type \"bye\" to say goodbye to me.");
    }

    public String generateLogo() {
        return
                " ___     _   ___   ___   __\n"
                        + "|   \\   | | |   | |   | |  |\n"
                        + "| |\\ \\  | | | | | | | | |  |__\n"
                        + "| | \\ \\_| | | | | | | | | ___ |\n"
                        + "|_|  \\____| |___| |___| |_____|\n";
    }
}
