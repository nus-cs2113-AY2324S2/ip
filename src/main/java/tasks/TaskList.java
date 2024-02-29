package tasks;
public class TaskList {
    protected String description;
    public boolean isDone;

    public TaskList(String description) {
        setDescription(description);
        setDone();
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDone() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public static void printHeaders() {
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task:");
    }


    /* Returns the type of task and their action. */
    public String toString() {
        String[] userInput = description.split("\\s+");
        String[] firstHalf;

        // Split based on the task that the user wants.
        if (userInput[0].equals("event")) {
            firstHalf = description.split("/from");
        }
        else {
            firstHalf = description.split("/by");
        }

        String value = String.valueOf(firstHalf[0]); // Convert the array element to a string.

        String[] splitLine = value.split("\\s+"); // Split the first half into array elements again, by whitespaces.
        StringBuilder action = new StringBuilder();

        // Obtain the action of the task.
        for (int i = 1; i < splitLine.length; i += 1) {
            action.append(splitLine[i]).append(" ");
        }
        return "[" + this.getStatusIcon() + "] " + action;
    }
}