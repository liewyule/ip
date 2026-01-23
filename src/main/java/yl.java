import java.util.Scanner;
import java.util.ArrayList;

public class yl {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        printHello();

        while (true) {
            String userInput = sc.nextLine();

            if (userInput.equals("bye")) {
                printGoodBye();
                break;
            }
                handleCommand(userInput, tasks);

            }
        }

    public static void printHello() {
        System.out.println("Hello! I'm yl");
        System.out.println("What can I do for you");
    }

    public static void printGoodBye() {
        System.out.println("Bye. Hope to see you again soon !");
    }

    public static void printList(ArrayList<Task> tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i).toString());
        }
    }

    public static void handleMark(String userInput, ArrayList<Task> tasks) {
        int num = Integer.parseInt((userInput.split(" ")[1])) - 1;
        tasks.get(num).mark();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks.get(num).toString());
    }

    public static void handleUnmark(String userInput, ArrayList<Task> tasks) {
        int num = Integer.parseInt((userInput.split(" ")[1])) - 1;
        tasks.get(num).unmark();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(tasks.get(num).toString());
    }

    public static void handleTodo(String userInput, ArrayList<Task> tasks) {
        String description = userInput.split(" ", 2)[1];
        Task task = new ToDos(description);
        tasks.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    public static void handleDeadline(String userInput, ArrayList<Task> tasks) {
        String deadLineTask = userInput.split(" ", 2)[1];
        String description = deadLineTask.split(" /by ", 2)[0];
        String deadLine = deadLineTask.split(" /by ", 2)[1];
        Task task = new Deadline(description, deadLine);
        tasks.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    public static void handleEvent(String userInput, ArrayList<Task> tasks) {
        String eventTask = userInput.split(" ", 2)[1];
        String description = eventTask.split(" /from ", 2)[0];
        String duration = eventTask.split(" /from ", 2)[1];
        String start = duration.split(" /to ", 2)[0];
        String end = duration.split(" /to ", 2)[1];
        Task task = new Event(description, start, end);
        tasks.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    public static void handleCommand(String userInput, ArrayList<Task> tasks) {
        String taskType = userInput.split(" ", 2)[0];
        switch (taskType) {
            case "list":
                printList(tasks);
                break;
            case "mark":
                handleMark(userInput, tasks);
                break;
            case "unmark":
                handleUnmark(userInput, tasks);
                break;
            case "todo":
                handleTodo(userInput, tasks);
                break;
            case "deadline":
                handleDeadline(userInput, tasks);
                break;
            case "event":
                handleEvent(userInput, tasks);
                break;
            default:
                System.out.println("No such task type");

        }

    }

}


