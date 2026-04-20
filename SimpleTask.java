package SmartTaskManagementSystem;

public final class SimpleTask extends Task implements Prioritizable {

    private int priority;

    public SimpleTask(String title, int priority) {
        super(title);
        this.priority = priority;
    }

    @Override
    public void execute() {
        System.out.println("Executing simple task: " + getTitle());
    }

    @Override
    public int getPriorityLevel() {
        return priority;
    }

    @Override
    public String toString() {
        return "SimpleTask{ID=" + getId() + ", Title='" + getTitle() + "', Priority=" + priority + "}";
    }

}

