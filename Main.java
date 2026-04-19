package SmartTaskManagementSystem;

import java.io.IOException;

public class Main{
    public static void main(String[] args){

        //create the generic manager
        TaskManager<Task> manager = new TaskManager<>();

        //create an immutable user
        User admin = new User(1, "AdminUser");
        //add users
        manager.addUser(admin);

        // add different types of tasks
        manager.addTask(new SimpleTask("Buy Milk", 1));
        manager.addTask(new ScheduleTask("Finish Project",  "2026-05-01"));
        manager.addTask(new SimpleTask("Clean Room", 2));

        System.out.println("User: " + admin.name());

        //display all sorted tasks(using streams)
       System.out.println("\nAll Tasks:");
            manager.getTasksSortedByPriority()
            .forEach(task -> System.out.println(task.getTitle()));

            //process tasks
        System.out.println("\nProcessing Tasks:");
        manager.processAllTasks(manager.getTasksSortedByPriority());


        //grouped tasks
        System.out.println("\nGrouped by Priority:");
            manager.getTasksGroupedByPriority()
            .forEach((priority, tasks) -> {
                System.out.println("Priority " + priority + ": " + tasks);
       });

       System.out.println("\nHigh Priority Count: " + manager.getHighPriorityCount());

       // Save to file
        try {
            manager.saveTasksToFile("tasks.txt");
            System.out.println("\nTasks saved to file!");
        } catch (IOException e) {
            System.out.println("Error saving: " + e.getMessage());
        }

        //Load from file
        try {
            manager.loadTasksFromFile("tasks.txt");
            System.out.println("Tasks loaded from file!");
        } catch (IOException e) {
            System.out.println("Error loading: " + e.getMessage());
        }
        
    }
}

    
