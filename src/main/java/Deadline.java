public class Deadline extends Task{
    private String deadline;
    public Deadline(String description, String deadline){
        super(description + String.format("(by:%s)",deadline));
    }
    @Override
    public String taskType(){
        return "[D]";
    }
}
