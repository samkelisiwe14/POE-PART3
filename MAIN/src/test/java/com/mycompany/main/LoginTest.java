/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.main;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoginTest {
    private Login login;

    @BeforeEach
    public void setUp() {
        login = new Login(); // Create a new Login instance before each test
    }

    @Test
    public void testCheckUserName_Valid() {
        assertTrue(login.checkUserName("user_1"));
    }

    @Test
    public void testCheckUserName_Invalid() {
        assertFalse(login.checkUserName("user")); // No underscore
        assertFalse(login.checkUserName("username_1")); // More than 5 characters
    }

    @Test
    public void testCheckPasswordComplexity_Valid() {
        assertTrue(login.checkPasswordComplexity("Ch&&sec@ke99!")); // Complex password
    }

    @Test
    public void testCheckPasswordComplexity_Invalid() {
        assertFalse(login.checkPasswordComplexity("password")); // Missing upper, number, special char
        assertFalse(login.checkPasswordComplexity("PASSWORD123!")); // Missing lower case
        assertFalse(login.checkPasswordComplexity("password123")); // Missing upper case and special char
    }

    @Test
    public void testRegisterUser_Success() {
        String result = login.registerUser("user_1", "Ch&&sec@ke99!", "First", "Last");
        assertEquals("User registered successfully!", result);
    }

    @Test
    public void testRegisterUser_InvalidUsername() {
        String result = login.registerUser("user", "Ch&&sec@ke99!", "First", "Last");
        assertEquals("Username is not correctly formatted, please ensure that your username contains an underscore and is no more than 5 characters in length.", result);
    }

    @Test
    public void testRegisterUser_InvalidPassword() {
        String result = login.registerUser("user_1", "password", "First", "Last");
        assertEquals("Password is not correctly formatted, please ensure that the password contains at least 8 characters, a capital letter, a number and a special character.", result);
    }

    @Test
    public void testLoginUser_Success() {
        login.registerUser("user_1", "Ch&&sec@ke99!", "First", "Last"); // Register the user first
        assertTrue(login.loginUser("user_1", "Ch&&sec@ke99!")); // Successful login
    }

    @Test
    public void testLoginUser_Failure() {
        assertFalse(login.loginUser("non_existent_user", "password")); // Non-existent user
    }

    @Test
    public void testReturnLoginStatus_Success() {
        login.registerUser("user_1", "Ch&&sec@ke99!", "First", "Last"); // Register the user first
        String result = login.returnLoginStatus("user_1", "Ch&&sec@ke99!");
        assertEquals("Welcome First, Last! It is great to see you again.", result);
    }

    @Test
    public void testReturnLoginStatus_Failure() {
        String result = login.returnLoginStatus("user_1", "wrongpassword"); // Incorrect password
        assertEquals("Username or password incorrect, please try again.", result);
    }

    @Test
    public void testReturnLoginStatus_NonExistentUser() {
        String result = login.returnLoginStatus("non_existent_user", "password");
        assertEquals("Username or password incorrect, please try again.", result);
    }
}
