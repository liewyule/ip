import java.util.Scanner;
import java.util.ArrayList;


public class yl {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        Storage storage = new Storage();
        tasks = storage.load();

        printHello();

        while (true) {
            String userInput = sc.nextLine();

            if (userInput.equals("bye")) {
                printGoodBye();
                break;
            }

            try {
                handleCommand(userInput, tasks);
                storage.save(tasks);
            } catch (BotException e) {
                System.out.println(e.getMessage());
            }
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

    public static void handleMark(String userInput, ArrayList<Task> tasks) throws BotException {
        String checkNum = userInput.split(" ")[1];

        //check the input after mark is a number
        if (isNotInteger(checkNum)) {
            throw new BotException("please indicate the task u want to mark as a number");
        }

        int num = Integer.parseInt(checkNum) - 1;

        //check the number is within the bounds
        if (num + 1 > tasks.size() || num + 1 <= 0) {
            throw new BotException("you only have " + tasks.size() + " tasks");
        }
        tasks.get(num).mark();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks.get(num).toString());
    }

    public static void handleDelete(String userInput, ArrayList<Task> tasks) throws BotException {
        String checkNum = userInput.split(" ")[1];

        //check the input after mark is a number
        if (isNotInteger(checkNum)) {
            throw new BotException("please indicate the task u want to delete as a number");
        }

        int num = Integer.parseInt(checkNum) - 1;

        //check the number is within the bounds
        if (num + 1 > tasks.size() || num + 1 <= 0) {
            throw new BotException("you only have " + tasks.size() + " tasks");
        }


        System.out.println("Noted. I've removed this task:");
        System.out.println(tasks.get(num).toString());
        tasks.remove(num);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    public static void handleUnmark(String userInput, ArrayList<Task> tasks) throws BotException {
        String checkNum = userInput.split(" ")[1];

        //check the input after mark is a number
        if (isNotInteger(checkNum)) {
            throw new BotException("please indicate the task u want to unmark as a number");
        }

        int num = Integer.parseInt(checkNum) - 1;

        //check the number is within the bounds
        if (num + 1 > tasks.size() || num + 1 <= 0) {
            throw new BotException("you only have " + tasks.size() + " tasks");
        }

        tasks.get(num).unmark();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(tasks.get(num).toString());
    }

    public static boolean isNotInteger(String s) {
        return !s.matches("\\d+");
    }

    public static void handleTodo(String userInput, ArrayList<Task> tasks) throws BotException {
        //check todo task cannot be empty
        String[] todo = userInput.split(" ", 2);
        if (todo.length < 2 || todo[1].trim().isEmpty()) {
            throw new BotException("todo cannot be empty!!!");
        }
        String description = todo[1];
        Task task = new ToDos(description);
        tasks.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    public static void handleDeadline(String userInput, ArrayList<Task> tasks) throws BotException {

        //check task cannot be empty
        String[] deadline = userInput.split(" ", 2);
        if (deadline.length < 2 || deadline[1].trim().isEmpty()) {
            throw new BotException("task cannot be empty!!!");
        }
        String deadLineTask = deadline[1];

        //check the deadline cannot be empty
        String[] checkTime = deadLineTask.split(" /by ", 2);
        if (checkTime.length < 2 || checkTime[1].trim().isEmpty()) {
            throw new BotException("pls specify a deadline");
        }
        String description = checkTime[0];
        String deadLine = checkTime[1];

        //create new task
        Task task = new Deadline(description, deadLine);
        tasks.add(task);

        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    public static void handleEvent(String userInput, ArrayList<Task> tasks) throws BotException {
        //check task cannot be empty
        String[] checkEvent = userInput.split(" ", 2);
        if (checkEvent.length < 2 || checkEvent[1].trim().isEmpty()) {
            throw new BotException("task cannot be empty!!!");
        }
        String eventTask = checkEvent[1];

        //check the start date cannot be empty
        String[] checkStart = eventTask.split(" /from ", 2);
        if (checkStart.length < 2 || checkStart[1].trim().isEmpty()) {
            throw new BotException("pls specify when the event start!");
        }

        String description = checkStart[0];
        String checkTime = checkStart[1];

        //check the end date cannot be empty
        String[] checkEnd = checkTime.split(" /to ", 2);
        if (checkEnd.length < 2 || checkEnd[1].trim().isEmpty()) {
            throw new BotException("pls specify when the event end!");
        }

        String start = checkEnd[0];
        String end = checkEnd[1];

        //create new task
        Task task = new Event(description, start, end);
        tasks.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    public static void handleCommand(String userInput, ArrayList<Task> tasks) throws BotException {
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
        case "delete":
            handleDelete(userInput, tasks);
            break;
        default:
            throw new BotException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

    }

}


