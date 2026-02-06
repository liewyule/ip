package holiday.command;

import holiday.BotException;
import holiday.storage.Storage;
import holiday.task.Task;
import holiday.task.TaskList;
import holiday.ui.Ui;

/**
 * Represents a command that deletes a task from the task list.
 */
public class DeleteCommand extends Command {

    private final int index;

    /**
     * Construct a delete command
     * @param index Index of the delete task
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Execute the delete command by delete the corresponding task
     *
     * @param tasks Task to be deleted
     * @param ui UI for displaying feedback.s
     * @param storage
     * @throws BotException
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BotException {
        Task deleteTask = tasks.delete(index);
        ui.printDeletedTask(deleteTask, tasks.size());
        storage.saveTask(tasks);
    }

}
