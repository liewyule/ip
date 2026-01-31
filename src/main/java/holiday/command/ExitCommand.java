package holiday.command;

import holiday.BotException;
import holiday.storage.Storage;
import holiday.task.TaskList;
import holiday.ui.Ui;

public class ExitCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BotException {
        ui.printGoodBye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
