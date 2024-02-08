import java.util.Objects;

public class EventTask extends Task{
    private String startTime, endTime;
    public EventTask(String nameWithDates, Boolean isDone) {
        StringBuilder startTime = new StringBuilder();
        StringBuilder endTime = new StringBuilder();
        StringBuilder name = new StringBuilder();
        String[] words = nameWithDates.split(" ");
        /*
          state:
          0 means currently concatenating name
          1 means currently concatenating startTime
          2 means currently concatenating endTime
         */
        int state = 0;
        for(String word : words) {
            if (Objects.equals(word, "/from")) {
                state += 1;
            }
            else if (Objects.equals(word, "/to")) {
                state += 1;
            }
            else {
                switch (state) {
                case (0):
                    name.append((name.length() == 0) ? "" : " ").append(word);
                    break;
                case (1):
                    startTime.append((startTime.length() == 0 ? "" : " ")).append(word);
                    break;
                case (2):
                    endTime.append((endTime.length() == 0 ? "" : " ")).append(word);
                    break;
                }
            }
        }
        setName(name.toString());
        setIsDone(isDone);
        setTaskType(2);
        this.startTime = startTime.toString();
        this.endTime = endTime.toString();
    }
    public void printTask() {
        System.out.println("[E][" + (getIsDone() ? "X" : " ") + "] " + getName() + " (from: " + startTime + " to: " + endTime + ")");
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getEndTime() {
        return endTime;
    }
}
