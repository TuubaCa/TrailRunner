package com.trailrunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



public class UserTest {

private User user;

@BeforeEach
public void setup() {
// Initialize user with basic information
user = new User(175.0f, 70.0f, 25);
}

    @Test
    public void shouldInitializeUserWithCorrectValues() {
       // Kontrollera att användarens initialvärden är korrekta 
        assertEquals(175.0f, user.getHeight(), "Height should be correctly initialized");
        assertEquals(70.0f, user.getWeight(), "Weight should be correctly initialized");
        assertEquals(25, user.getAge(), "Age should be correctly initialized");
    }
    

}