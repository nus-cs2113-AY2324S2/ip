public class DeadlineTask extends Task{
    protected static String deadline;

    public DeadlineTask(boolean status, String description) {
        this.isDone = status;
        this.description = setDeadline(description);
        this.type = "D";
    }

    /**
     * setDeadline parse argument of inputted query,
     * get the deadline,
     * assign it to the object
     *
     * @param argument
     * @return description of deadline without the deadline
     */
    public String setDeadline(String argument){
        // find index of '/'
        int index = argument.indexOf('/');
        deadline = argument.substring(index + 4);
        return argument.substring(0, index - 1);
    }

    public String getDeadline() {
        return deadline;
    }

    @Override
    public void printTask() {
        if (isDone) {
            System.out.println("[" + this.type + "][X] " + this.getDescription() + " (by: " + this.getDeadline() + " )");
        } else {
            System.out.println("[" + this.type + "][ ] " + this.getDescription() + " (by: " + this.getDeadline() + " )");
        }
    }
}
