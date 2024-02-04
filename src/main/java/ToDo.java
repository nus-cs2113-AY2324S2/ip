public class ToDo extends Task{
    ;
    public ToDo (String description) {
        super(description);
    }

    @Override
    public void printTask () {
        System.out.println (getTypeIcon() + getStatusIcon() + " " +  this.description);
    }

    @Override
    public String getTypeIcon (){
        return "[T]";
    }
}
