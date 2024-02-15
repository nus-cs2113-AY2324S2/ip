<<<<<<< HEAD

public abstract class Task {
    protected static final String MARKED = "[X]";
    protected static final String UNMARKED = "[ ]";
    protected boolean isMarked;
    protected String description;

    public Task(String description) {
        this.description = description;
        this.isMarked = false;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        String icon = isMarked ? MARKED : UNMARKED;
        return icon + " " + description;
=======
class Task {
    private boolean status = false;
    private String description;
    public Task(String description) {
        this.description = description;
    }
    public String getDescription(){
        return description;
    }
    public boolean getStatus() {
            return status;
    }
    public void markAsComplete() {
        this.status = true;
        System.out.println("    ____________________________________________________________\n" +
                "     Great! I've marked this task as done");
        System.out.println("       [X] " + this.description + "\n");
        System.out.println("    ____________________________________________________________\n");
    }
    public void markAsIncomplete() {
        this.status = false;
        System.out.println("    ____________________________________________________________\n" +
                "     Aights! I've marked this task as not done");
        System.out.println("       [ ] " + this.description + "\n");
        System.out.println("    ____________________________________________________________\n");
>>>>>>> 5a419c558feda1f16aac0891899331edea92bc9f
    }
}


