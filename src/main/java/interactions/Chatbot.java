package interactions;
import customexceptions.*;

public class Chatbot {
    // Sorted instructions
    public static final String[] INSTRUCTIONS = {"bye", "deadline", "delete", "event", "list",
            "mark", "rename", "todo"};
    public String getName() {
        return name;
    }
    private String name;
    public Chatbot() {
        name = "MOBY";
    }
    public void rename(String line) {
        String newName = line.substring(7);
        this.name = newName.toUpperCase();
        System.out.println("Renamed chatbot to " + this.name);
    }
    public void greet() { 
        System.out.println(name + ": Hello! I'm " + name + "!");
        System.out.println(name + ": What can I do for you?");
    }
    public int parseIndex(String line, String keyword) {
        int len = keyword.length();
        return Integer.parseInt(line.substring(len + 1));
//        try {
//            return Integer.parseInt(line.substring(len)) + 1;
//        } catch (NumberFormatException e) {
//            return -1;
//        }
    }
    public void echo(String line) {
        System.out.println(line);
    }
    public boolean isTypo(String line) { // identifies and deals with typo
        String[] words = line.split(" ");
        String typo = words[0];
        int typoSize = typo.length();
        for (String instruction : INSTRUCTIONS) {
            if (!instruction.equals(typo) && instruction.startsWith(typo)) {
                System.out.println("Did you mean: " + instruction + "?");
                return true;
            }
        }
        return false;
    }
    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
