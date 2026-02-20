package holiday.command;

import holiday.BotException;
import holiday.storage.Storage;
import holiday.task.TaskList;
import holiday.ui.Ui;

/**
 * Represents a command that execute greeting command.
 */
public class GreetingCommand extends Command {

    /**
     * Executes the greeting command and returns the greeting message.
     * @param tasks
     * @param ui ui to print the greeting message
     * @param storage storage to save the data, not used in this command
     * @return the greeting message
     * @throws BotException if there is an error during execution
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws BotException {

        assert ui != null : "Ui cannot be null";

        return ui.printHello();
    }
}
