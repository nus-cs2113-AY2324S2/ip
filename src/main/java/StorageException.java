/**
 * Deals with the situation in which the save file is corrupted
 */
public class StorageException extends TicklesException {

    @Override
    public void printExceptionMessage() {
        System.out.println("Error: Corrupted file format");
    }
}
