package holiday.ui;

import java.util.ArrayList;
import java.util.Scanner;

import holiday.task.Task;
import holiday.task.TaskList;

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
    public String printHello() {
        return "Hello! I'm Holiday. " + "What can I do for you";
    }


    /**
     * Prints the goodbye message when user input "bye" and program ends.
     */
    public String printGoodBye() {
        return "Bye. Hope to see you again soon !";
    }


    /**
     * Prints an error message.
     */
    public String printError() {
        return "Oh no! something went wrong :(, try again later";
    }

    /**
     * Prints an error message when cannot recognise the command
     * @return string that tell user no such command
     */
    public String printNoSuchCommand() {
        return "OOPS!!! I'm sorry, but I don't know what that means :-(";
    }

    /**
     * Prints a message to indicate that the task has been deleted.
     *
     * @param task The deleted task.
     * @param total The total remaining number of tasks.
     */
    public String printDeletedTask(Task task, int total) {
        assert task != null : "Task deleted cannot be null";
        return "Noted. I've removed this task:" + task.toString()
                + "Now you have " + total + " tasks in the list.";
    }

    /**
     * Prints a message to indicate that the task has been unmarked.
     *
     * @param task The unmarked task.
     */
    public String printUnmark(Task task) {
        assert task != null : "Unmark task cannot be null";
        return "OK, I've marked this task as not done yet:" + task.toString();
    }

    /**
     * Prints a message to indicate that the task has been marked.
     *
     * @param task The marked task.
     */
    public String printMark(Task task) {
        assert task != null : "Mark task cannot be null";
        return "Nice! I've marked this task as done:" + task.toString();
    }

    /**
     * Prints the header message before listing tasks.
     * Print all the task in the list.
     */
    public String printList(TaskList tasks) {
        assert tasks != null : "TaskList cannot be null";
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("Here are the tasks in your list:\n");

        ArrayList<Task> taskList = tasks.get();
        for (int i = 0; i < taskList.size(); i++) {
            stringbuilder.append(i + 1)
                    .append(". ")
                    .append(taskList.get(i))
                    .append("\n");
        }
        return stringbuilder.toString().trim();
    }

    /**
     * Prints the message to indicate a task has been added.
     *
     * @param task The added task.
     * @param total The total number of tasks.
     */
    public String printAddedTask(Task task, int total) {
        assert task != null : "Added task cannot be null";
        return "Got it. I've added this task:\n"
                + task + "\n"
                + "Now you have " + total + " tasks in the list.";
    }

    /**
     * Prints all the task that matching the find keyword.
     *
     * @param printTasks Tasks to be printed.
     */
    public String printMatchingTasks(ArrayList<Task> printTasks) {
        assert printTasks != null : "TaskList cannot be null";
        StringBuilder stringbuilder = new StringBuilder();

        if (printTasks.isEmpty()) {
            return "Oh no, there are no matching tasks in your list!";
        }
        stringbuilder.append("Here are the matching tasks in your list:\n");

        for (int i = 0; i < printTasks.size(); i++) {
            stringbuilder.append(i + 1)
                    .append(". ")
                    .append(printTasks.get(i))
                    .append("\n");
        }

        return stringbuilder.toString().trim();
    }
}
