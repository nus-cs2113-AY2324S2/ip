import java.util.ArrayList;

public class List {
    private ArrayList<Task> inputList = new ArrayList<Task>();

    public void setInputList(String input) {
        Task newTask = new Task(input);
        inputList.add(newTask);
    }
    public String getInputList() {
        String output = "";
        for (int i = 0; i < inputList.size(); i++) {
            output += i+1 + ". " + getItemFromList(i + 1);
        }
        return output;
    }

    public String getItemFromList(int n) {
        String output = "[";
        if (inputList.get(n-1).getIsDone()) {
            output += "X] ";
        }
        else {
            output += "] ";
        }
        output += inputList.get(n-1).getDescription() + "\n";
        return output;
    }

    public void markAsDone(int n, boolean isDone) {
        inputList.get(n-1).setAsDone(isDone);
    }

    public int listSize() {
        return inputList.size();
    }
}
