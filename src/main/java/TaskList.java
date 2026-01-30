import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList (ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public void printList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + "." + taskList.get(i).toString());
        }
    }
    public boolean isNotInteger(String s) {
        return !s.matches("\\d+");
    }

    public void mark(String userInput) throws BotException {
        String checkNum = userInput.split(" ")[1];

        //check the input after mark is a number
        if (isNotInteger(checkNum)) {
            throw new BotException("please indicate the task u want to mark as a number");
        }

        int num = Integer.parseInt(checkNum) - 1;

        //check the number is within the bounds
        if (num + 1 > taskList.size() || num + 1 <= 0) {
            throw new BotException("you only have " + taskList.size() + " tasks");
        }
        taskList.get(num).mark();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(taskList.get(num).toString());
    }

    public void unmark(String userInput) throws BotException {
        String checkNum = userInput.split(" ")[1];

        //check the input after mark is a number
        if (isNotInteger(checkNum)) {
            throw new BotException("please indicate the task u want to unmark as a number");
        }

        int num = Integer.parseInt(checkNum) - 1;

        //check the number is within the bounds
        if (num + 1 > taskList.size() || num + 1 <= 0) {
            throw new BotException("you only have " + taskList.size() + " tasks");
        }

        taskList.get(num).unmark();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(taskList.get(num).toString());
    }

    public void delete(String userInput) throws BotException {
        String checkNum = userInput.split(" ")[1];

        //check the input after mark is a number
        if (isNotInteger(checkNum)) {
            throw new BotException("please indicate the task u want to delete as a number");
        }

        int num = Integer.parseInt(checkNum) - 1;

        //check the number is within the bounds
        if (num + 1 > taskList.size() || num + 1 <= 0) {
            throw new BotException("you only have " + taskList.size() + " tasks");
        }


        System.out.println("Noted. I've removed this task:");
        System.out.println(taskList.get(num).toString());
        taskList.remove(num);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    public ArrayList<Task> get() {
        return this.taskList;
    }

    public void add(Task task) {
        taskList.add(task);
    }

    public void handleTodo(String userInput) throws BotException {
        //check todo task cannot be empty
        String[] todo = userInput.split(" ", 2);
        if (todo.length < 2 || todo[1].trim().isEmpty()) {
            throw new BotException("todo cannot be empty!!!");
        }
        String description = todo[1];
        Task task = new ToDos(description);
        add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    public void handleDeadline(String userInput) throws BotException {

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
        add(task);

        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    public void handleEvent(String userInput) throws BotException {
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
        add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    public void handleCommand(String userInput) throws BotException {
        String taskType = userInput.split(" ", 2)[0];
        switch (taskType) {
        case "list":
            printList();
            break;
        case "mark":
            mark(userInput);
            break;
        case "unmark":
            unmark(userInput);
            break;
        case "todo":
            handleTodo(userInput);
            break;
        case "deadline":
            handleDeadline(userInput);
            break;
        case "event":
            handleEvent(userInput);
            break;
        case "delete":
            delete(userInput);
            break;
        default:
            throw new BotException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

    }
}
