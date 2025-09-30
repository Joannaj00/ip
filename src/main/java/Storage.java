import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            return tasks;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                String type = parts[0];
                boolean isDone = parts[1].equals("1");
                String description = parts[2];
                if (type.equals("T")) {
                    Todo todo = new Todo(description);
                    if (isDone) todo.markAsDone();
                    tasks.add(todo);
                } else if (type.equals("D") && parts.length >= 4) {
                    Deadline d = new Deadline(description, parts[3]);
                    if (isDone) d.markAsDone();
                    tasks.add(d);
                } else if (type.equals("E") && parts.length >= 5) {
                    Event e = new Event(description, parts[3], parts[4]);
                    if (isDone) e.markAsDone();
                    tasks.add(e);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
        return tasks;
    }

    public void save(List<Task> tasks) {
        File folder = new File("./data");
        if (!folder.exists()) {
            folder.mkdir();
        }

        List<String> lines = new ArrayList<>();
        for (Task task : tasks) {
            if (task instanceof Todo) {
                lines.add("T | " + (task.isDone ? "1" : "0") + " | " + task.description);
            } else if (task instanceof Deadline) {
                Deadline d = (Deadline) task;
                lines.add("D | " + (d.isDone ? "1" : "0") + " | " + d.getDescription() + " | " + d.getBy());
            } else if (task instanceof Event) {
                Event e = (Event) task;
                lines.add("E | " + (e.isDone ? "1" : "0") + " | " + e.getDescription() + " | " + e.getFrom() + " | " + e.getTo());
            }
        }

        try {
            Files.write(Paths.get(filePath), lines);
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }
}
