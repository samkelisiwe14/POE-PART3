/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.main;

     import javax.swing.*;
import java.util.ArrayList;
/**
 *
 * @author tlesi
 */
public class EasyKanban {
    private final ArrayList<Task> tasks; // Declare ArrayList to store tasks
    private int totalTaskDuration;        // Variable to keep track of total task duration
    private final Login userLogin;        // Instance of Login class

    // Constructor
    public EasyKanban() {
        this.tasks = new ArrayList<>();
        this.totalTaskDuration = 0;
        this.userLogin = new Login(); // Initialize Login instance
    }

    // Main method
    public static void main(String[] args) {
        EasyKanban kanbanApp = new EasyKanban();

        // User registration process
        String username = JOptionPane.showInputDialog("Please enter a username (max 5 characters, must contain '_'):");
        String password = JOptionPane.showInputDialog("Please enter a password (min 8 characters, must contain a capital letter, a number, and a special character):");
        String firstName = JOptionPane.showInputDialog("Please enter your first name:");
        String lastName = JOptionPane.showInputDialog("Please enter your last name:");

        String registrationStatus = kanbanApp.userLogin.registerUser(username, password, firstName, lastName);
        JOptionPane.showMessageDialog(null, registrationStatus);

        if (registrationStatus.contains("successfully")) {
            boolean loginSuccessful = false;
            while (!loginSuccessful) {
                String loginUsername = JOptionPane.showInputDialog("Please enter your username to login:");
                String loginPassword = JOptionPane.showInputDialog("Please enter your password:");

                String loginMessage = kanbanApp.userLogin.returnLoginStatus(loginUsername, loginPassword);
                JOptionPane.showMessageDialog(null, loginMessage);
                loginSuccessful = loginMessage.startsWith("Welcome");

                if (!loginSuccessful) {
                    int retry = JOptionPane.showConfirmDialog(null, "Do you want to try again?", "Login Failed", JOptionPane.YES_NO_OPTION);
                    if (retry == JOptionPane.NO_OPTION) {
                        return;
                    }
                }
            }

            kanbanApp.run();
        }
    }

    // Method to run the main Kanban functionality
    public void run() {
        JOptionPane.showMessageDialog(null, "Welcome to EasyKanban");
        boolean running = true;

        while (running) {
            String menu = JOptionPane.showInputDialog("""
                    Please choose an option:
                    1. Add tasks
                    2. Display report
                    3. Search for tasks
                    4. Delete a task
                    5. Quit""");

            switch (menu) {
                case "1" -> addTasks();
                case "2" -> displayReport();
                case "3" -> searchTasks();
                case "4" -> deleteTask();
                case "5" -> running = false;
                default -> JOptionPane.showMessageDialog(null, "Invalid option. Please try again.");
            }
        }
    }

    // Method to add tasks to the Kanban system
    private void addTasks() {
        int numTasks = Integer.parseInt(JOptionPane.showInputDialog("How many tasks would you like to enter?"));

        for (int i = 0; i < numTasks; i++) {
            String taskName = JOptionPane.showInputDialog("Enter task name:");
            String taskDescription;
            while (true) {
                taskDescription = JOptionPane.showInputDialog("Enter task description (max 50 characters):");
                if (taskDescription.length() <= 50) {
                    break;
                }
                JOptionPane.showMessageDialog(null, "Please enter a task description of less than 50 characters");
            }
            String developerDetails = JOptionPane.showInputDialog("Enter developer first and last name:");
            int taskDuration = Integer.parseInt(JOptionPane.showInputDialog("Enter task duration in hours:"));
            String[] statusOptions = {"To Do", "Doing", "Done"};
            String taskStatus = (String) JOptionPane.showInputDialog(null, "Choose task status", "Task Status",
                    JOptionPane.QUESTION_MESSAGE, null, statusOptions, statusOptions[0]);

            Task task = new Task(taskName, taskDescription, developerDetails, taskDuration, taskStatus);
            JOptionPane.showMessageDialog(null, "Task successfully captured");
            tasks.add(task);
            totalTaskDuration += task.getTaskDuration();
            JOptionPane.showMessageDialog(null, task.printTaskDetails());
        }
        JOptionPane.showMessageDialog(null, "Total task duration: " + totalTaskDuration + " hours");
    }

    // Method to display a report of all tasks
    private void displayReport() {
        if (tasks.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No tasks available to display.");
            return;
        }

        StringBuilder report = new StringBuilder();
        for (Task task : tasks) {
            report.append(task.printTaskDetails()).append("\n\n");
        }

        JOptionPane.showMessageDialog(null, report.toString());
    }

    // Method to search for tasks
    private void searchTasks() {
        String searchOption = JOptionPane.showInputDialog("""
                Search Options:
                1. Search by Task Name
                2. Search by Developer""");

        switch (searchOption) {
            case "1" -> {
                String taskName = JOptionPane.showInputDialog("Enter the task name to search for:");
                for (Task task : tasks) {
                    if (task.getTaskName().equalsIgnoreCase(taskName)) {
                        JOptionPane.showMessageDialog(null, "Task Found:\n" + task.printTaskDetails());
                        return;
                    }
                }
                JOptionPane.showMessageDialog(null, "Task not found.");
            }
            case "2" -> {
                String developer = JOptionPane.showInputDialog("Enter the developer's name to search for:");
                StringBuilder developerTasks = new StringBuilder();
                for (Task task : tasks) {
                    if (task.getDeveloperDetails().equalsIgnoreCase(developer)) {
                        developerTasks.append(task.printTaskDetails()).append("\n\n");
                    }
                }
                JOptionPane.showMessageDialog(null, developerTasks.isEmpty() ? "No tasks found for this developer." : developerTasks.toString());
            }
            default -> JOptionPane.showMessageDialog(null, "Invalid search option.");
        }
    }

    // Method to delete a task by name
    private void deleteTask() {
        String taskName = JOptionPane.showInputDialog("Enter the name of the task to delete:");
        boolean taskRemoved = tasks.removeIf(task -> task.getTaskName().equalsIgnoreCase(taskName));

        if (taskRemoved) {
            JOptionPane.showMessageDialog(null, "Task successfully deleted.");
        } else {
            JOptionPane.showMessageDialog(null, "Task not found.");
        }}}
    

   

