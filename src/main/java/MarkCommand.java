public class MarkCommand extends Command{

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
