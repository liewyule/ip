package holiday.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import holiday.task.Deadline;
import holiday.task.Event;
import holiday.task.Task;
import holiday.task.TaskList;
import holiday.task.ToDos;

/**
 * Handles loading and saving tasks to the hard disk.
 */
public class Storage {
    private Path filePath;

    /**
     * Constructs a storage object and specify the data path.
     * Create data directory if not exist.
     */
    public Storage() {
        Path filePath = Paths.get("data", "taskList.txt");
        try {
            Files.createDirectories(filePath.getParent());
        } catch (IOException e) {
            System.out.println("Cannot create data folder.");
        }
        this.filePath = filePath;
    }


    /**
     * Loads tasks from the filePath and reconstruct them.
     *
     * @return List of tasks that store in the filePath.
     */
    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(filePath);
            for (String currLine : lines) {
                String[] parts = currLine.split(" \\| ");
                String taskType = parts[0];
                boolean isDone = parts[1].equals("1");
                String description = parts[2];

                Task task = null;
                if (taskType.equals("T")) {
                    task = new ToDos(description);

                } else if (taskType.equals("D")) {
                    String deadline = parts[3];

                    task = new Deadline(description, deadline);
                } else {
                    String from = parts[3];
                    String to = parts[4];
                    task = new Event(description, from, to);
                }
                tasks.add(task);
                if (isDone) {
                    task.mark();
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks.");
        }

        return tasks;
    }

    /**
     * Saves all tasks in the task list to the filePath.
     *
     * @param tasks The list of task to be saved.
     */
    public void save(TaskList tasks) {
        ArrayList<String> saveTask = new ArrayList<>();
        for (Task currTask : tasks.get()) {
            saveTask.add(currTask.saveString());
        }

        try {
            Files.write(filePath, saveTask);
        } catch (IOException e) {
            System.out.println("Error saving tasks.");
        }
    }
}

