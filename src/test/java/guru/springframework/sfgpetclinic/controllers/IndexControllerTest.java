package guru.springframework.sfgpetclinic.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class IndexControllerTest {

    IndexController controller;

    @BeforeEach
    void setUp() {
        controller = new IndexController();
    }

    @DisplayName("Test Proper View Name is returned for index page")
    @Test
    void index() {
        assertEquals("index", controller.index());
        assertEquals("index", controller.index(), "Wrong View Returned");

        assertEquals("index", controller.index(), () -> "Another Expensive Message " +
                "Make me only if you have to");
    }

    @Test
    @DisplayName("Test Exception")
    void oopsHandler() {
        assertThrows(ValueNotFoundException.class, () -> {
            controller.oopsHandler();
        });
    }

    @Disabled("Test timeout")
    @Test void testTimeout() {
        assertTimeout(Duration.ofMillis(100), () -> {
            Thread.sleep((5000));

            System.out.println("I got here");
        });
    }

    @Disabled("Test timeout preemp")
    @Test void testTimeoutPreemptive() {
        assertTimeoutPreemptively(Duration.ofMillis(100), () -> {
            Thread.sleep((5000));

            System.out.println("I got here preemptively");
        });
    }
}