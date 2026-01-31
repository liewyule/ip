package holiday.command;

import holiday.BotException;
import holiday.storage.Storage;
import holiday.task.Task;
import holiday.task.TaskList;
import holiday.ui.Ui;

public class MarkCommand extends Command {

    private final boolean isMark;
    private final int index;

    public MarkCommand(boolean isMark, int index) {
        this.isMark = isMark;
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BotException {
        if (isMark) {
            Task markTask = tasks.mark(index);
            ui.printMark(markTask);
        } else {
            Task unmarkTask = tasks.unmark(index);
            ui.printUnmark(unmarkTask);
        }
    }
}
