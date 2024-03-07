package interactions;
import customexceptions.IncompletePromptException;
import customexceptions.UnknownPromptException;
import tasks.TaskList;

/** Handles interface between user and UI chatbot */
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

    /** Opens discussion with the user by introducing itself. */
    public void greet() {
        System.out.println(name + ": Hello! I'm " + name + "!");
        System.out.println(name + ": What can I do for you?");
    }

    /**
     * Determines whether the keyword inputted is a typo or not, then suggests and returns the correct spelling.
     *
     * @param word The word in question that may or may not be a typo.
     * @return Correct spelling of the word.
     */
    public boolean isTypo(String word) {
        for (String instruction : INSTRUCTIONS) {
            if (!instruction.equals(word) && instruction.startsWith(word)) {
                System.out.println("Did you mean: " + instruction + "?");
                return true;
            }
        }
        return false;
    }

    /**
     * Shuts down the discussion between user and UI chatbot.
     */
    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
