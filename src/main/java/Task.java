
class Task {
    private boolean status = false;
    private final String description;
    private final String type;
    public Task(String description, String type) {
        this.description = description;
        this.type = type;
    }
    public String getDescription(){
        return description;
    }
    public String getType(){
        return type.toUpperCase();
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
    }

}


