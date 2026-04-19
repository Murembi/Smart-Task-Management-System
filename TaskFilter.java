package SmartTaskManagementSystem;

@FunctionalInterface
public interface TaskFilter {
    boolean filter(Task task);
}
