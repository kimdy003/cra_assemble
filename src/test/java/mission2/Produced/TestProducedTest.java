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

class TestProducedTest {
    private final InputStream originalIn = System.in;
    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream outContent;
    private TestProduced testProduced;

    @BeforeEach
    void setUpStreams() {
        testProduced = new TestProduced();

        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void restoreStreams() {
        System.setIn(originalIn);
        System.setOut(originalOut);
    }

    @Test
    @DisplayName("TEST_PRODUCED | PASS")
    void testProduced_pass() {
        Car car = new Car.Builder()
                .type(CarType.SUV)
                .engine(Engine.GM)
                .brake(Brake.MANDO)
                .steering(Steering.BOSCH)
                .build();

        testProduced.execute(car);
        assertTrue(outContent.toString().contains("PASS"));
    }

    @Test
    @DisplayName("TEST_PRODUCED | 브레이크 BOSCH, 조향장치 MOBIS")
    void runProduced_fail_1() {
        Car car = new Car.Builder()
                .type(CarType.SEDAN)
                .engine(Engine.WIA)
                .brake(Brake.BOSCH)
                .steering(Steering.MOBIS)
                .build();

        testProduced.execute(car);
        String outStr = outContent.toString();
        assertTrue(outStr.contains("FAIL"));
        assertTrue(outContent.toString().contains("Bosch 제동장치에는 Bosch 조향장치만 사용 가능"));
    }

    @Test
    @DisplayName("TEST_PRODUCED | 브레이크 BOSCH, 조향장치 MOBIS")
    void runProduced_fail_11() {
        Car car = new Car.Builder()
                .type(CarType.SEDAN)
                .engine(Engine.TOYOTA)
                .brake(Brake.BOSCH)
                .steering(Steering.BOSCH)
                .build();

        testProduced.execute(car);
        String outStr = outContent.toString();
        assertTrue(outStr.contains("PASS"));
    }

    @Test
    @DisplayName("TEST_PRODUCED | SEDAN & CONTINENTAL")
    void testProduced_fail_2() {
        Car car = new Car.Builder()
                .type(CarType.SEDAN)
                .engine(Engine.WIA)
                .brake(Brake.CONTINENTAL)
                .steering(Steering.MOBIS)
                .build();

        testProduced.execute(car);
        String outStr = outContent.toString();
        assertTrue(outStr.contains("FAIL"));
        assertTrue(outContent.toString().contains("Sedan에는 Continental 제동장치 사용 불가"));
    }

    @Test
    @DisplayName("TEST_PRODUCED | SUB & TOYOTA")
    void testProduced_pass_3() {
        Car car = new Car.Builder()
                .type(CarType.SUV)
                .engine(Engine.TOYOTA)
                .brake(Brake.CONTINENTAL)
                .steering(Steering.MOBIS)
                .build();

        testProduced.execute(car);
        String outStr = outContent.toString();
        assertTrue(outStr.contains("FAIL"));
        assertTrue(outContent.toString().contains("SUV에는 TOYOTA 엔진 사용 불가"));
    }

    @Test
    @DisplayName("TEST_PRODUCED | SUB & another")
    void testProduced_fail_3_another() {
        Car car = new Car.Builder()
                .type(CarType.SUV)
                .engine(Engine.WIA)
                .brake(Brake.BOSCH)
                .steering(Steering.MOBIS)
                .build();

        testProduced.execute(car);
        String outStr = outContent.toString();
        assertTrue(outStr.contains("FAIL"));
    }

    @Test
    @DisplayName("TEST_PRODUCED | TRUCK & WIA")
    void testProduced_fail_4() {
        Car car = new Car.Builder()
                .type(CarType.TRUCK)
                .engine(Engine.WIA)
                .brake(Brake.BOSCH)
                .steering(Steering.BOSCH)
                .build();

        testProduced.execute(car);
        String outStr = outContent.toString();
        assertTrue(outStr.contains("FAIL"));
        assertTrue(outContent.toString().contains("Truck에는 WIA 엔진 사용 불가"));
    }

    @Test
    @DisplayName("TEST_PRODUCED | TRUCK & MANDO")
    void testProduced_fail_5() {
        Car car = new Car.Builder()
                .type(CarType.TRUCK)
                .engine(Engine.TOYOTA)
                .brake(Brake.MANDO)
                .steering(Steering.MOBIS)
                .build();

        testProduced.execute(car);
        String outStr = outContent.toString();
        assertTrue(outStr.contains("FAIL"));
        assertTrue(outContent.toString().contains("Truck에는 Mando 제동장치 사용 불가"));
    }

    @Test
    @DisplayName("TEST_PRODUCED | TRUCK & another")
    void testProduced_pass_5_another() {
        Car car = new Car.Builder()
                .type(CarType.TRUCK)
                .engine(Engine.TOYOTA)
                .brake(Brake.BOSCH)
                .steering(Steering.MOBIS)
                .build();

        testProduced.execute(car);
        String outStr = outContent.toString();
        assertTrue(outStr.contains("FAIL"));
    }
}
