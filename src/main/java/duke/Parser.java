package duke;

public class Parser {
    private Storage storage = new Storage();

    public void parseInput(String input) throws PythiaException {
        if (input.equalsIgnoreCase("list")) {
            Command.list();
        } else if (isTaskCommand(input)) {
            Command.tryAddTask(input);
        } else if (input.contains("unmark ")) {
            Command.unmark(input);
        } else if (input.contains("mark ")) {
            Command.mark(input);
        } else if (input.contains("delete ")) {
            Command.delete(input);
        } else if (input.contains("find ")) {
            Command.find(input);
        } else {
            throw new PythiaException();
        }
        storage.trySaveList();
        System.out.println(MoodSprite.getLineBreak());
    }
    public String[] splitTaskTrimmer(String[] splitTask) {
        for (int i = 0; i < splitTask.length; i++) {
            splitTask[i] = splitTask[i].trim();
        }
        return splitTask;
    }
    public String[] parseDeadline(String task) {
        task = task.replaceFirst("deadline", "");
        return splitTaskTrimmer(task.split("/"));
    }

    public String[] parseEvent(String task) {
        task = task.replaceFirst("event", "");
        return splitTaskTrimmer((task.split("/")));
    }

    public boolean isTaskCommand(String task) {
        return (task.contains("todo") | task.contains("deadline") | task.contains("event"));
    }
}
