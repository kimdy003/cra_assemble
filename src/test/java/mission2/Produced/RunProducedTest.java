package mission2.Produced;

import mission2.Car;
import mission2.Enum.Brake;
import mission2.Enum.CarType;
import mission2.Enum.Engine;
import mission2.Enum.Steering;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class RunProducedTest {
    private final InputStream originalIn = System.in;
    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream outContent;
    private RunProduced runProduced;

    @BeforeEach
    void setUpStreams() {
        runProduced = new RunProduced();

        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void restoreStreams() {
        System.setIn(originalIn);
        System.setOut(originalOut);
    }

    @Test
    @DisplayName("RUN_PRODUCED | PASS")
    void runProduced_pass() {
        Car car = new Car.Builder()
                .type(CarType.SEDAN)
                .engine(Engine.GM)
                .brake(Brake.MANDO)
                .steering(Steering.BOSCH)
                .build();

        runProduced.execute(car);
        String outStr = outContent.toString();
        assertTrue(outStr.contains("Car Type : SEDAN"));
        assertTrue(outStr.contains("Engine   : GM"));
        assertTrue(outStr.contains("Brake    : MANDO"));
        assertTrue(outStr.contains("Steering : BOSCH"));
        assertTrue(outStr.contains("자동차가 동작됩니다."));
    }

    @Test
    @DisplayName("RUN_PRODUCED | 고장난 엔진")
    void runProduced_고장난_엔진() {
        Car car = new Car.Builder()
                .type(CarType.SEDAN)
                .engine(Engine.BROKEN)
                .brake(Brake.MANDO)
                .steering(Steering.BOSCH)
                .build();

        runProduced.execute(car);
        assertTrue(outContent.toString().contains("엔진이 고장나있습니다."));
    }

    @Test
    @DisplayName("RUN_PRODUCED | FAIL")
    void runProduced_fail() {
        Car car = new Car.Builder()
                .type(CarType.TRUCK)
                .engine(Engine.WIA)
                .brake(Brake.BOSCH)
                .steering(Steering.MOBIS)
                .build();

        runProduced.execute(car);
        assertTrue(outContent.toString().contains("자동차가 동작되지 않습니다"));
    }
}
