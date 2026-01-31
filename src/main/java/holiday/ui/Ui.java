package holiday.ui;

import holiday.task.Task;

import java.util.Scanner;

public class Ui {

    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void printHello() {
        System.out.println("Hello! I'm Holiday");
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

    public void printDelete(Task task, int total) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + total + " tasks in the list.");
    }

    public void printUnmark(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task.toString());
    }

    public void printMark(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task.toString());
    }

    public void printList() {
        System.out.println("Here are the tasks in your list:");
    }

    public void printAddTask(Task task, int total) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + total + " tasks in the list.");
    }
}
