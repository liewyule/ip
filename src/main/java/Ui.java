import java.util.Scanner;

public class Ui {

    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }
    public void printHello() {
        System.out.println("Hello! I'm yl");
        System.out.println("What can I do for you");
        this.printLine();
    }

    public String readCommand() {
        return scanner.nextLine();
    }
    public void printGoodBye() {
        System.out.println("Bye. Hope to see you again soon !");
    }

    public void printLine() {
        System.out.println("---------------------------------------------------");
    }

    public void printError() {
        System.out.println("Oh no! something went wrong :(, try again later");
    }
}
