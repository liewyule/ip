package holiday.command;

import holiday.ui.Ui;
import holiday.task.TaskList;
import holiday.storage.Storage;
import holiday.BotException;

public class ExitCommand extends  Command{

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BotException {
        ui.printGoodBye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
