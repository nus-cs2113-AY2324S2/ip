public class Todo {

    Boolean isDone;
    String description;
    String Icon="T";
    public Todo(boolean isDone, String description) {
        this.isDone = isDone;
        this.description = description;
    }

    public void SetisDone(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public String toString(){
        String DoneIcon = isDone? "x":" ";
        return "["+Icon+"]"+"["+DoneIcon+"]"+ description;
    }
}
