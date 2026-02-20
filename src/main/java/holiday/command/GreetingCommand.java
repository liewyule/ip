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
        * Execute the GreetingCommand and print the greeting message.
        *
        * @param tasks
        * @param ui  UI for displaying feedback.
        * @param storage Storage to save the task
        * @throws BotException
        */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws BotException {

        assert ui != null : "Ui cannot be null";

        return ui.printHello();
    }
}
