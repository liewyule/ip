package holiday.task;

import holiday.BotException;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public void printList() {
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + "." + taskList.get(i).toString());
        }
    }

    public Task mark(int index) throws BotException {
        //check the number is within the bounds
        if (index + 1 > taskList.size() || index + 1 <= 0) {
            throw new BotException("you only have " + taskList.size() + " tasks");
        }
        taskList.get(index).mark();
        return taskList.get(index);

    }

    public Task unmark(int index) throws BotException {
        //check the number is within the bounds
        if (index + 1 > taskList.size() || index + 1 <= 0) {
            throw new BotException("you only have " + taskList.size() + " tasks");
        }

        taskList.get(index).unmark();
        return taskList.get(index);

    }

    public Task delete(int index) throws BotException {

        //check the number is within the bounds
        if (index + 1 > taskList.size() || index + 1 <= 0) {
            throw new BotException("you only have " + taskList.size() + " tasks");
        }
        return taskList.remove(index);
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
