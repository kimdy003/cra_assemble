package mission2.menu;

import mission2.Car;
import mission2.Enum.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RunTestMenuTest {
    @Mock
    private Car.Builder builder;
    @Mock
    private Car car;

    private RunTestMenu menu;
    private final InputStream originalIn = System.in;
    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream outContent;


    @BeforeEach
    void setUp() {
        menu = new RunTestMenu();
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        lenient().when(builder.build()).thenReturn(car);
    }

    @AfterEach
    void restoreStreams() {
        System.setIn(originalIn);
        System.setOut(originalOut);
    }

    @Test
    @DisplayName("RUN_TEST | 0 선택, 처음으로 가기(CAR_TYPE)")
    void runTest_select_0() {
        Optional<Step> result = menu.input("0", builder);
        String out = outContent.toString();

        assertEquals(Optional.of(Step.CAR_TYPE), result);
        assertTrue(out.isEmpty());
        verifyNoInteractions(builder);
    }

    @Test
    @DisplayName("RUN_TEST | 문자 선택")
    void runTest_select_string() {
        Optional<Step> result = menu.input("test", builder);
        String out = outContent.toString();

        assertEquals(Optional.of(Step.RUN_TEST), result);
        assertTrue(out.contains("ERROR :: 0 이상의 정수만 입력 가능"));
        verifyNoInteractions(builder);
    }

    @Test
    @DisplayName("RUN_TEST | 3 선택, 1 또는 2 선택 가능 에러")
    void runtest_select_3() {
        Optional<Step> result = menu.input("3", builder);
        String out = outContent.toString();

        assertEquals(Optional.of(Step.RUN_TEST), result);
        assertTrue(out.contains("ERROR :: 1 또는 2만 선택 가능합니다"));
        verify(builder).build();
    }

    @Test
    @DisplayName("RUN_TEST | 자동차 동작")
    void runtest_run_동작() {
        when(car.getType()).thenReturn(CarType.SUV);
        when(car.getEngine()).thenReturn(Engine.GM);
        when(car.getBrake()).thenReturn(Brake.MANDO);
        when(car.getSteering()).thenReturn(Steering.BOSCH);
        when(car.isValidCombination()).thenReturn(false);

        Optional<Step> result = menu.input("1", builder);

        String outStr = outContent.toString();
        assertTrue(outStr.contains("Car Type : SUV"));
        assertTrue(outStr.contains("Engine   : GM"));
        assertTrue(outStr.contains("Brake    : MANDO"));
        assertTrue(outStr.contains("Steering : BOSCH"));
        assertTrue(outStr.contains("자동차가 동작됩니다."));
        assertEquals(Optional.of(Step.RUN_TEST), result);
    }

    @Test
    @DisplayName("RUN_TEST | 고장난 엔진 선택")
    void runtest_run_고장난_엔진() {
        when(car.getEngine()).thenReturn(Engine.BROKEN);

        Optional<Step> result = menu.input("1", builder);

        String outStr = outContent.toString();
        assertTrue(outStr.contains("엔진이 고장나있습니다."));
        assertTrue(outStr.contains("자동차가 움직이지 않습니다."));
        assertEquals(Optional.of(Step.RUN_TEST), result);
    }

    @Test
    @DisplayName("RUN_TEST | 자동차 미동작")
    void runtest_run_fail() {
        when(car.getEngine()).thenReturn(Engine.GM);
        when(car.isValidCombination()).thenReturn(true);

        Optional<Step> result = menu.input("1", builder);

        String outStr = outContent.toString();
        assertTrue(outStr.contains("자동차가 동작되지 않습니다"));
        assertEquals(Optional.of(Step.RUN_TEST), result);
    }

    @Test
    @DisplayName("RUN_TEST | 조합 테스트 PASS")
    void runtest_test_pass() {
        when(car.isValidCombination()).thenReturn(false);

        Optional<Step> result = menu.input("2", builder);

        String outStr = outContent.toString();
        assertTrue(outStr.contains("자동차 부품 조합 테스트 결과 : PASS"));
        assertEquals(Optional.of(Step.RUN_TEST), result);
    }

    @Test
    @DisplayName("RUN_TEST | 조합 테스트 FAIL")
    void runtest_test_fail() {
        when(car.isValidCombination()).thenReturn(true);
        when(car.invalidCombinationReason()).thenReturn("잘못된 조합");

        Optional<Step> result = menu.input("2", builder);

        String outStr = outContent.toString();
        assertTrue(outStr.contains("자동차 부품 조합 테스트 결과 : FAIL"));
        assertTrue(outStr.contains("잘못된 조합"));
        assertEquals(Optional.of(Step.RUN_TEST), result);
    }
}
