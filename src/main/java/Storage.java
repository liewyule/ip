import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private Path filePath;

    public Storage() {
        Path filePath = Paths.get("data", "taskList.txt");
        try {
            Files.createDirectories(filePath.getParent());
        } catch (IOException e) {
            System.out.println("Cannot create data folder.");
        }
        this.filePath = filePath;
    }

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
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks.");
        }

        return tasks;
    }

    public void save(ArrayList<Task> tasks) {
        ArrayList<String> saveTask = new ArrayList<>();
        for (Task currTask : tasks) {
            saveTask.add(currTask.saveString());
        }

        try {
            Files.write(filePath, saveTask);
        } catch (IOException e) {
            System.out.println("Error saving tasks.");
        }
    }
}

