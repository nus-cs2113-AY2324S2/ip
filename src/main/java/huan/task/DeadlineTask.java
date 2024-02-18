package huan.task;

import java.util.Objects;

public class DeadlineTask extends Task{
    private String ddlTime;
    public DeadlineTask(String nameWithDate, Boolean isDone) throws Exception{
        StringBuilder ddlTime = new StringBuilder();
        StringBuilder name = new StringBuilder();
        String[] words = nameWithDate.split(" ");
        /*
          state:
          0 means currently concatenating name
          1 means currently concatenating ddlTime
        */
        int state = 0;
        for(String word : words) {
            if (Objects.equals(word, "/by")) {
                state += 1;
            }
            else {
                switch (state) {
                case (0):
                    name.append((name.length() == 0) ? "" : " ").append(word);
                    break;
                case (1):
                    ddlTime.append((ddlTime.length() == 0 ? "" : " ")).append(word);
                    break;
                }
            }
        }
        if(state != 1 || name.toString().isEmpty() || isDone.toString().isEmpty()) {
            throw new Exception();
        }
        setName(name.toString());
        setIsDone(isDone);
        setTaskType(3);
        this.ddlTime = ddlTime.toString();
    }

    public void printTask() {
        System.out.println("[D][" + (getIsDone() ? "X" : " ") + "] " + getName() + " (by: " + ddlTime + ")");
    }

    public String getDdlTime() {
        return ddlTime;
    }

    public void setDdlTime(String ddlTime) {
        this.ddlTime = ddlTime;
    }

}
