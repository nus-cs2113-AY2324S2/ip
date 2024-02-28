public class Logic {
    private final TaskList list;
    private final Storage saveInstance;
    public Logic(TaskList currentList, Storage storageInstance) {
        this.list = currentList;
        this.saveInstance = storageInstance;
    }


    public void markLogic(String userInput){
        int taskIndex = Parser.getFirstInt(userInput);
        list.markIndex(taskIndex);
        saveInstance.setSavedList(list.getTasks());
        saveInstance.uploadTasks();
    }


    public void unmarkLogic(String userInput){
        int taskIndex = Parser.getFirstInt(userInput);
        list.unmarkIndex(taskIndex);
        saveInstance.setSavedList(list.getTasks());
        saveInstance.uploadTasks();
    }


    public void deadlineLogic(String userInput){
        String description = Parser.extractDescription(userInput);
        String deadLine = Parser.extractStartTime(userInput);
        list.insertTask(new Deadline(description, deadLine, false));
        saveInstance.setSavedList(list.getTasks());
        saveInstance.uploadTasks();
    }


    public void eventLogic(String userInput){
        String description = Parser.extractDescription(userInput);
        String start = Parser.extractStartTime(userInput);
        String end = Parser.extractEndTime(userInput);
        list.insertTask(new Event(description, start, end, false));
        saveInstance.setSavedList(list.getTasks());
        saveInstance.uploadTasks();
    }


    public void todoLogic(String userInput){
        list.insertTask(new Todo(Parser.extractDescription(userInput), false));
        saveInstance.setSavedList(list.getTasks());
        saveInstance.uploadTasks();
    }


    public void removeLogic(String userInput){
        int taskIndex = Parser.getFirstInt(userInput);
        list.deleteIndex(taskIndex);
        saveInstance.setSavedList(list.getTasks());
        saveInstance.uploadTasks();
    }
}
