package mission2.menu;

import mission2.Car;
import mission2.Enum.Steering;
import mission2.Enum.Step;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SteeringMenuTest {
    @Mock
    Car.Builder builder;
    private SteeringMenu menu;

    @BeforeEach
    void setUp() {
        menu = new SteeringMenu();
    }

    @Test
    @DisplayName("STEERING | 0 선택, 뒤로 가기(BRAKE)")
    void steering_select_0() {
        Optional<Step> next = menu.input("0", builder);

        verify(builder, never()).steering(any());
        assertEquals(Optional.of(Step.BRAKE), next);
    }

    @Test
    @DisplayName("STEERING | 2 선택, MOBIS")
    void steering_select_2() {
        Optional<Step> next = menu.input("2", builder);

        verify(builder).steering(Steering.MOBIS);
        assertEquals(Optional.of(Step.RUN_TEST), next);
    }

    @Test
    @DisplayName("STEERING | 범위 밖 숫자 선택")
    void steering_select_7() {
        Optional<Step> next = menu.input("7", builder);

        verify(builder, never()).steering(any());
        assertEquals(Optional.of(Step.STEERING), next);
    }

    @Test
    @DisplayName("STEERING | 문자 선택")
    void steering_select_string() {
        Optional<Step> next = menu.input("test", builder);

        verify(builder, never()).steering(any());
        assertEquals(Optional.of(Step.STEERING), next);
    }
}
