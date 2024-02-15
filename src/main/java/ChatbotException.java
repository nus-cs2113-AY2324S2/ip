public class ChatbotException extends Exception{
    private final String description;
    public ChatbotException(String description) {
        this.description = description;
    }
    public void printDescription() {
        System.out.println(description);
    }
}
