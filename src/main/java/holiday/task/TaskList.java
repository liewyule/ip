package holiday.task;

import java.util.ArrayList;

import holiday.BotException;

/**
 * Represents a list of tasks and provides operation
 * to manage tasks
 */
public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }



    /**
     * Marks the tasks of the given index as completed.
     *
     * @param index Index of the task
     * @return The updated task
     * @throws BotException If the index is out of bounds.
     */
    public Task mark(int index) throws BotException {
        //check the number is within the bounds
        if (index + 1 > taskList.size() || index + 1 <= 0) {
            throw new BotException("you only have " + taskList.size() + " tasks");
        }
        taskList.get(index).mark();
        return taskList.get(index);

    }

    /**
     * Marks the tasks of the given index as not complete.
     *
     * @param index Index of the task
     * @return The updated task
     * @throws BotException If the index is out of bounds.
     */
    public Task unmark(int index) throws BotException {
        //check the number is within the bounds
        if (index + 1 > taskList.size() || index + 1 <= 0) {
            throw new BotException("you only have " + taskList.size() + " tasks");
        }

        taskList.get(index).unmark();
        return taskList.get(index);

    }

    /**
     * Deletes the tasks of the given index.
     *
     * @param index Index of the task
     * @return The deleted task
     * @throws BotException If the index is out of bounds.
     */
    public Task delete(int index) throws BotException {

        //check the number is within the bounds
        if (index + 1 > taskList.size() || index + 1 <= 0) {
            throw new BotException("you only have " + taskList.size() + " tasks");
        }
        return taskList.remove(index);
    }

    /**
     * Gets the task ArrayList.
     * @return Task ArrayList.
     */
    public ArrayList<Task> get() {
        return this.taskList;
    }

    /**
     * Adds a specific task into the task list
     *
     * @param task The task to be added
     */
    public void add(Task task) {
        assert taskList != null : "TaskList cannot be null";
        taskList.add(task);
    }

    /**
     * Gets the number of task in the tasks list.
     *
     * @return Size of the Task ArrayList.
     */
    public int size() {
        assert taskList != null : "TaskList cannot be null";
        return this.taskList.size();
    }
}
