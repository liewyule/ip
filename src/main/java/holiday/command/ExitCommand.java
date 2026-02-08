package holiday.command;

import holiday.BotException;
import holiday.storage.Storage;
import holiday.task.TaskList;
import holiday.ui.Ui;

/**
 * Represents a command that exit the program.
 */
public class ExitCommand extends Command {

    /**
     * Execute the ExitCommand and print the goodbye message.
     *
     * @param tasks
     * @param ui  UI for displaying feedback.
     * @param storage
     * @throws BotException
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws BotException {
        return ui.printGoodBye();
    }

    /**
     * Sets the status of exit become true.
     * @return True
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
