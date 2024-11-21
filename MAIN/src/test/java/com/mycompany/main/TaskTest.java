/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.main;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
/**
 *
 * @author RC_Student_lab
 */
public class TaskTest {
   @Test
public void testGenerateTaskID() {
    Task task = new Task("Create Login", "Develop a login system", "Mike Smith", 5, "To Do");
    assertEquals("CR:1:MS", task.getTaskId(), "Task ID generation is incorrect.");
}
@Test
public void testPrintTaskDetails() {
    Task task = new Task("Add Arrays", "Array functionality", "Glenda Oberholzer", 11, "Done");
    System.out.println("Actual Output:");
    System.out.println(task.printTaskDetails());

    String expected = """
            Task ID: AA:2:GO
            Task Name: Add Arrays
            Task Description: Array functionality
            Developer: Glenda Oberholzer
            Task Duration: 11 hours
            Task Status: Done
            """;
    assertEquals(expected, task.printTaskDetails(), "Task details are incorrectly formatted.");
}

@Test
public void testIsStatus() {
    Task task = new Task("Create Reports", "Generate reports", "Samantha Paulson", 2, "Done");
    assertTrue(task.isStatus("Done"), "Task status check failed.");
    assertFalse(task.isStatus("To Do"), "Task status falsely matched.");
}
}