package holiday.command;

import holiday.BotException;
import holiday.storage.Storage;
import holiday.task.TaskList;
import holiday.ui.Ui;

/**
 * Abstract class that implement the method execute.
 * Extends by AddCommand, DeleteCommand, ExitCommand, ListCommand, MarkCommand.
 */
public abstract class Command {

    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws BotException;

    /**
     * Checks whether the program is exit
     * @return False by default.
     */
    public boolean isExit() {
        return false;
    }
}
