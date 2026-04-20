package SmartTaskManagementSystem;

public final class ScheduleTask extends Task implements Prioritizable {
    private String scheduleTime;

    public ScheduleTask(String title, String scheduleTime) {
        super(title);
        this.scheduleTime = scheduleTime;
    }

    @Override
    public void execute() {
        System.out.println("Executing scheduled task: " + getTitle() + " due on " + scheduleTime);
    }

    @Override
    public int getPriorityLevel() {
        return 3;   // example High 
    }

    public String getScheduleTime() {
    return scheduleTime;
}

    @Override
    public String toString() {
        return "ScheduleTask{ID=" + getId() + ", Title='" + getTitle() + "', ScheduleTime='" + scheduleTime + "', Priority=" + getPriorityLevel() + "}";
    }

}
