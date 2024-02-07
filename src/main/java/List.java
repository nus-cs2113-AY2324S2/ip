import java.util.ArrayList;

public class List {
    private ArrayList<Task> inputList = new ArrayList<Task>();

    public void addToList(String input) {
        Task newTask = new Task(input);
        inputList.add(newTask);
    }
    public String getList() {
        String output = "";
        for (int i = 1; i <= inputList.size(); i++) {
            output +=  i + ". " + getTask(i);
        }
        return output;
    }

    public String getTask(int n) {
        String output = "[";
        if (inputList.get(n - 1).getIsDone()) {
            output += "X] ";
        }
        else {
            output += "] ";
        }
        output += inputList.get(n - 1).getDescription() + "\n";
        return output;
    }

    public void markAsDone(int n, boolean isDone) {
        inputList.get(n - 1).setIsDone(isDone);
    }

    public int listSize() {
        return inputList.size();
    }
}
