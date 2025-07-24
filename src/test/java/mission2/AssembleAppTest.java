package mission2;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class AssembleAppTest {
    private final InputStream originalIn = System.in;
    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream outContent;

    @BeforeEach
    void setUpStreams() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void restoreStreams() {
        System.setIn(originalIn);
        System.setOut(originalOut);
    }

    @Test
    @DisplayName("ASSEMBLE | exit")
    void main_exit_pass() {
        String input = "exit\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        AssembleApp.main(new String[]{});

        String out = outContent.toString();
        assertTrue(out.contains("어떤 차량 타입을 선택할까요?"));
        assertTrue(out.contains("바이바이"));
    }

    @Test
    @DisplayName("ASSEMBLE | 전체 1 선택")
    void main_all_1_pass() {
        String input = String.join("\n", "1", "1", "1", "1", "1", "exit") + "\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        AssembleApp.main(new String[]{});

        String out = outContent.toString();
        assertTrue(out.contains("어떤 차량 타입을 선택할까요?"));
        assertTrue(out.contains("어떤 엔진을 탑재할까요?"));
        assertTrue(out.contains("어떤 제동장치를 선택할까요?"));
        assertTrue(out.contains("어떤 조향장치를 선택할까요?"));
        assertTrue(out.contains("멋진 차량이 완성되었습니다."));

        assertTrue(out.contains("Car Type : SEDAN"));
        assertTrue(out.contains("Engine   : GM"));
        assertTrue(out.contains("Brake    : MANDO"));
        assertTrue(out.contains("Steering : BOSCH"));
        assertTrue(out.contains("자동차가 동작됩니다."));

        assertTrue(out.contains("바이바이"));
    }
}
