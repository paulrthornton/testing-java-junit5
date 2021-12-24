package guru.springframework.sfgpetclinic.model;

import guru.springframework.sfgpetclinic.RepeatedModelTests;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.TestInfo;

public class PersonRepeatedTest implements RepeatedModelTests{

    @RepeatedTest(value = 10, name = "{displayName} : {currentRepetition} - {totalRepetitions}")
    @DisplayName("My repeated test")
    void myRepeatedTest() {

    }

    @RepeatedTest(5)
    void myRepeatedTestWithDI(TestInfo testInfo, RepetitionInfo repetitionInfo) {
        System.out.println(testInfo.getDisplayName() + ": " + repetitionInfo.getCurrentRepetition());
    }

    @RepeatedTest(value = 5, name = "{displayName} : {currentRepetition} | {totalRepetitions}")
    @DisplayName("My Assignment Repeated Test")
    void myAssignmentRepeated() {
        // todo impl
    }

}
