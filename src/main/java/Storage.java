import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {
    private ArrayList<Task> tasks;
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
        return tasks;
    }

    public void save(ArrayList<Task> tasks) {
        ArrayList<String> saveTask = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            Task currTask = tasks.get(i);
            saveTask.add(currTask.saveString());
        }

        try {
            Files.write(filePath, saveTask);
        } catch (IOException e) {
            System.out.println("Error saving tasks.");
        }
    }
}

