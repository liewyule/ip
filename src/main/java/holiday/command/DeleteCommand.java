package holiday.command;

import holiday.ui.Ui;
import holiday.task.TaskList;
import holiday.task.Task;
import holiday.storage.Storage;
import holiday.BotException;

public class DeleteCommand extends  Command{

    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BotException {
        Task deleteTask = tasks.delete(index);
        ui.printDelete(deleteTask, tasks.size());
    }

}
