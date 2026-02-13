package holiday.command;

import holiday.BotException;
import holiday.storage.Storage;
import holiday.task.TaskList;
import holiday.ui.Ui;

/**
 * Represents a command that execute greeting command.
 */
public class GreetingCommand extends Command {

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws BotException {

        assert ui != null : "Ui cannot be null";

        return ui.printHello();
    }
}
