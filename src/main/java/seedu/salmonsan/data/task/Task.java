package seedu.salmonsan.data.task;

import seedu.salmonsan.data.exception.SalmonMissingArgument;

public class Task {
    protected boolean isDone;
    protected String description;
    protected String type;

    public Task() throws SalmonMissingArgument {
        this(false,"");
    }

    public Task(boolean status, String s) throws SalmonMissingArgument {
        this.isDone = status;
        this.setDescription(s);
        this.type = "T";
    }

    public void setDescription(String description) throws SalmonMissingArgument {
        if (description==null) {
            throw new SalmonMissingArgument();
        }
        this.description = description;
    }

    public void setBoolean(boolean status) {
        this.isDone = status;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     *
     * @return boolean isDone of seedu.salmonsan.data.task.Task
     */
    public boolean getStatus() {
        return this.isDone;
    }

    /**
     *
     * @return String description of seedu.salmonsan.data.task.Task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * print the task in proper format
     */
    public void printTask() {
        if (isDone) {
            System.out.println("[" + this.type + "][X] " + this.getDescription());
        } else {
            System.out.println("[" + this.type + "][ ] " + this.getDescription());
        }
    }

    /**
     * convert task into a string and returns it
     * @return
     */
    public String toString() {
        String answer;
        answer = this.type + " | " + this.isDone + " | " + this.description;
        return answer;
    }

    /**
     * parse string from .txt file into a task
     * @param s
     * @throws SalmonMissingArgument
     * @throws StringIndexOutOfBoundsException
     */
    public void parse(String s) throws SalmonMissingArgument, StringIndexOutOfBoundsException {
        // format T | true | description
        int firstSlash = s.indexOf('|');
        int secondSlash = s.indexOf('|', firstSlash + 1);
        int spaceAfterIsDone = s.indexOf(' ', firstSlash + 2);

        String isDoneStatus = s.substring(firstSlash + 2, spaceAfterIsDone);
        String description = s.substring(secondSlash + 2);

        description.replaceAll("\\s+","");

        this.setBoolean(Boolean.parseBoolean(isDoneStatus));
        this.setDescription(description);
    }
}
