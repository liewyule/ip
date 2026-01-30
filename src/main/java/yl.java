import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDateTime;


public class yl {
    private final Storage storage;
    private final Ui ui;
    private final TaskList tasks;

    public yl() {
        this.storage = new Storage();
        this.ui = new Ui();
        this.tasks = new TaskList(storage.load());
    }


    public void run() {
        Scanner sc = new Scanner(System.in);

        ui.printHello();

        while (true) {
            String userInput = ui.readCommand();
            ui.printLine();

            if (userInput.equals("bye")) {
                ui.printGoodBye();
                break;
            }
            try {
                tasks.handleCommand(userInput);
                storage.save(tasks);
                ui.printLine();
            } catch (BotException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new yl().run();
    }

}


