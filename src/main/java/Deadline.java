public class Deadline extends Task{

    protected String deadline;

    public Deadline(){
        super(null);
    }

    public Deadline(String line){
        super(line);
        taskType = "D";
        int dividerIndex = line.indexOf("/by");
        if(dividerIndex == -1){
            setTaskName(line);
            setDeadline(null);
            return;
        }
        String deadline = line.substring(dividerIndex + 4);
        setDeadline(deadline);
        taskName = line.substring(0, dividerIndex - 1);
        setTaskName(taskName);
    }

    public String getDeadline(){
        return deadline;
    }

    public void setDeadline(String deadline){
        this.deadline = deadline;
    }

    @Override
    public String toString(){
        return String.format("[%s][%s] %s (by %s)", taskType, doneSymbol, taskName, deadline);
    }

}
