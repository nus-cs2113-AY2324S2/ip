public class Deadline extends Todo {
    protected String endDate;

    public Deadline(String description, String endDate){
        super(description);
        this.endDate = endDate;
        this.tag = "D";
    }

    @Override
    public String toString(){
        return super.toString() + " (by: " + endDate + ")";
    }

}