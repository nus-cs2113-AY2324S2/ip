public class Task {

    private String name;
    private boolean isCompleted
 = false;

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
        return isCompleted;
    }

    public void markCompleted() {
        this.isCompleted = true;
    }

    public void markUncompleted() {
        this.isCompleted = false;
    }

    public void printBox() {
        if (isCompleted) {
            System.out.print("[X]");
        } else {
            System.out.print("[ ]");
        }
    }

    public void printName() {
        System.out.print(this.name);
    }

    public void printFull() {
        printBox();
        System.out.print(" ");
        printName();
        System.out.println();
    }

}
