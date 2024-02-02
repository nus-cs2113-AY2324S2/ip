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
    }
}


