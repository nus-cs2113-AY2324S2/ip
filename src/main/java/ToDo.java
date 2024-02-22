public class Todo extends Task {
    public Todo (String description){
        super(description);
    }

    // Prints task
    public void printTask(){
        System.out.print("[T][");

        System.out.println("] " + description);
    }
}