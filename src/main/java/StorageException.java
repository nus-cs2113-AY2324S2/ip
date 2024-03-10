public class StorageException extends TicklesException {

    @Override
    public void printExceptionMessage() {
        System.out.println("Error: Corrupted file format");
    }
}
