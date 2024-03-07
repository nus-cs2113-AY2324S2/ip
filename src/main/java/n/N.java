/**
 * <h1>N</h1>
 * The N program is a CLI-based chatbot that can help to
 * keep track of tasks
 *
 * @author  anneleong
 * @version 1.0
 * @since   2024-03-07
 */

package n;

import n.exceptions.EmptyTaskDescriptionException;

import java.io.FileNotFoundException;
import java.io.IOException;

public class N {
    private Ui ui;
    public static Storage storage;
    /**
     * Constructs a new object of N.
     * Creates a new instance of Ui which will handle interactions with the user.
     */
    public N() {
        ui = new Ui();
        storage = new Storage();
    }
    /**
     * Returns lateral location of the specified position.
     * If the position is unset, NaN is returned.
     *
     * @throws FileNotFoundException If filepath does not exist.
     * @throws EmptyTaskDescriptionException If task in input message is empty.
     */
    public void run() throws IOException, EmptyTaskDescriptionException {
        this.ui = new Ui();
        ui.printWelcome();
        storage.loadTaskList();
        ui.handleMessages();
        storage.saveTaskList();
    }
    public static void main(String[] args) throws IOException, EmptyTaskDescriptionException {
        new N().run();
    }
}
