public class ToDo extends Task{

    public ToDo(String task) {
        super(task);
    }

    @Override
    public String taskType(){
        return "[T]";
    }
}
