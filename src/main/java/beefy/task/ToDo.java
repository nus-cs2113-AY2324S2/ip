package beefy.task;

public class ToDo extends Task{
    public ToDo(String Description){
        super(Description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toDiskFormat() {
        return "T," + (this.getStatus() ? "TRUE," : "FALSE,") + description + "\n";
    }
}
