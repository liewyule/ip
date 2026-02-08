package holiday.command;

import java.util.ArrayList;

import holiday.BotException;
import holiday.storage.Storage;
import holiday.task.Task;
import holiday.task.TaskList;
import holiday.ui.Ui;

/**
 * Represents a command that find the task in the task list.
 */
public class FindCommand extends Command {

    private final String keyword;

    /**
     * Construct a FindCommand by specifying a search keyword.
     * @param keyword Keyword to search the corresponding task.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Execute the ListCommand and print out the task list
     * that contain the search keyword.
     * .
     * @param tasks The full task list.
     * @param ui UI for displaying feedback.
     * @param storage
     * @throws BotException
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws BotException {
        ArrayList<Task> printTask = new ArrayList<>();
        for (Task currTask : tasks.get()) {
            if (currTask.toString().contains(this.keyword)) {
                printTask.add(currTask);
            }
        }
        return ui.printMatchingTasks(printTask);
    }
}
