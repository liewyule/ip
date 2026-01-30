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

    public void mark(int index) throws BotException {
        //check the number is within the bounds
        if (index + 1 > taskList.size() || index + 1 <= 0) {
            throw new BotException("you only have " + taskList.size() + " tasks");
        }
        taskList.get(index).mark();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(taskList.get(index).toString());
    }

    public void unmark(int index) throws BotException {
        //check the number is within the bounds
        if (index + 1 > taskList.size() || index + 1 <= 0) {
            throw new BotException("you only have " + taskList.size() + " tasks");
        }

        taskList.get(index).unmark();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(taskList.get(index).toString());
    }

    public void delete(int index) throws BotException {

        //check the number is within the bounds
        if (index + 1 > taskList.size() || index + 1 <= 0) {
            throw new BotException("you only have " + taskList.size() + " tasks");
        }


        System.out.println("Noted. I've removed this task:");
        System.out.println(taskList.get(index).toString());
        taskList.remove(index);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    public ArrayList<Task> get() {
        return this.taskList;
    }

    public void add(Task task) {
        taskList.add(task);
    }
    public int size() {
        return this.taskList.size();
    }
}
