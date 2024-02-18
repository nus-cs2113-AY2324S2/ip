public class JeffException {

    public static class InvalidInputException extends Exception {
        public InvalidInputException(String message) {
            super(message);
        }
    }

    public static class InvalidKeywordException extends Exception {
        public InvalidKeywordException(String message) {
            super(message);
        }
    }

}


