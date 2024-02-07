public class ToDo extends Task{

    public ToDo(String description) {
        super(description.substring(5));
        this.type = "T";
    }


}
