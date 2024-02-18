
public class Jeff {
    public static void main(String[] args) throws JeffException.InvalidKeywordException {
        List list = new List();
        list.generateSizeOfList(100);
        int taskIndex;
        String description;

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
                        break;

                    case "mark":
                        taskIndex = Parser.getFirstInt(userInput);
                        list.markIndex(taskIndex);
                        break;

                    case "unmark":
                        taskIndex = Parser.getFirstInt(userInput);
                        list.unmarkIndex(taskIndex);
                        break;

                    case "deadline":
                        description = Parser.extractDescription(userInput);
                        String deadLine = Parser.extractStartTime(userInput);
                        list.insertTask(new Deadline(description, deadLine));
                        break;


                    case "event":
                        description = Parser.extractDescription(userInput);
                        String start = Parser.extractStartTime(userInput);
                        String end = Parser.extractEndTime(userInput);
                        list.insertTask(new Event(description, start, end));
                        break;


                    case "todo":
                        list.insertTask(new Todo(Parser.extractDescription(userInput)));
                        break;

                    case "remove":
                        taskIndex = Parser.getFirstInt(userInput);
                        list.deleteIndex(taskIndex);
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
