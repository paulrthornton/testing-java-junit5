package guru.springframework.sfgpetclinic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestInfo;

@Tag("repeated")
public interface RepeatedModelTests {

    @BeforeEach
    default void beforeEachConsoleOutputter(TestInfo testInfo, RepetitionInfo repInfo) {
        System.out.println("Running test - " + testInfo.getDisplayName() + ": " + repInfo.getCurrentRepetition());
    }
}
