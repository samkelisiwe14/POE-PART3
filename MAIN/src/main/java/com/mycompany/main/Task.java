/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.main;
/**
 *
 * @author RC_Student_lab
 */
public class Task {
 
      private final String taskName;
    private final String taskDescription;
    private final String developerDetails;
    private final int taskDuration;
    private final String taskStatus;
    private static int taskCounter = 1; // Start counter at 1 for user readability
    private final String taskId;

    // Constructor
    public Task(String taskName, String taskDescription, String developerDetails, int taskDuration, String taskStatus) {
        // Validation to ensure non-empty essential fields
        if (taskName == null || taskName.isBlank()) {
            throw new IllegalArgumentException("Task name cannot be null or empty.");
        }
        if (developerDetails == null || developerDetails.isBlank()) {
            throw new IllegalArgumentException("Developer details cannot be null or empty.");
        }
        if (taskDescription == null || taskDescription.isBlank()) {
            throw new IllegalArgumentException("Task description cannot be null or empty.");
        }
        if (taskDuration <= 0) {
            throw new IllegalArgumentException("Task duration must be greater than zero.");
        }

        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.developerDetails = developerDetails;
        this.taskDuration = taskDuration;
        this.taskStatus = taskStatus;
        this.taskId = generateTaskId(); // Automatically generate task ID
    }

    // Method to generate a unique task ID
    private String generateTaskId() {
        String[] devNameParts = developerDetails.split(" ");
        String developerInitials = devNameParts.length > 1
                ? devNameParts[0].substring(0, 1).toUpperCase() + devNameParts[1].substring(0, 1).toUpperCase()
                : devNameParts[0].substring(0, 1).toUpperCase();
        String id = taskName.substring(0, 2).toUpperCase() + ":" + taskCounter + ":" + developerInitials;
        taskCounter++; // Increment counter for next task
        return id;
    }

    // Getter methods
    public String getTaskName() {
        return taskName;
    }

    public String getDeveloperDetails() {
        return developerDetails;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public int getTaskDuration() {
        return taskDuration;
    }

    public String getTaskId() {
        return taskId;
    }

    // Method to print task details
    public String printTaskDetails() {
        return """
                Task ID: %s
                Task Name: %s
                Task Description: %s
                Developer: %s
                Task Duration: %d hours
                Task Status: %s
                """.formatted(taskId, taskName, taskDescription, developerDetails, taskDuration, taskStatus);
    }

    // Optional: Method to check if a task matches a given status
    public boolean isStatus(String status) {
        return this.taskStatus.equalsIgnoreCase(status);
    }

    // Optional: Method to compare task durations
    public static Task compareTaskDuration(Task task1, Task task2) {
        return task1.getTaskDuration() >= task2.getTaskDuration() ? task1 : task2;
    }
}

   

