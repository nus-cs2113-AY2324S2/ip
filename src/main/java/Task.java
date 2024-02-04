public class Task {

    protected String name;
    protected boolean isCompleted = false;

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

    @Override
    public String toString() {
        if (isCompleted) {
            return String.format("%s %s", "[X]", this.name);
        } else {
            return String.format("%s %s", "[ ]", this.name);
        }

    }

    public void printName() {
        System.out.print(this.name);
    }

}
