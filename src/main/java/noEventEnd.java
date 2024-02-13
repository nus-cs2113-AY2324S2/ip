public class noEventEnd extends EventException{
    public void errorMessage(){
        System.out.println("No end date mentioned! After your /from {date}, type /to {date}");
    }
}
