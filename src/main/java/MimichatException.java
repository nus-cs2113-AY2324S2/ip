public class MimichatException {

    // For input format exceptions
    public static class InvalidInputFormatException extends Exception {
        public InvalidInputFormatException(){
            super();
        }

        public InvalidInputFormatException(String message){
            super(message);
        }
    }


}
