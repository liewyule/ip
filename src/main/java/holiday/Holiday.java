package holiday;

import holiday.command.Command;
import holiday.parser.Parser;
import holiday.storage.Storage;
import holiday.task.TaskList;
import holiday.ui.Ui;

public class Holiday {
    private final Storage storage;
    private final Ui ui;
    private final TaskList tasks;

    public Holiday() {
        this.storage = new Storage();
        this.ui = new Ui();
        this.tasks = new TaskList(storage.load());
    }


    public void run() {

        ui.printHello();
        boolean isExit = false;
        while (!isExit) {
            String userInput = ui.readCommand();

            if (userInput.equals("bye")) {
                ui.printGoodBye();
                break;
            }
            try {
                Command c = Parser.parse(userInput);
                c.execute(tasks, ui, storage);
                storage.save(tasks);
                ui.printLine();
                isExit = c.isExit();
            } catch (BotException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Holiday().run();
    }

}


