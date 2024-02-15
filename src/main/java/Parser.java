public class Parser {
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
    public Task addTask(String task) throws PythiaException{
        String[] parsedTask;
        if (task.contains("todo ")) {
            task = task.replaceFirst("todo", "");
            if (task.isBlank()) {
                throw new PythiaException();
            }
            return new Todo(task);
        } else if (task.contains("deadline ")) {
            parsedTask = parseDeadline(task);
            if (parsedTask.length < 2) {
                throw new PythiaException();
            }
            return new Deadline(parsedTask[0], parsedTask[1]);
        } else if (task.contains("event ")){
            parsedTask = parseEvent(task);
            if (parsedTask.length < 3) {
                throw new PythiaException();
            }
            return  new Event(parsedTask[0], parsedTask[1], parsedTask[2]);
        } else {
            throw new PythiaException();
        }
    }

    public boolean isTaskCommand(String task) {
        return (task.contains("todo") | task.contains("deadline") | task.contains("event"));
    }
}
