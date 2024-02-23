public class DeadlineTask extends Task{
    protected static String deadline;


    public DeadlineTask() throws SalmonMissingArgument{
        this(false, "DEFAULT /by DEFAULT");
    }

    public DeadlineTask(boolean status, String description) throws SalmonMissingArgument{
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
     * @return String description of deadlineTask
     */
    public String setDeadline(String argument) throws SalmonMissingArgument {
        if (argument == null) {
            throw new SalmonMissingArgument();
        }
        // find index of '/'
        int index = argument.indexOf('/');
        deadline = argument.substring(index + 4);
        return argument.substring(0, index - 1);
    }

    /**
     *
     * @return String containing deadline of task
     */
    public String getDeadline() {
        return deadline;
    }

    @Override
    public void printTask() {
        if (isDone) {
            System.out.println("[" + this.type + "][X] " + this.getDescription() + " (by: " + this.getDeadline() + ")");
        } else {
            System.out.println("[" + this.type + "][ ] " + this.getDescription() + " (by: " + this.getDeadline() + ")");
        }
    }

    @Override
    public String toString() {
        String answer;
        answer = this.type + " | " + this.isDone + " | " + this.description + " | " + this.deadline;
        return answer;
    }

    @Override
    public void parse(String s) throws SalmonMissingArgument {
        // format T | true | description
        int firstSlash = s.indexOf('|');
        int secondSlash = s.indexOf('|', firstSlash + 1);
        int thirdSlash = s.indexOf('|', secondSlash + 1);
        int spaceAfterIsDone = s.indexOf(' ', firstSlash + 2);

        String isDoneStatus = s.substring(firstSlash + 2, spaceAfterIsDone);
        String description = s.substring(secondSlash + 2, thirdSlash - 1);
        String deadline = s.substring(thirdSlash + 2);

        this.setBoolean(Boolean.parseBoolean(isDoneStatus));
        this.description = description;
        this.deadline = deadline;
    }
}
