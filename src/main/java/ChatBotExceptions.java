public class ChatBotExceptions extends Exception{

    public ChatBotExceptions(String message){

        super("ERROR: " + message);
    }
}