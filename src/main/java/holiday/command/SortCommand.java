package holiday.command;

import holiday.BotException;
import holiday.storage.Storage;
import holiday.task.Task;
import holiday.task.TaskList;
import holiday.ui.Ui;

public class SortCommand extends Command {

    private final String description;

    public SortCommand(String description) {
        this.description = description;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws BotException {

        assert tasks != null : "TaskList cannot be null";
        assert ui != null : "Ui cannot be null";
        assert storage != null : "Storage cannot be null";

        if (description.equals("name")) {
            tasks.sortByName();
            storage.saveTask(tasks);
            return ui.printList(tasks);
        } else {
            return ui.printNoSuchCommand();
        }
    }
}
