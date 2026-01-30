public class AddCommand extends Command{

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
            System.out.println("Got it. I've added this task:");
            System.out.println(task);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            break;
        }
        case "deadline": {
            Task task = new Deadline(description, to);
            tasks.add(task);

            System.out.println("Got it. I've added this task:");
            System.out.println(task);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            break;
        }
        case "event": {
            //create new task
            Task task = new Event(description, from, to);
            tasks.add(task);
            System.out.println("Got it. I've added this task:");
            System.out.println(task);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            break;
        }
        }
    }
}
