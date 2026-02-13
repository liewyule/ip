package holiday;

import holiday.command.Command;
import holiday.parser.Parser;
import holiday.storage.Storage;
import holiday.task.TaskList;
import holiday.ui.Ui;

/**
 * The main entry point of the Holiday chatbot.
 */
public class Holiday {
    private final Storage storage;
    private final Ui ui;
    private final TaskList tasks;

    /**
     * Constructs a Holiday chatbot instance and initializes some component.
     * <p>
     * Loads existing tasks from hard disk to tasks.
     */
    public Holiday() {
        this.storage = new Storage();
        this.ui = new Ui();
        this.tasks = new TaskList(storage.loadTask());
    }


    public String getResponse(String userInput) {
        try {
            Command c = Parser.parse(userInput);
            return c.execute(tasks, ui, storage);
        } catch (BotException e) {
            return e.getMessage();
        }
    }


}


