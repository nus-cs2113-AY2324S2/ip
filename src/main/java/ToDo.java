public class ToDo extends Task{
    public ToDo(String description, int arrayIndex) {
        super(description, arrayIndex);
        this.taskType = Type.ToDo;
    }

    @Override
    public String toString() {
        return this.getIndex()+ ". [T] ["+ this.getCheckMark()+"] " +this.getDescription();
    }
}
