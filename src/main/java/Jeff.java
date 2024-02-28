public class Jeff {
    public static void main(String[] args) throws JeffException.InvalidKeywordException {
        Ui userInterface = new Ui();
        TaskList list = new TaskList();
        Storage saveInstance = new Storage(list.getTasks(), userInterface);
        Logic processor = new Logic(list, saveInstance);


        list.generateList(userInterface);
        Storage.deserializeTasks();
        list.setTasks(saveInstance.getSavedList());
        userInterface.greetingMessage();


        boolean isChatting = true;
            while (isChatting) {
                try{
                String userInput = Parser.getUserInput();
                String userFirstWord = Parser.getFirstWord(userInput);

                switch (userFirstWord) {
                    case "list":
                        list.listTasks(list.getTasks());
                        break;
                    case "bye":
                        isChatting = false;
                        saveInstance.uploadTasks();
                        break;
                    case "mark":
                        processor.markLogic(userInput);
                        break;
                    case "unmark":
                        processor.unmarkLogic(userInput);
                        break;
                    case "deadline":
                        processor.deadlineLogic( userInput);
                        break;
                    case "event":
                        processor.eventLogic(userInput);
                        break;
                    case "todo":
                        processor.todoLogic(userInput);
                        break;
                    case "remove":
                        processor.removeLogic(userInput);
                        break;
                    default:
                        throw new JeffException.InvalidKeywordException("");
                }
            } catch (JeffException.InvalidKeywordException e) {
                    throw new JeffException.InvalidKeywordException("SORRY I DONT UNDERSTAND THAT KEYWORD PLEASE" +
                            " USE todo, deadline or event only. THANKS");
            }
        }
            userInterface.goodbyeMessage();
    }
}
