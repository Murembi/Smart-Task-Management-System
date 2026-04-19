package SmartTaskManagementSystem;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.nio.file.*;
import java.io.IOException;


public class TaskManager<T extends Task>{

    private Map<Integer, T> tasks = new HashMap<>();
    private Set<User> users = new HashSet<>();

    public void addTask(T task) {
        if(task == null){
            throw new InvalidTaskException("Task cannot be null.");
        }
        tasks.put(task.getId(), task);
    }

    public void addUser(User user){
    users.add(user);
    }

    public List<T> getTasksSortedByPriority() {

    return tasks.values()
            .stream()
            .sorted(Comparator.comparingInt(t -> 
                (t instanceof Prioritizable p) ? p.getPriorityLevel() : Integer.MAX_VALUE
            ))
           .collect(Collectors.toList());
    }

    //PECS: // process all task
    public void processAllTasks(List<? extends Task> taskList) {

        taskList.forEach(task -> {
            task.execute();

        if (task instanceof Prioritizable p) {
            System.out.println("Priority: " + p.getPriorityLevel());
        }
    });
}

    //groupingBy advannced lambdas

public Map<Integer, List<Task>> getTasksGroupedByPriority() {

    return tasks.values()
            .stream()
            .filter(t -> t instanceof Prioritizable)
            .collect(Collectors.groupingBy(
                    t -> ((Prioritizable) t).getPriorityLevel()
            ));
}

    // counting
public long getHighPriorityCount() {

    return tasks.values()
            .stream()
            .filter(t -> t instanceof Prioritizable p && p.getPriorityLevel() >= 2)
            .count();}


public void saveTasksToFile(String filename) throws IOException {

    List<String> lines = tasks.values()
            .stream()
            .map(task -> {
                if (task instanceof SimpleTask s) {
                    return task.getId() + ",SimpleTask," + s.getTitle() + "," + s.getPriorityLevel();
                } else if (task instanceof ScheduleTask st) {
                    return task.getId() + ",ScheduleTask," + st.getTitle() + "," + st.getScheduleTime();
                }
                return "";
            })
            .toList();

    Files.write(Path.of(filename), lines);
        
}
public void loadTasksFromFile(String filename) throws IOException {

        List<String> lines = Files.readAllLines(Path.of(filename));

        for (String line : lines) {

            String[] parts = line.split(",");

            int id = Integer.parseInt(parts[0]); // currently unused
            String type = parts[1];
            String title = parts[2];

            if (type.equals("SimpleTask")) {
                int priority = Integer.parseInt(parts[3]);
                addTask((T) new SimpleTask(title, priority));

            } else if (type.equals("ScheduleTask")) {
                String date = parts[3];
                addTask((T) new ScheduleTask(title, date));
            }
        }
    }

    //exceute tasks in parallel 
    
    public void processTasksConcurrently() {

    tasks.values().forEach(task -> {
        new Thread(() -> task.execute()).start();
    });
}


}
