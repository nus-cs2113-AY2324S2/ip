package format;

/**
 * Format the output
 */
public class Formatter {
    public void printDividingLine() {
        System.out.println("\t__________________________________________________");
    }

    public void printFunctionality() {
        System.out.println("\t\t1. Type \"todo\" + \"TASKCONTENT\" to record a 'todo' task.");
        System.out.println("\t\t2. Type \"deadline\" + \"TASKCONTENT\" + \"/by\" + \"DATE + TIMES\" to" +
                " record a 'deadline' task.");
        System.out.println("\t\t3. Type \"event\" + \"TASKCONTENT\" + \"/from\" + \"DATE + TIMES\" + " +
                "\"/to\" + \"DATE + TIMES\" to record a 'event' task.");
        System.out.println("\t\t4. Type \"list\" to see what I have recorded.");
        System.out.println("\t\t5. Type \"mark\" + \"NUMBER\" to mark tasks as done.");
        System.out.println("\t\t6. Type \"unmark\" + \"NUMBER\" to mark tasks as not done.");
        System.out.println("\t\t7. Type \"delete\" + \"NUMBER\" to delete tasks.");
        System.out.println("\t\t8. Type \"find\" + \"TASKCONTENT\" to find tasks");
        System.out.println("\t\t9. Type \"find\" + \"/at\" + \"DATE\" to find tasks");
        System.out.println("\t\t10. Type \"bye\" to say goodbye to me.");
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
