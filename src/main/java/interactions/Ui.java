package interactions;
import customexceptions.IncompletePromptException;
import customexceptions.UnknownPromptException;
import tasks.TaskList;

/** Handles interface between user and UI chatbot */
public class Ui {
    // Sorted command instructions
    public static final String[] INSTRUCTIONS = {"bye", "deadline", "delete", "event", "find", "list",
            "mark", "rename", "todo"};
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    protected String name;

    /**
     * Boolean flag to identify whether the file which saves the list of tasks have been saved.
     * This file is called {@code file.txt} and can be found under the same directory as the
     * app, jar file or main java file.
     */
    protected boolean isTextFileSavedMentioned = false;

    public boolean isTextFileSavedMentioned() {
        return isTextFileSavedMentioned;
    }

    /**
     * Sets the boolean flag that indicates if a first instance of the saving of a task list
     * is mentioned to be true, but only once for each session.
     */
    public void setTextFileSavedMentionedTrue() {
        isTextFileSavedMentioned = true;
        System.out.println("      This task has been saved! " +
                "You may access the saved tasks in list.txt file under the same directory");
    }

    public Ui() {
        name = "MOBY";
    }

    /** Opens discussion with the user by introducing itself. */
    public void greet() {
        System.out.println(name + ": Hello! I'm " + name + "! What can I do for you?");
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
