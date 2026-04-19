package SmartTaskManagementSystem;

public abstract sealed class Task permits SimpleTask, ScheduleTask {

    private static int counter = 0;

    private final int id;
    private final String title;

    public Task(String title) {
        this.title = title;
        this.id = ++counter;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public abstract void execute();
}