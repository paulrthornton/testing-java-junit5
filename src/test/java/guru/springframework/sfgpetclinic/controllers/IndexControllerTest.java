package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.ControllerTests;
import org.junit.jupiter.api.*;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

class IndexControllerTest implements ControllerTests {

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

        assertThat(controller.index()).isEqualTo("index");
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

    @Test
    void testAssumptionTrue() {
        // assumeTrue("Guru".equalsIgnoreCase(System.getenv("Wibble")));
    }

    @Test
    void testAssumptionTrueAssumptionIsTrue() {
        assumeTrue("Guru".equalsIgnoreCase("Guru"));
    }
}