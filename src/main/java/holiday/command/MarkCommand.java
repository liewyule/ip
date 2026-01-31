package holiday.command;

import holiday.BotException;
import holiday.storage.Storage;
import holiday.task.Task;
import holiday.task.TaskList;
import holiday.ui.Ui;

/**
 * Represents a command that mark or unmark a task of the task list.
 */
public class MarkCommand extends Command{

    private final boolean isMark;
    private final int index;

    /**
     * Construct a MarkCommand.
     *
     * @param isMark True if it is a mark command, False for unmark command.
     * @param index Index of the mark/unmark task.
     */
    public MarkCommand(boolean isMark, int index) {
        this.isMark = isMark;
        this.index = index;
    }

    /**
     * Execute the mark/unmark command.
     *
     * @param tasks Task list to mark/unmark the task.
     * @param ui UI for displaying feedback.
     * @param storage
     * @throws BotException
     */
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
