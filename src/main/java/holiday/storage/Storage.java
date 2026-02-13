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
import holiday.task.ToDo;

/**
 * Handles loading and saving tasks to the hard disk.
 */
public class Storage {
    private final Path filePath;

    /**
     * Constructs a storage object and specify the data path.
     * Create data directory if not exist.
     */
    public Storage() {
        this.filePath = getFilePath();
    }

    private static Path getFilePath() {
        Path filePath = Paths.get("data", "taskList.txt");
        try {
            Files.createDirectories(filePath.getParent());
        } catch (IOException e) {
            System.out.println("Cannot create data folder.");
        }
        return filePath;
    }


    /**
     * Loads tasks from the filePath and reconstruct them.
     *
     * @return List of tasks that store in the filePath.
     */
    public ArrayList<Task> loadTask() {
        assert filePath != null : "File path should not be null";

        ArrayList<Task> tasks = new ArrayList<>();
        try {
            if (!Files.exists(filePath)) {
                return tasks;
            }

            List<String> lines = Files.readAllLines(filePath);

            for (String currLine : lines) {
                Task task = parseTaskFromLine(currLine);
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks.");
        }

        return tasks;
    }

    private Task parseTaskFromLine(String line) {
        String[] parts = line.split(" \\| ");
        Task task;

        if (parts.length < 3) {
            return null;
        }

        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        switch (type) {
        case "T":
            task = new ToDo(description);
            break;
        case "D":
            if (parts.length < 4) {
                return null;
            }
            task = new Deadline(description, parts[3]);
            break;
        case "E":
            if (parts.length < 5) {
                return null;
            }
            task = new Event(description, parts[3], parts[4]);
            break;
        default:
            return null;
        }
        if (isDone) {
            task.mark();
        }
        return task;
    }

    /**
     * Saves all tasks in the task list to the filePath.
     *
     * @param tasks The list of task to be saved.
     */
    public void saveTask(TaskList tasks) {
        ArrayList<String> saveTask = new ArrayList<>();
        for (Task currTask : tasks.get()) {
            saveTask.add(currTask.stringSaveToFile());
        }

        try {
            Files.write(filePath, saveTask);
        } catch (IOException e) {
            System.out.println("Error saving tasks.");
        }
    }

    private static ArrayList<String> getSaveTask(TaskList tasks) {
        ArrayList<String> saveTask = new ArrayList<>();
        for (Task currTask : tasks.get()) {
            saveTask.add(currTask.stringSaveToFile());
        }
        return saveTask;
    }
}

