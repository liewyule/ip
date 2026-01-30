package holiday.command;

import holiday.ui.Ui;
import holiday.task.TaskList;
import holiday.storage.Storage;
import holiday.BotException;

public abstract class Command {

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws BotException;

    public boolean isExit() {
        return false;
    }
}
