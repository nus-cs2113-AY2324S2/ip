package Utils;

/**
 * Represents the Parser. A <code>Parser</code> object corresponds to
 * a Parser use to separate the input command
 */
public class Parser {
    private String type = null;
    private String taskDate = null;
    private String taskDescription = null;
    private String number = null;
    public Parser(String instruction) throws IndexOutOfBoundsException{
        String[] splitInstruction = instruction.split(" ", 2);
        this.type = splitInstruction[0];

        if(splitInstruction.length>1){
            this.number = splitInstruction[1];
        }

        if (instruction.startsWith("deadline")) {
            this.type = "deadline";
            this.taskDescription = splitInstruction[1].split("/", 2)[0];
            String task_date = splitInstruction[1].split("/", 2)[1];
            this.taskDate = task_date.replace("by ", "");
        } else if (instruction.startsWith("event")) {
            this.type = "event";
            String task_date = splitInstruction[1].split("/", 2)[1];
            this.taskDescription = splitInstruction[1].split("/", 2)[0];
            this.taskDate = task_date.replace("/", "");
            this.taskDate = task_date.replace("from ", "");
        } else if (instruction.startsWith("todo")) {
            this.type = "todo";
            this.taskDescription = splitInstruction[1];
        }
    }

    public String getNumber() {
        return number;
    }

    public String getTaskDate() {
        return taskDate;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public String getType() {
        return type;
    }

}
