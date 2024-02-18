public class Todo extends Task{

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString(){
        return ("[T]" + super.toString());
    }

    @Override
    public void printTask (int numberOfListItems){
        System.out.println("Got it! I've added this task:\n" + this.toString());
        System.out.println("Now you have " + (numberOfListItems + 1) + " tasks in the list!");
    }

}
