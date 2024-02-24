import quill.command.Command;
import quill.command.ExitCommand;
import quill.exception.EmptyDateException;
import quill.exception.QuillException;
import quill.parser.Parser;
import quill.storage.Save;
import quill.task.*;
import quill.ui.TextUi;

import java.util.ArrayList;

public class Quill {
    private static TextUi ui;
    private static Save save;
    private static TaskList tasks;

    public Quill() {
        ui = new TextUi();
        tasks = Save.readFile();
    }
    public void run() {
        TextUi.showWelcomeMessage();
        boolean isExit = false;
        Command c;
        do {
            String line = ui.getUserCommand();
            TextUi.showDivider();
            c = Parser.parse(line);
            c.execute(tasks, ui, save);
            TextUi.showDivider();
        } while (!ExitCommand.isExit(c));
    }

    public static void main(String[] args) {
        new Quill().run();
    }
}
