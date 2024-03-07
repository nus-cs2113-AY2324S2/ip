package interactions;
import customexceptions.IncompletePromptException;
import customexceptions.UnknownPromptException;
import tasks.TaskList;

public class Ui {
    // Sorted instructions
    public static final String[] INSTRUCTIONS = {"bye", "deadline", "delete", "event", "find", "list",
            "mark", "rename", "todo"};
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    private String name;
    public Ui() {
        name = "MOBY";
    }
    public void greet() { 
        System.out.println(name + ": Hello! I'm " + name + "!");
        System.out.println(name + ": What can I do for you?");
    }
    public boolean isTypo(String word) {
        for (String instruction : INSTRUCTIONS) {
            if (!instruction.equals(word) && instruction.startsWith(word)) {
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
