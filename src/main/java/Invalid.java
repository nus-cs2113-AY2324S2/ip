public class Invalid extends Task{
    public Invalid(String name) {
        super(name);
        this.taskType = TaskType.INVALID;
    }

    //return the error message
    public String toString(){
        return name;
    }
}
