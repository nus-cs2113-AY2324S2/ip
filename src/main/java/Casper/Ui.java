package Casper;


/**
 * Handles all functionalities that relate to the user interface.
 */
public class Ui {
    private static final String SEPARATOR = "    _______________________________________________________________________";

    public Ui(){};

    /**
     * Prints the line separator.
     */
    public void printLine(){
        System.out.println(SEPARATOR);
    }

    /**
     * Prints the message separated by bounding lines (general use).
     */
    public void wrapEchoMessage(String message){
        printLine();
        System.out.println("     "+message);
        printLine();
    }

    /**
     * Prints Casper's greeting message.
     */
    public void echoGreetings(){
        String logo = "       ___ __ _ ___ _ __   ___ _ __\n"
                + "      / __/ _` / __| '_ \\ / _ \\ '__|\n"
                + "     | (_| (_| \\__ \\ |_) |  __/ |   \n"
                + "      \\___\\__,_|___/ .__/ \\___|_|   \n"
                + "                   | |              \n"
                + "                   |_|               ";
        System.out.println("    Starting\n" + logo);
        wrapEchoMessage("Boo! I'm Casper!\n     What can I do for you?");
    }

    /**
     * Prints an exception message when an unrecognized keyword is received.
     *
     * @param keywordList A <code>String[]</code> consisting of valid keywords.
     * @param e A CasperUnrecognizedKeywordException instance.
     */
    public void unrecognizedKeywordException(String[] keywordList, CasperUnrecognizedKeywordException e){
        System.out.println(SEPARATOR);
        System.out.println("     Pardon? I didn't quite understand \""+ e.getUnrecognizedKeyword()+"\"");
        System.out.println("     Maybe refer to the following list of commands?");
        for(String keyword:keywordList){
            System.out.println("      - " + keyword);
        }
        System.out.println(SEPARATOR);
    }


    /**
     * Prints a message indicating the <code>Todo</code> syntax was invalid.
     */
    public void echoUnrecognizedTodo(){
        wrapEchoMessage("What did you want to do? Try again?\n\n"
                +"     Make sure its in the format: \n"
                +"       todo {description}");
    }

    /**
     * Prints a message indicating the <code>Deadline</code> syntax was invalid.
     */
    public void echoUnrecognizedDeadline(){
        wrapEchoMessage("A deadline to what? Try again?\n\n"
                +"     Make sure its in the format: \n"
                +"       deadline {description} /by {date}");
    }

    /**
     * Prints a message indicating the <code>Event</code> syntax was invalid.
     */
    public void echoUnrecognizedEvent(){
        wrapEchoMessage("What event did you say? Try again?\n\n"
                +"     Make sure its in the format: \n"
                +"       event {description} /from {date} /to {date}");
    }

    /**
     * Prints a message indicating the task was successfully removed.
     *
     * @param taskToRemove The <code>Task</code> to be removed.
     * @param noOfTasks An integer representing the number of current tasks after deleting.
     */
    public void echoDeleteTask(Task taskToRemove, int noOfTasks){
        wrapEchoMessage("Nicely done!. It's about time you get things done: \n       "
                +taskToRemove+"\n     Now you have "+noOfTasks+" task(s) in the list");
    }

    /**
     * Prints a message indicating the task was successfully added.
     *
     * @param newTask The <code>Task</code> to be added.
     * @param noOfTasks An integer representing the number of current tasks after adding.
     */
    public void echoAddedTask(Task newTask, int noOfTasks){
        wrapEchoMessage("Got it. I've added this task: \n       "
                +newTask+"\n     Now you have "+noOfTasks+" tasks in the list");
    }
}
