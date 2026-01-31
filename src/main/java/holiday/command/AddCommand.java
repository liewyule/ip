package holiday.command;

import holiday.BotException;
import holiday.storage.Storage;
import holiday.task.Deadline;
import holiday.task.Event;
import holiday.task.Task;
import holiday.task.TaskList;
import holiday.task.ToDos;
import holiday.ui.Ui;

public class AddCommand extends Command {

    private final String type;
    private final String description;
    private final String from;
    private final String to;

    public AddCommand(String type, String description, String from, String to) {
        this.type = type;
        this.description = description;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BotException {
        switch (this.type) {
        case "todo": {
            Task task = new ToDos(description);
            tasks.add(task);
            ui.printAddTask(task, tasks.size());
            break;
        }
        case "deadline": {
            Task task = new Deadline(description, to);
            tasks.add(task);
            ui.printAddTask(task, tasks.size());
            break;
        }
        case "event": {
            Task task = new Event(description, from, to);
            tasks.add(task);
            ui.printAddTask(task, tasks.size());
            break;
        }
        }
    }
}
