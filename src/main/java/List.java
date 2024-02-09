import java.util.ArrayList;

public class List {
    private ArrayList<Task> inputList = new ArrayList<Task>();

    public void addToList(String input, TaskType type) {
        Task newTask;
        if (type == TaskType.EVENT) {
            int startTimeIndex = input.indexOf("/from");
            String description = input.substring(0, startTimeIndex).strip();
            String startTime = input.substring(startTimeIndex + "/from ".length()).strip();
            startTime = startTime.replace("/", "");
            newTask = new Event(description, startTime);
        } else if (type == TaskType.DEADLINE) {
            int timeIndex = input.indexOf("/by");
            String description = input.substring(0, timeIndex).strip();
            String time = input.substring(timeIndex + "/by ".length()).strip();
            newTask = new Deadline(description, time);
        } else if (type == TaskType.TODO) {
            newTask = new Todo(input);
        } else {
            newTask = new Task(input);
        }
        inputList.add(newTask);

    }
    public String getList() {
        String output = "";
        for (int i = 1; i <= inputList.size(); i++) {
            output +=  i  + ". " + getTask(i) + "\n";
        }
        return output;
    }

    public String getTask(int n) {
        String output = inputList.get(n - 1).getFullDescription();
        return output;
    }

    public void markAsDone(int n, boolean isDone) {
        inputList.get(n - 1).setIsDone(isDone);
    }

    public int listSize() {
        return inputList.size();
    }


}
