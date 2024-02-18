
public class Jeff {
    public static void main(String[] args) throws JeffException.InvalidKeywordException {
        List list = new List();
        list.generateList();
        int taskIndex;
        String description;
        Saver saveInstance = new Saver(list.getTasks());
        Saver.deserializeTasks();
        list.setTasks(saveInstance.getSavedList());

        System.out.println("Hello! My name is Jeff.");
        System.out.println("What can I do for you?");


        boolean isChatting = true;
            while (isChatting) {
                try{
                String userInput = Parser.getUserInput();
                String userFirstWord = Parser.getFirstWord(userInput);


                switch (userFirstWord) {
                    case "list":
                        list.listTasks();
                        break;

                    case "bye":
                        isChatting = false;
                        saveInstance.uploadTasks();

                        break;

                    case "mark":
                        taskIndex = Parser.getFirstInt(userInput);
                        list.markIndex(taskIndex);
                        saveInstance.setSavedList(list.tasks);
                        saveInstance.uploadTasks();
                        break;

                    case "unmark":
                        taskIndex = Parser.getFirstInt(userInput);
                        list.unmarkIndex(taskIndex);
                        saveInstance.setSavedList(list.tasks);
                        saveInstance.uploadTasks();
                        break;

                    case "deadline":
                        description = Parser.extractDescription(userInput);
                        String deadLine = Parser.extractStartTime(userInput);
                        list.insertTask(new Deadline(description, deadLine, false));
                        saveInstance.setSavedList(list.tasks);
                        saveInstance.uploadTasks();
                        break;


                    case "event":
                        description = Parser.extractDescription(userInput);
                        String start = Parser.extractStartTime(userInput);
                        String end = Parser.extractEndTime(userInput);
                        list.insertTask(new Event(description, start, end, false));
                        saveInstance.setSavedList(list.tasks);
                        saveInstance.uploadTasks();
                        break;


                    case "todo":
                        list.insertTask(new Todo(Parser.extractDescription(userInput), false));
                        saveInstance.setSavedList(list.tasks);
                        saveInstance.uploadTasks();
                        break;

                    case "remove":
                        taskIndex = Parser.getFirstInt(userInput);
                        list.deleteIndex(taskIndex);
                        saveInstance.setSavedList(list.tasks);
                        saveInstance.uploadTasks();
                        break;

                    default:
                        throw new JeffException.InvalidKeywordException("");
                }
            } catch (JeffException.InvalidKeywordException e) {
                    throw new JeffException.InvalidKeywordException("SORRY I DONT UNDERSTAND THAT KEYWORD PLEASE" +
                            " USE todo, deadline or event only. THANKS");
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
