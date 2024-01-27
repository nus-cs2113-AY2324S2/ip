public class Task {

    String Name;

    public Task() {
        Name = "Unnamed";
    }

    public Task(String input) {
        this.Name = input;
    }

    public String getName() {
        return this.Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

}
