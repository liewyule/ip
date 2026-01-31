package holiday.command;

import holiday.ui.Ui;
import holiday.task.TaskList;
import holiday.storage.Storage;
import holiday.BotException;

/**
 * Abstract class that implement the method execute.
 * Extends by AddCommand, DeleteCommand, ExitCommand, ListCommand, MarkCommand.
 */
public abstract class Command {

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws BotException;

    /**
     * Checks whether the program is exit
     * @return False by default.
     */
    public boolean isExit() {
        return false;
    }
}
