package holiday.ui;

import holiday.task.Task;
import holiday.task.TaskList;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles all user interactions for the Holiday chatbot
 */
public class Ui {

    private final Scanner scanner;


    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prints the welcome message when the program start.
     */
    public void printHello() {
        System.out.println("Hello! I'm Holiday");
        System.out.println("What can I do for you");
        this.printLine();
    }

    /**
     * Reads user input.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Prints the goodbye message when user input "bye" and program ends.
     */
    public void printGoodBye() {
        System.out.println("Bye. Hope to see you again soon !");
    }

    /**
     * Prints a separate line for each conversation.
     */
    public void printLine() {
        System.out.println("---------------------------------------------------");
    }

    /**
     *Prints an error message.
     */
    public void printError() {
        System.out.println("Oh no! something went wrong :(, try again later");
    }

    /**
     * Prints a message to indicate that the task has been deleted.
     *
     * @param task The deleted task.
     * @param total The total remaining number of tasks.
     */
    public void printDelete(Task task, int total) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + total + " tasks in the list.");
    }

    /**
     * Prints a message to indicate that the task has been unmarked.
     *
     * @param task The unmarked task.
     */
    public void printUnmark(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task.toString());
    }

    /**
     * Prints a message to indicate that the task has been marked.
     *
     * @param task The marked task.
     */
    public void printMark(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task.toString());
    }

    /**
     * Prints the header message before listing tasks.
     * Print all the task in the list.
     */
    public void printList(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        ArrayList<Task> taskList = tasks.get();
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + "." + taskList.get(i).toString());
        }
    }

    /**
     * Prints the message to indicate a task has been added.
     *
     * @param task The added task.
     * @param total The total number of tasks.
     */
    public void printAddTask(Task task, int total) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + total + " tasks in the list.");
    }

    /**
     * Prints all the task that matching the find keyword.
     *
     * @param printTasks Tasks to be printed.
     */
    public void printFind(ArrayList<Task> printTasks) {
        if (printTasks.isEmpty()) {
            System.out.println("Oh no, there are no matching tasks in your list!");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < printTasks.size(); i++) {
                System.out.println((i + 1) + "." + printTasks.get(i).toString());
            }

        }
    }
}
