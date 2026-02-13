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

    private final String[] keywords;

    /**
     * Construct a FindCommand by specifying a search keyword.
     * @param keyword Keyword to search the corresponding task.
     */
    public FindCommand(String... keyword) {
        this.keywords = keyword;
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
        assert tasks != null : "TaskList cannot be null";
        assert ui != null : "Ui cannot be null";
        assert storage != null : "Storage cannot be null";

        ArrayList<Task> printTask = new ArrayList<>();
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task currTask : tasks.get()) {
            if (isMatch(currTask)) {
                matchingTasks.add(currTask);
            }
        }
        return ui.printMatchingTasks(matchingTasks);
    }

    private boolean isMatch(Task task) {
        for (String k : keywords) {
            if (task.toString().contains(k)) {
                return true;
            }
        }
        return false;
    }
}
