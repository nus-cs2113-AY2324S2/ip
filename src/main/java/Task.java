public class Task {
    protected String description;
    protected boolean isDone;

    protected String type;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.type = "T";
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    //method that extracts the task number to mark or unmark
    public static int extractTaskNumber(String input) {
        String keyword;
        if (input.startsWith("mark")) {
            keyword = "mark";
        } else {
            keyword = "unmark";
        }
        //checks if theres even characters after mark/unmark
        if (input.length() > keyword.length()) {
            String numberString = input.substring(keyword.length()).trim();
            int taskNumber = Integer.parseInt(numberString);
            //checks if taskNumber is valid
            if (taskNumber <= 0) {
                return -1;
            } else {
                return taskNumber;
            }
        } else {
            return -1;
        }
    }


    //method that modifies whether task is done or not done, depending on keyword mark or unmark detected
    public static void modifyDoneState(int taskNumber, String input) {
        //only executes if taskNumber is valid
        if (taskNumber != -1) {
            if (input.startsWith("mark")) {
                List.markAsDone(taskNumber);
            } else {
                List.markAsNotDone(taskNumber);
            }
        }
    }


}


