public class Task {

    private String name;
    private boolean completed = false;

    public Task() {
        name = "Unnamed";
    }

    public Task(String input) {
        this.name = input;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getCompletedStatus() {
        return completed;
    }

    public void markCompleted() {
        completed = true;
    }

    public void markUncompleted() {
        completed = false;
    }

    public void printBox() {
        if (completed) {
            System.out.print("[X]");
        } else {
            System.out.print("[ ]");
        }
    }

    public void printName() {
        System.out.print(name);
    }

    public void printFull() {
        printBox();
        System.out.print(" ");
        printName();
        System.out.println();
    }

}
